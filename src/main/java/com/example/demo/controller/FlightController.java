package com.example.demo.controller;

import com.example.demo.domain.FlightInfoRecord;
import com.example.demo.domain.FlightInfoUpdate;
import com.example.demo.dto.FlightUpdate;
import com.example.demo.service.IFlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    @Secured("ROLE_ADMIN")
    @CrossOrigin
    @RequestMapping(value = "/flights", method = RequestMethod.POST)
    public ResponseEntity<String> updateFlights(@RequestBody FlightUpdate flightUpdate) {
        LOG.info("Getting flights from FLIFO API");
        flightService.getFlights(flightUpdate);
        return new ResponseEntity<String>("Flights pulled back successfully", HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin
    @RequestMapping(value = "/updateflightrecord", method = RequestMethod.PUT)
    public ResponseEntity<String> updateflightrecord(@RequestBody FlightInfoRecord flightInfoRecord) {
        LOG.info("Request to update flight record");
        flightService.updateFlightRecord(flightInfoRecord);
        return new ResponseEntity<String>("flight record updated successfully", HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin
    @RequestMapping(value = "/deleteflightrecord", method = RequestMethod.POST) //i should use http.DELETE
    public ResponseEntity<String> deleteflightrecord(@RequestBody FlightInfoRecord flightInfoRecord) {
        LOG.info("Request to delete flight record");
        flightService.deleteFlightRecord(flightInfoRecord);
        return new ResponseEntity<String>("flight record deleted successfully", HttpStatus.OK);
    }
}
