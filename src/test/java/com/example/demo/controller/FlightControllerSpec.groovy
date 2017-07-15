package com.example.demo.controller

import com.example.demo.service.FlightService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Subject

class FlightControllerSpec extends Specification {
    def mockFlightService = Mock(FlightService)
    def @Subject
            flightController = new FlightController(flightService: mockFlightService)
    def mockMvc = MockMvcBuilders.standaloneSetup(flightController).build()

    void 'Test getFlights should retrieve flights from flight service '() {
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get('/flights').contentType(MediaType.APPLICATION_JSON))

        then:
        1 * mockFlightService.getFlights() >> {
            []
        }
        response.andExpect(MockMvcResultMatchers.status().isOk())
    }
}