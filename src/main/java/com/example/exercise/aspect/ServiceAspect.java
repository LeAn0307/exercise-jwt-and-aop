package com.example.exercise.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ServiceAspect {
    private final Logger LOGGER =  LoggerFactory.getLogger(ServiceAspect.class);
    @Before("execution(* com.example.exercise.service.*.*(..))")
    public void before(JoinPoint joinPoint){
        LOGGER.info("before called "+ joinPoint.toString());
    }
    @After("execution(* com.example.exercise.service.*.*(..))")
    public void after(JoinPoint joinPoint){
        LOGGER.info("after called "+ joinPoint.toString());
    }
    @AfterThrowing("execution(* com.example.exercise.service.*.*(..))")
    public void afterWithBreak(JoinPoint joinPoint){
        LOGGER.info("error at "+ joinPoint.toString());
    }
}