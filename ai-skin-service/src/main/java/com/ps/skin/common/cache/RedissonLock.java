package com.ps.skin.common.cache;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Redisson分布式锁工具类
 *
 * @author liuhj
 * @date 2020/05/19 15:15
 */
public class RedissonLock {

    private static DistributedLock distributedLock;

    public static void setDistributedLock(DistributedLock lock) {
        distributedLock = lock;
    }

    /**
     * 加锁
     *
     * @param lockKey
     * @return
     */
    public boolean lock(String lockKey) {
        return distributedLock.lock(lockKey);
    }

    /**
     * 释放锁
     *
     * @param lockKey
     */
    public void unlock(String lockKey) {
        distributedLock.unlock(lockKey);
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public void unlock(RLock lock) {
        distributedLock.unlock(lock);
    }

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param timeout 超时时间 单位：秒
     */
    public RLock lock(String lockKey, int timeout) {
        return distributedLock.lock(lockKey, timeout);
    }

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param unit    时间单位
     * @param timeout 超时时间
     */
    public RLock lock(String lockKey, TimeUnit unit, int timeout) {
        return distributedLock.lock(lockKey, unit, timeout);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        return distributedLock.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        return distributedLock.tryLock(lockKey, unit, waitTime, leaseTime);
    }
}
