package com.portal.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * JointPoint method for ExecutionTime Annotation
 */
@Log4j2
@Aspect
@Component
public class ExecutionTimeImpl {


    @Around("@annotation(ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object response = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("Execution Time for method: {} is {}ms", joinPoint.getSignature(), (end - start));
        return response;
    }

}
