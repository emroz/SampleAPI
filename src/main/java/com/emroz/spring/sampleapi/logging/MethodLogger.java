package com.emroz.spring.sampleapi.logging;

import com.emroz.spring.sampleapi.logging.model.LogContext;
import com.emroz.spring.sampleapi.logging.model.LogMessage;
import com.emroz.spring.sampleapi.logging.utility.PojoMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Objects;

import static net.logstash.logback.marker.Markers.append;
import static net.logstash.logback.marker.Markers.appendRaw;

/**
 * Created by ahabib10 on 9/11/15.
 */
@Aspect
@Component
public class MethodLogger {

    static Logger log = LoggerFactory.getLogger(MethodLogger.class.getName());

    @Value("${linkLog.logSourceName}")
    private String logSourceName;

    private static String BEFORE_METHOD = "Entering";
    private static String AFTER_METHOD = "Leaving";
    private static String AFTER_THROW = "Exception thrown";
    private static String LOG_INFO_FIELD_NAME = "additionalInfo";
    private static String LOG_MESSAGE = "Loggin grom LINK Aspect";

    private static String BEFORE_STRING = "[ entering < {0} > ]";

    private static String BEFORE_WITH_PARAMS_STRING = "[ entering < {0} > with params {1} ]";

    private static String AFTER_THROWING = "[ exception thrown < {0} > exception message {1} with params {2} ]";

    private static String AFTER_RETURNING = "[ leaving < {0} > returning {1} ]";

    private static String AFTER_RETURNING_VOID = "[ leaving < {0} > ]";

//    @Before("@annotation(com.emroz.spring.sampleapi.logging.Loggable)")
//    public void enteringMethod(JoinPoint joinPoint){
//        log.info("Entering method. Name: " + MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName());
//    }

    //@Before(value = "@annotation(trace)", argNames = "joinPoint, trace")
    //public void before(JoinPoint joinPoint, Loggable loggable) {
    @Before("@annotation(com.emroz.spring.sampleapi.logging.Loggable)")
    public void before(JoinPoint joinPoint) {

        //Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        LogMessage lm =new LogMessage();
        lm.setTransactionId(LogContext.getTransactionId());
        lm.setClientId(LogContext.getClientId());
        lm.setMethodHook(BEFORE_METHOD);

        //String name = joinPoint.getSignature().getName();
        lm.setMethodName(joinPoint.getSignature().getName());
        lm.setClassName(joinPoint.getTarget().getClass().getCanonicalName());
        lm.setParameters(joinPoint.getArgs());
        lm.setLogSource(logSourceName);
        try {
            String v = PojoMapper.toJson(lm, false);
            System.out.println(v);
            //log.info(append("name"));
            //log.info("{\"name\":\"value\"}");
           log.info(appendRaw(LOG_INFO_FIELD_NAME, v.toString()), LOG_MESSAGE);
            //log.info(append("test", v), "message");
        }catch(Exception e){

        }

//        if (ArrayUtils.isEmpty(joinPoint.getArgs())) {
//            log.info(format( BEFORE_STRING, name, constructArgumentsString(joinPoint.getArgs())));
//        } else {
//            log.info(format( BEFORE_WITH_PARAMS_STRING, transactionId, name, constructArgumentsString(joinPoint.getArgs())));
//        }
    }

//    @AfterThrowing(pointcut = "loggableOn(level)")
//    public void afterThrowing(final JoinPoint joinPoint, final Loggable level) throws Throwable {
//        log.error("afterThrowing"+MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName());
//    }

    @AfterThrowing(value = "@annotation(com.emroz.spring.sampleapi.logging.Loggable)",
            throwing = "throwable", argNames = "joinPoint, throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {

        Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        String name = joinPoint.getSignature().getName();
//        logger.log(LogLevel.ERROR, clazz, throwable, AFTER_THROWING, name,
//                throwable.getMessage(), constructArgumentsString(clazz,
//                        joinPoint.getArgs()));
        //log.error(format(AFTER_THROWING, name, throwable.getMessage(), constructArgumentsString(joinPoint.getArgs())));
        log.error(format(AFTER_THROWING, name, throwable.getMessage(), constructArgumentsString(joinPoint.getArgs())), throwable);
    }



    //@AfterReturning(value = "@annotation(trace)", returning = "returnValue", argNames = "joinPoint, trace, returnValue")
    //public void afterReturning(JoinPoint joinPoint, Loggable loggable, Object returnValue) {
    @AfterReturning(value = "@annotation(com.emroz.spring.sampleapi.logging.Loggable)", returning = "returnValue", argNames = "joinPoint, returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {

        LogMessage lm =new LogMessage();
        lm.setTransactionId(LogContext.getTransactionId());
        lm.setClientId(LogContext.getClientId());
        lm.setMethodHook(AFTER_METHOD);
        lm.setMethodName(joinPoint.getSignature().getName());
        lm.setClassName(joinPoint.getTarget().getClass().getCanonicalName());
        lm.setParameters(joinPoint.getArgs());
        lm.setLogSource(logSourceName);
        lm.setReturnValue(returnValue);

        Class<? extends Object> clazz = joinPoint.getTarget().getClass();
        String name = joinPoint.getSignature().getName();

        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) joinPoint
                    .getSignature();
            Class<?> returnType = signature.getReturnType();
            lm.setReturnType(returnType.getName());
            //System.out.println(returnValue.toString());
//            if (returnType.getName().compareTo("void") == 0) {
////                logger.log(loggable.value(), clazz, null, AFTER_RETURNING_VOID,
////                        name, constructArgumentsString(clazz, returnValue));
//
//                log.info(format(AFTER_RETURNING_VOID, constructArgumentsString(name , clazz, returnValue)));
//
//                return;
//            }
        }



        try {
            String v = PojoMapper.toJson(lm, false);
            System.out.println(v);
            log.info(appendRaw("message", v.toString()), "");
            //log.info(append("test", v), "message");
        }catch(Exception e){

        }

//        logger.log(loggable.value(), clazz, null, AFTER_RETURNING, name,
//                constructArgumentsString(clazz, returnValue));

        //log.info(format(AFTER_RETURNING, constructArgumentsString(name , clazz, returnValue)));
    }

    private String format( String pattern,  Object... arguments) {

        return MessageFormat.format(pattern, arguments);
    }

    private String constructArgumentsString(Object... arguments) {

        StringBuffer buffer = new StringBuffer();
        for (Object object : arguments) {

            buffer.append( " : " + object);
        }

        return buffer.toString();
    }

}
