package com.emroz.spring.sampleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import java.io.IOException;


/**
 * Created by ahabib10 on 9/11/15.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public Filter myFilter(){
//        return new Filter() {
//            @Override
//            public void init(FilterConfig filterConfig) throws ServletException {
//                System.out.println("Init FIlter");
//
//            }
//
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//                System.out.println("Do FIlter");
//                chain.doFilter(request, response);
//            }
//
//            @Override
//            public void destroy() {
//                System.out.println("Init FIlter");
//            }
//        };
//    }

}
