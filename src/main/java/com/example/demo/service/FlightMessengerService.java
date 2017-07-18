package com.example.demo.service;

import com.example.demo.domain.FlightInfoRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

//TODO: add interface
@Service
public class FlightMessengerService {

    private static final String TOPIC_UPDATE = "/topic/flightupdate";
//    private static final String TOPIC_CREATE = "/topic/poicreate";
//    private static final String TOPIC_DELETE = "/topic/poidelete";

    private static Logger LOG = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private SimpMessageSendingOperations messaging;

    //    @Override
//    public void sendCreate(String payload) {
//        LOG.debug("Send create to client");
//        messaging.convertAndSend(TOPIC_CREATE, payload);
//    }
//    @Override
//    public void sendDelete(Long id) {
//        LOG.debug("Send delete message to client");
//        messaging.convertAndSend(TOPIC_DELETE, id);
//    }
    
    public void sendUpdate(FlightInfoRecord updatedFlightRecord) {
        LOG.debug("Send update to client");
        messaging.convertAndSend(TOPIC_UPDATE, updatedFlightRecord);
    }
}