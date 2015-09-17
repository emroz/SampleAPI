package com.emroz.spring.sampleapi.logging.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahabib10 on 9/16/15.
 */
public class LogMessage {
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMethodHook() {
        return methodHook;
    }

    public void setMethodHook(String methodHook) {
        this.methodHook = methodHook;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getLogSource() {
        return logSource;
    }

    public void setLogSource(String logSource) {
        this.logSource = logSource;
    }


//    public String getStatusCode() {
//        return statusCode;
//    }
//
//    public void setStatusCode(String statusCode) {
//        this.statusCode = statusCode;
//    }

//    public long getResponseTime() {
//        return responseTime;
//    }
//
//    public void setResponseTime(long responseTime) {
//        this.responseTime = responseTime;
//    }
//
//    public String getEnvironment() {
//        return environment;
//    }
//
//    public void setEnvironment(String environment) {
//        this.environment = environment;
//    }
//
//    public String getOriginOfcall() {
//        return originOfcall;
//    }
//
//    public void setOriginOfcall(String originOfcall) {
//        this.originOfcall = originOfcall;
//    }
//
//    public String getApiInstanceId() {
//        return apiInstanceId;
//    }
//
//    public void setApiInstanceId(String apiInstanceId) {
//        this.apiInstanceId = apiInstanceId;
//    }
//
//    public String getDevice() {
//        return device;
//    }
//
//    public void setDevice(String device) {
//        this.device = device;
//    }

    String transactionId = "";
    String clientId = "";
    String methodHook= "";
    String methodName= "";
    String className= "";
    Object[] parameters;// = new ArrayList<Object>() {};
    Object returnValue;
    String returnType = "";
    String logSource = "";
    //String statusCode = "";
    //long responseTime = 0;
    //String environment= "";
    //String originOfcall= ""; //Country
    //String apiInstanceId= ""; // instance that handeled the request
    //String device= "";




}
