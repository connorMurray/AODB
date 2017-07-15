package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OperatingCarrier {

    @Id
    @GeneratedValue
    private Long id;

    String airlineCode;
    String flightNumber;
    String airline;

    public OperatingCarrier() {

    }

    public String getAirlineCode() {

        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {

        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {

        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {

        this.flightNumber = flightNumber;
    }

    public String getAirline() {

        return airline;
    }

    public void setAirline(String airline) {

        this.airline = airline;
    }
}