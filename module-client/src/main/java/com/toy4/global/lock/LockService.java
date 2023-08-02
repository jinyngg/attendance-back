package com.toy4.global.lock;

import static com.toy4.global.response.type.ErrorCode.EMPLOYEE_TRANSACTION_LOCK;

import com.toy4.domain.employee.exception.EmployeeException;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LockService {

    private final RedissonClient redissonClient;

    public void lock(String key) throws EmployeeException {
        RLock lock = redissonClient.getLock(getLockKey(key));
        log.debug("Trying lock for user sign up : {}", key);

        try {
            boolean isLock = lock.tryLock(1, 15, TimeUnit.SECONDS);
            if (!isLock) {
                log.error("=====Lock acquisition failed=====");
                throw new EmployeeException(EMPLOYEE_TRANSACTION_LOCK);
            }
        } catch (EmployeeException e) {
            throw e;
        } catch (Exception e) {
            log.error("Redis lock failed", e);
        }
    }

    public void unlock(String key) {
        log.debug("Unlock for user sign up : {}", key);
        redissonClient.getLock(getLockKey(key)).unlock();
    }

    private String getLockKey(String key) {
        return "ACLK:" + key;
    }

}
