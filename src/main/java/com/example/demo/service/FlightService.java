package com.example.demo.service;

import com.example.demo.configuration.FlifoConfig;
import com.example.demo.domain.FlightInfoUpdate;
import com.example.demo.dto.FlightUpdate;
import com.example.demo.repository.FlightInfoUpdateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class FlightService implements IFlightService {

    private static Logger LOG = LoggerFactory.getLogger(FlightService.class);
    @Autowired
    private FlightInfoUpdateRepository flightInfoUpdateRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MultiValueMap<String, String> flifoRequestHeaders;
    @Autowired
    private FlifoConfig flifoConfig;

    public Iterable<FlightInfoUpdate> getFlightRecords() {
        return flightInfoUpdateRepository.findAll();
    }

    public void getFlights(FlightUpdate flightUpdate) throws RestClientException {
        ResponseEntity<FlightInfoUpdate> response = null;
        HttpEntity<Object> requestEntity = new HttpEntity<>(flifoRequestHeaders);
        String url = generateUrl(
                flifoConfig.getAeroAPIEndPoint(),
                flightUpdate.getAirportCode(),
                flightUpdate.getAirlineCode(),
                flightUpdate.getFlightType(),
                flightUpdate.getFutureWindow());

        LOG.info("Attempting to retrieve flights from FLIFO: " + url);
        response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, FlightInfoUpdate.class);
        LOG.info("FLIFO Response: " + response.getStatusCode());

        if (response != null) {
//            FlightInfoUpdate flightInfoUpdate = response.getBody();
//            save(flightInfoUpdate);

            save(response.getBody());
        }
    }

    private void save(FlightInfoUpdate flightInfoUpdate) {
        if (flightInfoUpdate != null) {
            flightInfoUpdateRepository.save(flightInfoUpdate);
        }
    }

    private String generateUrl(String endpoint, String airportCode, String airlineCode, String flightType, Integer futureWindow) {
        String url = endpoint + "/" + airportCode + "/" + airlineCode + "/" + flightType;
        if (futureWindow != 0) {
            url += "?futureWindow=" + futureWindow;
        }
        return url;
    }
}
