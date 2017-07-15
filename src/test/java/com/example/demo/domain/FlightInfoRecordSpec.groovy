package com.example.demo.domain

import spock.lang.Specification

class FlightInfoRecordSpec extends Specification {
    void 'Test FlightInfoRecord: '() {

        given:
        FlightInfoRecord flightInfoRecord = new FlightInfoRecord()
        OperatingCarrier operatingCarrier = new OperatingCarrier()

        when: 'property setters called'
        flightInfoRecord.airportCode = "airportCode"
        flightInfoRecord.scheduled = "scheduled"
        flightInfoRecord.estimated = "estimated"
        flightInfoRecord.status = "status"
        flightInfoRecord.statusText = "statusText"
        flightInfoRecord.gate = "gate"
        flightInfoRecord.city = "city"
        flightInfoRecord.operatingCarrier = operatingCarrier

        then: 'test property getters return expected value'
        assert (flightInfoRecord.getAirportCode() == "airportCode")
        assert (flightInfoRecord.getScheduled() == "scheduled")
        assert (flightInfoRecord.getEstimated() == "estimated")
        assert (flightInfoRecord.getStatus() == "status")
        assert (flightInfoRecord.getStatusText() == "statusText")
        assert (flightInfoRecord.getGate() == "gate")
        assert (flightInfoRecord.getCity() == "city")
        assert (flightInfoRecord.getOperatingCarrier() == operatingCarrier)
    }
}
