import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  airportCodes: any[] = [
    {label: 'MIA'},
    {label: 'DBN'},
    {label: 'LAX'},
    {label: 'LGW'}
  ];
  selectedAirportCode: string;

  airlineCodes: any[] = [
    {label: 'AA'},
    {label: 'EI'},
    {label: 'DL'},
    {label: 'CO'}
  ];
  selectedAirlineCode: string;

  flightTypes: any[] = [
    {label: 'Arrival'},
    {label: 'Departure'}
  ];
  selectedFlightType: string;

  constructor() {
  }

  ngOnInit() {
  }

  onclick(): void {
  }
}
