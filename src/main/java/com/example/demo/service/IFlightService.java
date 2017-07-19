package com.example.demo.service;

import com.example.demo.domain.FlightInfoRecord;
import com.example.demo.domain.FlightInfoUpdate;
import com.example.demo.dto.FlightUpdate;

public interface IFlightService {
    void getFlights(FlightUpdate flightUpdate);

    Iterable<FlightInfoUpdate> getFlightRecords();

    void updateFlightRecord(FlightInfoRecord updatedFlightRecord);

    void deleteFlightRecord(FlightInfoRecord updatedFlightRecord);
}
