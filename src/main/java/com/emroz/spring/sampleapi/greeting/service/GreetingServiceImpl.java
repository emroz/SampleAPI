package com.emroz.spring.sampleapi.greeting.service;

import com.emroz.spring.sampleapi.greeting.model.Greeting;
import com.emroz.spring.sampleapi.logging.Loggable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by ahabib10 on 9/11/15.
 */
@Service
public class GreetingServiceImpl implements GreetingService {

    private static final String template = "Hello, %s! from %s";

    @Value("${myprop.greetingFrom}")
    private String greetingFrom;

    @Loggable
    @Override
    public Greeting greet(long counter, String name) throws Exception{

        if (StringUtils.equalsIgnoreCase(name, "throw")) {
            throw new Exception("Testing Exception");
        } else {
            return new Greeting(counter, String.format(template, name, greetingFrom));
        }
    }
}
