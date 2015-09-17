package com.emroz.spring.sampleapi.greeting.service;

import com.emroz.spring.sampleapi.greeting.model.Greeting;

/**
 * Created by ahabib10 on 9/11/15.
 */
public interface GreetingService {

    Greeting greet(long counter, String name) throws Exception;
}
