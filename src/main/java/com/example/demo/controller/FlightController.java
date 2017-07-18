package com.example.demo.controller;

import com.example.demo.domain.FlightInfoUpdate;
import com.example.demo.dto.FlightUpdate;
import com.example.demo.service.IFlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlightController {

    private static Logger LOG = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private IFlightService flightService;

    @CrossOrigin
    @RequestMapping(value = "/flightrecords", method = RequestMethod.GET)
    public Iterable<FlightInfoUpdate> getFlightRecords() {
        LOG.info("Getting flight list from database");
        return flightService.getFlightRecords();
    }

    @CrossOrigin
    @RequestMapping(value = "/flights", method = RequestMethod.POST)
    public ResponseEntity<String> updateFlights(@RequestBody FlightUpdate flightUpdate) {
        LOG.info("Getting flights from FLIFO API");
        flightService.getFlights(flightUpdate);
        return new ResponseEntity<String>("Flights pulled back successfully", HttpStatus.OK);
    }
}
