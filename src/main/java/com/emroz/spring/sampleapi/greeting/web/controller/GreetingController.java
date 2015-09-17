package com.emroz.spring.sampleapi.greeting.web.controller;

import com.emroz.spring.sampleapi.greeting.model.Greeting;
import com.emroz.spring.sampleapi.greeting.service.GreetingService;

import com.emroz.spring.sampleapi.logging.Loggable;
import com.emroz.spring.sampleapi.logging.model.LogMessage;
import com.emroz.spring.sampleapi.logging.utility.PojoMapper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ahabib10 on 9/11/15.
 */
@RestController
public class GreetingController {

    private final Logger log = LoggerFactory.getLogger(GreetingController.class);

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    GreetingService greetingService;

    @Loggable
    @RequestMapping(value = "/greeting",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception {
        return greetingService.greet(counter.incrementAndGet(), name);
    }

}
