package com.emroz.spring.sampleapi.logging.utility;

import java.util.UUID;

/**
 * Created by ahabib10 on 9/16/15.
 */
public class LogUtility {

    public static final String generateLocalTransactionID(){
        return UUID.randomUUID().toString();
    }
}
