package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static Logger LOG = LoggerFactory.getLogger(FlightController.class);

    @ExceptionHandler(RestClientException.class)
    public void handleRestClientException(RestClientException exception, HttpServletResponse response) throws IOException {
        LOG.error("RestClientException error occurred " + exception.getMessage());
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception, HttpServletResponse response) throws IOException {
        LOG.error("Exception error occurred " + exception.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
