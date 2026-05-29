package com.skeleton.common.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around("execution(* com.skeleton..controller..*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        log.info("{} - {}ms", point.getSignature().toShortString(), System.currentTimeMillis() - start);
        return result;
    }
}
