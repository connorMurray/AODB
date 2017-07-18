package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FlightInfoRecord implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String airportCode;
    private String scheduled;
    private String estimated;
    private String gate;
    private String status;
    private String statusText;
    private String city;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("operatingCarrier")
    OperatingCarrier operatingCarrier;

    public FlightInfoRecord() {
    }

    public String getAirportCode() {

        return airportCode;
    }

    public void setAirportCode(String airportCode) {

        this.airportCode = airportCode;
    }

    public String getScheduled() {

        return scheduled;
    }

    public void setScheduled(String scheduled) {

        this.scheduled = scheduled;
    }

    public String getEstimated() {

        return estimated;
    }

    public void setEstimated(String estimated) {

        this.estimated = estimated;
    }

    public String getGate() {

        return gate;
    }

    public void setGate(String gate) {

        this.gate = gate;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getStatusText() {

        return statusText;
    }

    public void setStatusText(String statusText) {

        this.statusText = statusText;
    }

    public OperatingCarrier getOperatingCarrier() {

        return operatingCarrier;
    }

    public void setOperatingCarrier(OperatingCarrier operatingCarrier) {

        this.operatingCarrier = operatingCarrier;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }
}