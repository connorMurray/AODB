package com.example.demo.controller

import com.example.demo.domain.FlightInfoUpdate
import com.example.demo.service.FlightService
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class FlightControllerSpec extends Specification {
    def mockFlightService = Mock(FlightService)
    def @Subject
            flightController = new FlightController(flightService: mockFlightService)
    def mockMvc = MockMvcBuilders.standaloneSetup(flightController).build()

    @Shared
    Iterable<FlightInfoUpdate> flightInfoUpdate

//    void 'Test getFlights should retrieve flights from flight service '() {
//        when:
//        def response = mockMvc.perform(MockMvcRequestBuilders.get('/flights').contentType(MediaType.APPLICATION_JSON))
//
//        then:
//        1 * mockFlightService.getFlights() >> {
//            []
//        }
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//    }

//    void 'Test getFlightRecords should retrieve flight records from flight service '() {
//        when:
//        def response = mockMvc.perform(MockMvcRequestBuilders.get('/flightrecords').contentType(MediaType.APPLICATION_JSON))
//
//        then:
//        1 * mockFlightService.getFlightRecords() >> {
//            []
//        }
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//    }
}