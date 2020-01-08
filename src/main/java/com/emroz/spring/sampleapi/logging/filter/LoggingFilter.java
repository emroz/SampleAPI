package com.emroz.spring.sampleapi.logging.filter;

import com.emroz.spring.sampleapi.logging.model.LogContext;
import com.emroz.spring.sampleapi.logging.utility.LogUtility;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ahabib10 on 9/16/15.
 */
@Component
public class LoggingFilter extends OncePerRequestFilter {

    public static final Logger LOGGER = LoggerFactory
            .getLogger(LoggingFilter.class.getCanonicalName());

    @Value("${linkLog.transactionId.httpHeaderName}")
    private String transIdHeaderName;
    private String codeguru;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String transactionId = request.getHeader(transIdHeaderName);
        if (StringUtils.isEmpty(transactionId)){
            transactionId = LogUtility.generateLocalTransactionID();
        }
        LOGGER.info(" Logging Filter: Transaction Id :" + transactionId);
        LogContext.setTransactionId(transactionId);

        try{
            filterChain.doFilter(request, response);
        }finally {
            LogContext.clearContext();
        }


    }
}
