package com.example.demo.dto;

import java.io.Serializable;


public class FlightUpdate implements Serializable {

    private String airportCode;
    private String flightType;
    private String airlineCode;
    private Integer futureWindow;

    public FlightUpdate() {
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Integer getFutureWindow() {
        return futureWindow;
    }

    public void setFutureWindow(Integer futureWindow) {
        this.futureWindow = futureWindow;
    }
}