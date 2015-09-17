package com.emroz.spring.sampleapi.logging.model;

import java.security.PrivateKey;
import java.util.List;

/**
 * Created by ahabib10 on 9/16/15.
 */
public class LogInfo {

    private String transactionId;
    private String clientId;
    private String methodName;
    private String className;
    private String returnValue;
    private List<String> argumentList;
    private String message;
    private String payload;
}
