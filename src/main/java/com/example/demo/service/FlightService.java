package com.example.demo.service;

import com.example.demo.configuration.FlifoConfig;
import com.example.demo.domain.FlightInfoRecord;
import com.example.demo.domain.FlightInfoUpdate;
import com.example.demo.dto.FlightUpdate;
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
import org.springframework.web.client.RestTemplate;

@Service
public class FlightService implements IFlightService {

    private static Logger LOG = LoggerFactory.getLogger(FlightService.class);
    @Autowired
    private FlightInfoUpdateRepository flightInfoUpdateRepository;
    @Autowired
    private FlightIntoRecordRepository flightIntoRecordRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MultiValueMap<String, String> flifoRequestHeaders;
    @Autowired
    private FlifoConfig flifoConfig;
    @Autowired
    private FlightMessengerService flightMessengerService;

    public Iterable<FlightInfoUpdate> getFlightRecords() {
        return flightInfoUpdateRepository.findAll();
    }

    public void getFlights(FlightUpdate flightUpdate) {
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
            FlightInfoUpdate flightInfoUpdate = response.getBody();
            save(flightInfoUpdate);
        } else {
            LOG.error("Error accessing flifo response body");
        }
    }

    public void updateFlightRecord(FlightInfoRecord updatedFlightRecord) {
        LOG.info("Updating flight record");
        FlightInfoRecord flightInfoRecord = flightIntoRecordRepository.findOne(updatedFlightRecord.getId());
        flightInfoRecord.setRemark(updatedFlightRecord.getRemark());
        flightInfoRecord.setGate(updatedFlightRecord.getGate());
        flightInfoRecord.setEstimated(updatedFlightRecord.getEstimated());
        flightInfoRecord.setScheduled(updatedFlightRecord.getScheduled());
        flightIntoRecordRepository.save(flightInfoRecord);
        LOG.info("Flight record updated");
        flightMessengerService.sendUpdate(flightInfoRecord);
    }

    public void deleteFlightRecord(FlightInfoRecord updatedFlightRecord) {
        LOG.info("deleting flight record");
        FlightInfoRecord flightInfoRecord = flightIntoRecordRepository.findOne(updatedFlightRecord.getId());
        flightIntoRecordRepository.delete(flightInfoRecord);
        LOG.info("Flight record deleted");
        flightMessengerService.sendDelete(flightInfoRecord);
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
