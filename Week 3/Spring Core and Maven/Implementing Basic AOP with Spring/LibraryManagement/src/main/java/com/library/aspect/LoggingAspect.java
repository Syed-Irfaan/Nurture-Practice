package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.library.service.BookService.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("[LOG] Before method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.library.service.BookService.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("[LOG] After method: " + joinPoint.getSignature().getName());
    }
}
