import {Component, OnInit} from '@angular/core';
import {DashboardService} from "./dashboard.service";
import {FlightService} from "./flight.service";
import {IFlightUpdateMenu, IFlightUpdateMenuValue, IFlightInformation, IFlightRecord} from "./flight.model";
import * as moment from 'moment';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  providers: [DashboardService, FlightService]
})
export class DashboardComponent implements OnInit {

  airportCodes: IFlightUpdateMenu[];
  selectedAirportCode: IFlightUpdateMenuValue;
  airlineCodes: IFlightUpdateMenu[];
  selectedAirlineCode: IFlightUpdateMenuValue;
  flightTypes: IFlightUpdateMenu[];
  selectedFlightType: IFlightUpdateMenuValue;
  futureWindow: number;
  flightInformation: IFlightInformation;
  selectedFlightRecord: IFlightRecord;
  displayDialog: boolean;

  constructor(private dashboardService: DashboardService, private flightService: FlightService) {
  }

  ngOnInit() {
    this.initialize();
  }

  onRowSelect(event) {
    this.displayDialog = true;
  }

  save(flightRecord: IFlightRecord): void {
    this.dashboardService.updateFlightRecord(flightRecord);
    this.flightInformation.flightRecord = this.flightService.updateFlightRecord(this.flightInformation.flightRecord, flightRecord);
    this.displayDialog = false;
  }

  delete(flightRecord: IFlightRecord): void {
    this.dashboardService.deleteFlightRecord(flightRecord);
    this.flightInformation.flightRecord = this.flightService.deleteFlightRecord(this.flightInformation.flightRecord, flightRecord);
    this.displayDialog = false;
  }

  isValidForm(): boolean {
    return this.selectedAirlineCode && this.selectedAirportCode &&
      this.selectedFlightType && !this.flightInformation;
  }

  update(): void {
    this.dashboardService.updateFlights(
      {
        airlineCode: this.selectedAirlineCode.code,
        airportCode: this.selectedAirportCode.code,
        flightType: this.selectedFlightType.code,
        futureWindow: this.futureWindow
      }
    ).then((result) => {
      this.getFlights();
    })
      .catch((error) => console.error(error));
  }

  private getFlights(): void {
    this.dashboardService.getFlights().subscribe(flightInformation => {
      this.onFlightInformationRetrieved(flightInformation);
    });
  }

  private onFlightInformationRetrieved(flightInformation: IFlightInformation): void {
    if (flightInformation && flightInformation.flightRecord) {
      flightInformation.flightRecord.forEach((flightRecord: IFlightRecord) => {
        if (flightRecord.estimated) {
          flightRecord.estimated = this.formatDateTime(flightRecord.estimated);
        }
        if (flightRecord.scheduled) {
          flightRecord.scheduled = this.formatDateTime(flightRecord.scheduled);
        }
      });
      this.flightInformation = flightInformation
    }
  }

  private formatDateTime(dateTime: string): string {
    return moment(dateTime).format('h:mm:ss a');
  }

  private initialize(): void {
    this.futureWindow = 0;
    this.airportCodes = [
      {label: 'MIA', value: {code: 'MIA'}},
      {label: 'DBN', value: {code: 'DBN'}},
      {label: 'LAX', value: {code: 'LAX'}},
      {label: 'LGW', value: {code: 'LGW'}}
    ];
    this.airlineCodes = [
      {label: 'AA', value: {code: 'AA'}},
      {label: 'EI', value: {code: 'EI'}},
      {label: 'DL', value: {code: 'DL'}},
      {label: 'CO', value: {code: 'CO'}},
    ];
    this.flightTypes = [
      {label: 'Arrival', value: {code: 'A'}},
      {label: 'Departure', value: {code: 'D'}},
    ];
  }
}
