package com.example.demo.service;

import com.example.demo.domain.FlightInfoUpdate;

public interface IFlightService {
    void getFlights();

    Iterable<FlightInfoUpdate> getFlightRecords();
}
