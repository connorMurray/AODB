package com.example.demo.domain

import spock.lang.Specification

class OperatingCarrierSpec extends Specification {
    void 'Test OperatingCarrier Entityl: '() {
        given:
        OperatingCarrier operatingCarrier = new OperatingCarrier()

        when: 'property setters called'
        operatingCarrier.airlineCode = "airlineCode"
        operatingCarrier.airline = "airline"
        operatingCarrier.flightNumber = "flightNumber"

        then: 'test getters return expected value'
        assert (operatingCarrier.getAirlineCode() == "airlineCode")
        assert (operatingCarrier.getAirline() == "airline")
        assert (operatingCarrier.getFlightNumber() == "flightNumber")

    }
}