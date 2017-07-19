import { Injectable } from '@angular/core';
import * as _ from 'underscore';
import {IFlightRecord} from "./flight.model";

export interface IFlightService {
  deleteFlightRecord(flightRecords: IFlightRecord[], flightRecordToDelete: IFlightRecord): IFlightRecord[];
  updateFlightRecord(flightRecords: IFlightRecord[], updatedFlightRecord: IFlightRecord): IFlightRecord[];
}
@Injectable()
export class FlightService implements IFlightService{

  constructor() { }

  public deleteFlightRecord(flightRecords: IFlightRecord[], flightRecordToDelete: IFlightRecord): IFlightRecord[] {
    flightRecords = _.reject(flightRecords, (record: IFlightRecord) => {
      return record.id === flightRecordToDelete.id;
    });
    return flightRecords;
  }

  public updateFlightRecord(flightRecords: IFlightRecord[], updatedFlightRecord: IFlightRecord): IFlightRecord[] {
    flightRecords.forEach((matchingRecord: IFlightRecord) => {
      if (matchingRecord.id === updatedFlightRecord.id) {
        matchingRecord.airportCode = updatedFlightRecord.airportCode;
        matchingRecord.scheduled = updatedFlightRecord.scheduled;
        matchingRecord.estimated = updatedFlightRecord.estimated;
        matchingRecord.gate = updatedFlightRecord.gate;
        matchingRecord.status = updatedFlightRecord.status;
        matchingRecord.statusText = updatedFlightRecord.statusText;
        matchingRecord.city = updatedFlightRecord.city;
        matchingRecord.remark = updatedFlightRecord.remark;
        matchingRecord.operatingCarrier = updatedFlightRecord.operatingCarrier;
        matchingRecord.operatingCarrier.airlineCode = updatedFlightRecord.operatingCarrier.airlineCode;
        matchingRecord.operatingCarrier.flightNumber = updatedFlightRecord.operatingCarrier.flightNumber;
        matchingRecord.operatingCarrier.airline = updatedFlightRecord.operatingCarrier.airline;
      }
    });
    return flightRecords;
  }
}
