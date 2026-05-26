package com.example.it211ss10hw03.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around("execution(* com.example.it211ss10hw03.service.InventoryService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long time = System.currentTimeMillis() - start;

            if (time > 500) {
                log.warn("[Performance Alert] Hàm {} quá chậm ({} ms)", joinPoint.getSignature().getName(), time);
            } else {
                log.info("Hàm {} chạy mất {} ms", joinPoint.getSignature().getName(), time);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.warn("Lỗi nghiệp vụ tại {}: {}", joinPoint.getSignature().getName(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Lỗi hệ thống tại {}: {}", joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        }
    }
}
