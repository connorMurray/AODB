package com.example.demo.controller;

import com.example.demo.domain.FlightInfoUpdate;
import com.example.demo.service.IFlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {

    private static Logger LOG = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private IFlightService flightService;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public void getFLights() {
        LOG.info("Getting flights from FLIFO API");
        flightService.getFlights();
    }

    @RequestMapping(value = "/flightrecords", method = RequestMethod.GET)
    public Iterable<FlightInfoUpdate> getFlightRecords() {
        LOG.info("Getting flight list from database");
        Iterable<FlightInfoUpdate> FlightInformationUpdates = flightService.getFlightRecords();
        return FlightInformationUpdates;
    }
}
