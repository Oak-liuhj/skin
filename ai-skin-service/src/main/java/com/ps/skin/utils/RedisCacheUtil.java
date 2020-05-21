package com.ps.skin.utils;

import com.alibaba.fastjson.JSONObject;
import com.ps.skin.common.cache.RedissonLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存工具类
 *
 * @author liuhj
 * @date 2020/05/19 13:22
 */
@Service
public class RedisCacheUtil<T> {

    private final static String RedisCacheUtilKeyPre = "RedisCacheUtilKeyPre_";
    private final static String RedisCacheUtilLockKeyPre = "RedisCacheUtilLockKeyPre_";
    @Autowired
    private RedissonLock redissonLock;
    @Autowired
    private RedisTemplate redisTemplate;


    public T get(String key, Long expire, Class deskClass, Cacheables cacheables) {
        return this.getCacheObject(key, expire, deskClass, false, cacheables);
    }

    /**
     * 获取缓存数据对象
     *
     * @param key
     * @param expire    过期时间/秒
     * @param deskClass 对应的类型
     * @param cacheables
     * @return
     */
    public T getList(String key, Long expire, Class deskClass, Cacheables cacheables) {
        return this.getCacheObject(key, expire, deskClass, true, cacheables);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void del(String key) {
        key = this.makeRedisKey(key);
        redisTemplate.delete(key);
    }

    /**
     * 获取缓存数据 如果无缓存 将会从Cacheable中获取 Cacheable需要自己提供数据
     *
     * @param key
     * @param expire    过期时间/秒
     * @param deskClass 对应的类型
     * @param isList    是否List对象
     * @param cacheables 缓存参数接口
     * @return
     */
    public T getCacheObject(String key, Long expire, Class deskClass, boolean isList, Cacheables cacheables) {
        key = this.makeRedisKey(key);
        String lockKey = RedisCacheUtil.RedisCacheUtilLockKeyPre + key + "_";
        T obj = null;
        //加锁防止缓存击穿
        boolean isLock = redissonLock.lock(lockKey);
        try {
            if (isLock) {
                if (isList) {
                    obj = this.getCacheList(key, deskClass);
                } else {
                    obj = this.getCacheObject(key, deskClass);
                }
                if (null == obj) {
                    obj = (T) cacheables.getData();
                    if (!(obj instanceof List) && isList) {
                        throw new RuntimeException("当前Cacheable接口对应实现的getData返回值必须为List，不能是单个对象");
                    }
                    if (obj instanceof List && !isList) {
                        throw new RuntimeException("当前Cacheable接口对应实现的getData返回值必须为单个对象，不能是List");
                    }
                    this.set(key, obj, expire);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (isLock) {
                redissonLock.unlock(lockKey);
            }
            return obj;
        }
    }


    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值 对象集合等要用fastJosn转成String
     * @return 缓存的对象
     */
    public void set(String key, T value, Long expire) {
        key = this.makeRedisKey(key);
        String redisValue = null;
        if (null == value) {
            return;
        }
        if (value instanceof String) {
            redisValue = (String) value;
        } else {
            String objValue = JSONObject.toJSON(value).toString();
            redisValue = objValue;
        }
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        operation.set(key, redisValue);
        if (null != expire) {
            this.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public String get(String key) {
        key = this.makeRedisKey(key);
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 获取缓存对象：对应类型
     *
     * @param key
     * @param deskClass
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(String key, Class deskClass) {
        key = this.makeRedisKey(key);
        String redisValue = this.get(key);
        if (null == redisValue) {
            return null;
        }
        T redisObj = (T) JSONObject.parseObject(redisValue, deskClass);
        return redisObj;
    }

    /***
     * 获取缓存数组对象：对应类型
     * @param key
     * @param deskClass
     * @param <T>
     * @return
     */
    public <T> T getCacheList(String key, Class deskClass) {
        key = this.makeRedisKey(key);
        String redisValue = this.get(key);
        if (null == redisValue) {
            return null;
        }
        T redisObj = (T) JSONObject.parseArray(redisValue, deskClass);
        return redisObj;
    }

    /**
     * 构造key
     *
     * @param key
     * @return
     */
    private String makeRedisKey(String key) {
        if (null == key) {
            throw new RuntimeException("key不能为空");
        }
        if (key.contains(RedisCacheUtil.RedisCacheUtilKeyPre)) {
            return key;
        } else {
            return RedisCacheUtil.RedisCacheUtilKeyPre + key;
        }
    }
}
