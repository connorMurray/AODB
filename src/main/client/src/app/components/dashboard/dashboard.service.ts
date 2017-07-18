import {Injectable} from '@angular/core';
import {Http, Response, RequestOptions, Headers} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import {IFlightInformation, IFlightUpdate} from "./flight.model";

export interface IDashboardService {
  getFlights(): Observable<IFlightInformation>;
  updateFlights(flightUpdate: IFlightUpdate): Promise<any>;
}

@Injectable()
export class DashboardService implements IDashboardService {

  private aodbUrl: string = 'http://localhost:8080';

  constructor(private _http: Http) {
  }

  public getFlights(): Observable<IFlightInformation> {
    console.info('Attempting to retrieve flights from aodb');
    let flightRecordUrl: string = '/flightrecords';
    return this._http.get(this.aodbUrl + flightRecordUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  //TODO: get flights when this returns status ok!
  updateFlights(flightUpdate: IFlightUpdate): Promise<any> {
    let headers = new Headers({'Content-Type': 'application/json'});
    return this._http
      .post('http://localhost:8080/flights', JSON.stringify(flightUpdate), {headers: headers})
      .map((response) => response.json())
      .toPromise()
      // .then(res => res.json().data)
      .catch(this.handleError);
  };

  private extractData(res: Response) {
    let body = res.json();
    return body[0] || [];
  }

  private handleError(error: any) {
    let errMsg = (error.message) ? error.message : error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
