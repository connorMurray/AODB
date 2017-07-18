package com.example.demo.service;

import com.example.demo.domain.FlightInfoUpdate;
import com.example.demo.dto.FlightUpdate;

public interface IFlightService {
    void getFlights(FlightUpdate flightUpdate);

    Iterable<FlightInfoUpdate> getFlightRecords();
}
