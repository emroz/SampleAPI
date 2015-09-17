package com.emroz.spring.sampleapi.logging.model;


import org.slf4j.MDC;

/**
 * Created by ahabib10 on 9/16/15.
 */
public class LogContext {

    private static final String TRANSACTION_ID_KEY = "Link.Transaction.Id";
    private static final String CLIENT_ID_KEY = "Link.Client.Id";



    public static void setTransactionId(String transactionId){
        MDC.put(TRANSACTION_ID_KEY, transactionId);
    }

    public static void setClientId(String clientId){
        MDC.put(CLIENT_ID_KEY, clientId);
    }

    public static String getTransactionId(){
        return MDC.get(TRANSACTION_ID_KEY);
    }

    public static String getClientId(){
        return MDC.get(CLIENT_ID_KEY);
    }

    public static void clearContext(){
        MDC.remove(TRANSACTION_ID_KEY);
        MDC.remove(CLIENT_ID_KEY);
    }


}
