package com.example.demo.service

import com.example.demo.configuration.FlifoConfig
import com.example.demo.domain.FlightInfoRecord
import com.example.demo.domain.FlightInfoUpdate
import com.example.demo.domain.OperatingCarrier
import com.example.demo.repository.FlightInfoUpdateRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class FlightServiceSpec extends Specification {
    def mockRestTemplate = Mock(RestTemplate)
    @Shared
    def mockFlifoConfig = Mock(FlifoConfig)
    def mockFlightInfoUpdateRepository = Mock(FlightInfoUpdateRepository)

    def mockResponseEntity = Mock(ResponseEntity);

    @Subject
    FlightService flightService

    @Shared
    FlightInfoUpdate flightInfoUpdate

    def setupSpec() {
        mockFlifoConfig.getAeroAPIEndPoint() >> "https://flifo.api.aero"
        mockFlifoConfig.getDepartureflightUpdateEndPoint() >> "/flifo/v3/flights/mia/d"
        mockFlifoConfig.getSubscribeForUpdates() >> true
        mockFlifoConfig.getFlightEndPoint() >> "/flight/"

        FlightInfoRecord flightInfoRecord = new FlightInfoRecord(
                airportCode: 'MIA',
                scheduled: 'scheduled',
                estimated: 'estimated',
                gate: 'D4',
                status: 'SC',
                city: "London",
                statusText: 'SCHEDULE_CHANGED',
                operatingCarrier: new OperatingCarrier(
                        airlineCode: 'EZY',
                        flightNumber: '556',
                        airline: 'EasyJet'))

        flightInfoUpdate = new FlightInfoUpdate(airportCode: "MIA", adi: "adi", flightDate: "22-APR-2017", flightRecord: [flightInfoRecord])
    }

    def setup() {
        flightService = new FlightService(
                restTemplate: mockRestTemplate,
                flifoConfig: mockFlifoConfig,
                flightInfoUpdateRepository: mockFlightInfoUpdateRepository
        )
    }

    void 'Test getFlightRecords(): request flights from database '() {
        when:
        flightService.getFlightRecords()

        then:
        1 * mockFlightInfoUpdateRepository.findAll()
    }
    
    void 'Test getFlights(): request flights from flifo and save to database '() {
        setup:
        mockResponseEntity.getStatusCode() >> HttpStatus.OK
        mockResponseEntity.getBody() >> flightInfoUpdate

        when:
        flightService.getFlights()

        then:
        1 * mockRestTemplate.exchange(_, _, _, _, _) >> { url, httpMethod, requestEntity, type, queryParams ->
            assert url == "https://flifo.api.aero/flifo/v3/flights/mia/d"
            assert httpMethod == HttpMethod.GET
            assert type == FlightInfoUpdate.class
            assert requestEntity == new HttpEntity<>(null);
            mockResponseEntity
        }

        then:
        1 * mockFlightInfoUpdateRepository.save(_) >> { FlightInfoUpdate flightInfoUpdate ->
            assert flightInfoUpdate.airportCode == 'MIA'
            assert flightInfoUpdate.adi == 'adi'
            assert flightInfoUpdate.flightDate == '22-APR-2017'

            assert flightInfoUpdate.flightRecord[0].airportCode == 'MIA'
            assert flightInfoUpdate.flightRecord[0].scheduled == 'scheduled'
            assert flightInfoUpdate.flightRecord[0].estimated == 'estimated'
            assert flightInfoUpdate.flightRecord[0].gate == 'D4'
            assert flightInfoUpdate.flightRecord[0].status == 'SC'
            assert flightInfoUpdate.flightRecord[0].city == 'London'
            assert flightInfoUpdate.flightRecord[0].statusText == 'SCHEDULE_CHANGED'

            assert flightInfoUpdate.flightRecord[0].operatingCarrier.airlineCode == 'EZY'
            assert flightInfoUpdate.flightRecord[0].operatingCarrier.flightNumber == '556'
            assert flightInfoUpdate.flightRecord[0].operatingCarrier.airline == 'EasyJet'
        }
    }
}