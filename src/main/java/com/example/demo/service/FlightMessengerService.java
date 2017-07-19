package com.example.demo.service;

import com.example.demo.domain.FlightInfoRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class FlightMessengerService {

    private static final String TOPIC_UPDATE = "/topic/flightupdate";
    private static final String TOPIC_DELETE = "/topic/flightdelete";

    private static Logger LOG = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private SimpMessageSendingOperations messaging;

    public void sendDelete(FlightInfoRecord flightInfoRecord) {
        LOG.debug("Send delete message to queue");
        messaging.convertAndSend(TOPIC_DELETE, flightInfoRecord);
    }

    public void sendUpdate(FlightInfoRecord flightInfoRecord) {
        LOG.debug("Send update to queue");
        messaging.convertAndSend(TOPIC_UPDATE, flightInfoRecord);
    }
}