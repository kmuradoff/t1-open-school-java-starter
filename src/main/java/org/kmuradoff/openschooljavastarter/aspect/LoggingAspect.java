package org.kmuradoff.openschooljavastarter.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.kmuradoff.openschooljavastarter.config.LoggingProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final LoggingProperty properties;

    public LoggingAspect(LoggingProperty properties) {
        this.properties = properties;
    }

    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object logHttpRequestResponse(ProceedingJoinPoint joinPoint) {
        if (!properties.isEnabled()) {
            return proceedWithoutLogging(joinPoint);
        }

        logRequest();

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            throw new RuntimeException("Exception in LoggingAspect", ex);
        }

        logResponse();
        return result;
    }

    private Object proceedWithoutLogging(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable ex) {
            throw new RuntimeException("Exception in LoggingAspect", ex);
        }
    }

    private void logRequest() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String message = String.format("HTTP Request: %s %s",
                    request.getMethod(), request.getRequestURI());
            logAtConfiguredLevel(message);
        }
    }

    private void logResponse() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletResponse response = attributes.getResponse();
            if (response != null) {
                String message = String.format("HTTP Response Status: %d", response.getStatus());
                logAtConfiguredLevel(message);
            }
        }
    }

    private void logAtConfiguredLevel(String message) {
        switch (properties.getLevel().toUpperCase()) {
            case "DEBUG" -> logger.debug(message);
            case "WARN" -> logger.warn(message);
            case "ERROR" -> logger.error(message);
            default -> logger.info(message);
        }
    }
}