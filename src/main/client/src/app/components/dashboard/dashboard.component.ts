import {Component, OnInit} from '@angular/core';
import {DashboardService} from "./dashboard.service";
import {IFlightUpdateMenu, IFlightUpdateMenuValue, IFlightInformation, IFlightRecord} from "./flight.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  providers: [DashboardService]
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

  constructor(private dashboardService: DashboardService) {
  }

  ngOnInit() {
    this.initialize();
  }

  //TODO: set to false then save and delete hit just for show
  onRowSelect(event) {
    this.displayDialog = true;
  }

  save(flightRecord: IFlightRecord): void {
    this.dashboardService.updateFlightRecord(flightRecord);
    this.displayDialog = false;
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
      //only if status code!
      this.getFlights();
    })
      .catch((error) => console.error(error));
  }

  getFlights(): void {
    this.dashboardService.getFlights().subscribe(flightInformation => {
      this.flightInformation = flightInformation
    });
  }

  isValidForm() {
    return this.selectedAirlineCode && this.selectedAirportCode &&
      this.selectedFlightType && !this.flightInformation;
  }

  initialize() {
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
