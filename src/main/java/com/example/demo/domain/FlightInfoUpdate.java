package com.example.demo.domain;

import javax.persistence.*;
import java.util.List;


@Entity
public class FlightInfoUpdate {

    @Id
    @GeneratedValue
    private Long id;

    private String airportCode;
    private String adi;
    private String flightDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "flight_info_update_id")
    private List<FlightInfoRecord> flightRecord;

    public FlightInfoUpdate() {

    }

    public String getAirportCode() {

        return airportCode;
    }

    public void setAirportCode(String airportCode) {

        this.airportCode = airportCode;
    }

    public String getAdi() {

        return adi;
    }

    public void setAdi(String adi) {

        this.adi = adi;
    }

    public String getFlightDate() {

        return flightDate;
    }

    public void setFlightDate(String flightDate) {

        this.flightDate = flightDate;
    }

    public List<FlightInfoRecord> getFlightRecord() {

        return flightRecord;
    }

    public void setFlightRecord(List<FlightInfoRecord> flightRecord) {

        this.flightRecord = flightRecord;
    }
}