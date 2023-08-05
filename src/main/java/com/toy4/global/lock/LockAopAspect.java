package com.toy4.global.lock;

import com.toy4.global.aop.EmployeeLockInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LockAopAspect {

    private final LockService lockService;

    @Around("@annotation(com.toy4.global.aop.EmployeeLock) && args(request)")
    public Object aroundMethod(
            ProceedingJoinPoint proceedingJoinPoint,
            EmployeeLockInterface request
    ) throws Throwable {
        // lock 취득 시도
        lockService.lock(request.getEmail());

        try {
            return proceedingJoinPoint.proceed();
        } finally {
            // lock 해제
            lockService.unlock(request.getEmail());
        }
    }

}
