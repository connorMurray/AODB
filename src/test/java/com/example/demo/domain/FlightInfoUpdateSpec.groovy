package com.example.demo.domain

import spock.lang.Specification

class FlightInfoUpdateSpec extends Specification {
    void 'Test FlightInfoUpdate: '() {

        given:
        FlightInfoUpdate flightInfoUpdate = new FlightInfoUpdate()
        FlightInfoRecord flightInfoRecord = new FlightInfoRecord()

        def list = []
        list.add(flightInfoRecord)

        when: 'property setters called'
        flightInfoUpdate.airportCode = "airportCode"
        flightInfoUpdate.adi = "adi"
        flightInfoUpdate.flightDate = "flightDate"
        flightInfoUpdate.flightRecord = list

        then: 'test property getters return expectected value'
        assert flightInfoUpdate.airportCode == "airportCode"
        assert flightInfoUpdate.adi == "adi"
        assert flightInfoUpdate.flightDate == "flightDate"
        assert flightInfoUpdate.flightRecord[0] == flightInfoRecord
    }
}