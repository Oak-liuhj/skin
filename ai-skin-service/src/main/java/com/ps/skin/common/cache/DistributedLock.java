package com.ps.skin.common.cache;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Redisson分布式锁
 *
 * @author liuhj
 * @date 2020/05/19 13:45
 */
public interface DistributedLock {
    /**
     * 加锁
     *
     * @param lockKey key
     * @return
     */
    boolean lock(String lockKey);

    /**
     * 加有效时间锁
     *
     * @param lockKey key
     * @param timeout 有效时间
     * @return
     */
    RLock lock(String lockKey, int timeout);

    /**
     * Redis锁，有效时间
     *
     * @param lockKey key
     * @param unit    时间单位
     * @param timeout 失效时间
     * @return
     */
    RLock lock(String lockKey, TimeUnit unit, int timeout);

    /**
     * 尝试加锁
     *
     * @param lockKey   key
     * @param unit      时间单位
     * @param waitTime  等待时间
     * @param leaseTime 自动释放锁时间
     * @return
     */
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    /**
     * 解锁
     *
     * @param lockKey key
     */
    void unlock(String lockKey);

    /**
     * 解锁
     *
     * @param lock redis
     */
    void unlock(RLock lock);

}
