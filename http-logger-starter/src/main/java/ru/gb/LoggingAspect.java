package ru.gb;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint jp) throws Throwable{
        long start = System.currentTimeMillis();
        String methodName = jp.getSignature().getName();

        Object proceed = jp.proceed();

        long executionTime = System.currentTimeMillis() - start;

        logger.log(Level.INFO, jp.getSignature() + "method name: " + methodName + " выполнен за " + executionTime + " мс");

        return proceed;
    }
}
