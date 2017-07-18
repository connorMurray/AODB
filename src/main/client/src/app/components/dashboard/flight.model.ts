export interface IFlightInformation {
  airportCode: string;
  adi: string;
  flightDate: string;
  flightRecord: IFlightRecord[];
}

export interface IFlightRecord {
  airportCode: string;
  scheduled: string;
  estimated: string;
  gate: string;
  status: string;
  statusText: string;
  city: string;
  operatingCarrier: IOperatingCarrier;
  id: number;
  remark: string;
}

export interface IOperatingCarrier {
  airlineCode: string;
  flightNumber: string;
  airline: string;
}

export interface IFlightUpdate {
  airlineCode: string;
  airportCode: string;
  flightType: string;
  futureWindow: number;
}

export interface IFlightUpdateMenu {
  label: string,
  value: IFlightUpdateMenuValue
}

export interface IFlightUpdateMenuValue {
  code: string;
}
