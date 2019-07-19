package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
@EnableScheduling
@Component
public class ScheduleTask {
    public static void main(String[] args){
        System.out.println("2018-09-14".substring(0,7));
    }

    @Scheduled(cron = "0 0 */1 * * ? ")
    public void printSay() {

        String lockKey = "test";
        ReentrantLock lock = null;
        try {
            lock = JobLock.acquireLock(lockKey);
            if (lock.tryLock()) {
                log.info("This is a say method!"+new Date());
                System.out.println("This is a say method!"+new Date());
            } else {
                log.warn("Job[{}]正在执行，不能重复执行！", lockKey);
            }
        } catch (Exception e) {
        } finally {
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("Job[{}]释放锁{}", lockKey, lock);
            }
        }
    }

    /**
     * Job锁，防止job被重复调度执行，仅适用于单机场景
     *
     * @author liuzhe
     * @date 2018年4月3日
     */
    private static final class JobLock {

        /**
         * 锁缓存
         */
        private static final Map<String, ReentrantLock> LOCK_CACHE = new ConcurrentHashMap<>();

        /**
         * 获取锁
         *
         * @param lockKey
         * @return
         */
        public static ReentrantLock acquireLock(String lockKey) {
            String key = StringUtils.trim(lockKey);
            ReentrantLock lock = LOCK_CACHE.get(key);
            if (lock == null) {
                synchronized (LOCK_CACHE) {
                    lock = LOCK_CACHE.get(key);
                    if (lock == null) {
                        lock = new ReentrantLock();
                        LOCK_CACHE.putIfAbsent(key, lock);
                    }
                }
            }
            return lock;
        }

    }
}