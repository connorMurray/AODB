package com.example.demo.service;

import com.example.demo.configuration.FlifoConfig;
import com.example.demo.domain.FlightInfoUpdate;
import com.example.demo.repository.FlightInfoUpdateRepository;
import com.example.demo.repository.FlightIntoRecordRepository;
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
    private FlightIntoRecordRepository flightInfoRecordRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MultiValueMap<String, String> flifoRequestHeaders;
    @Autowired
    private FlifoConfig flifoConfig;

    public Iterable<FlightInfoUpdate> getFlightRecords() {
        return flightInfoUpdateRepository.findAll();
    }

    public void getFlights() {
        HttpEntity<Object> requestEntity = new HttpEntity<>(flifoRequestHeaders);
        String url = generateUrl(flifoConfig.getAeroAPIEndPoint(), flifoConfig.getDepartureflightUpdateEndPoint());
        ResponseEntity<FlightInfoUpdate> response = null;

        try {
            LOG.info("Attempting to retrieve flights from FLIFO: " + url);
            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, FlightInfoUpdate.class);
            LOG.info("Response: " + response.getStatusCode());
        } catch (RestClientException ex) {
            LOG.error("Error retrieving flights from FLIFO: ", ex);
        }

        if (response != null) {
            FlightInfoUpdate flightInfoUpdate = response.getBody();
            save(flightInfoUpdate);
        }
    }

    private void save(FlightInfoUpdate flightInfoUpdate) {
        if (flightInfoUpdate != null) {
            flightInfoUpdateRepository.save(flightInfoUpdate);
        }
    }

    private String generateUrl(String... stringParts) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String part : stringParts) {
            stringBuilder.append(part);
        }
        String builtString = stringBuilder.toString();
        return builtString;
    }
}
