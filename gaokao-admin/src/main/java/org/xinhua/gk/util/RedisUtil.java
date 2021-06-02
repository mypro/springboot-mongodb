package org.xinhua.gk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 暂时取消redis使用
 */
@Component
public class RedisUtil {


    @Autowired
    private  RedisTemplate redisTemplate;



    /**
     * 写入字符
     *
     * @param key
     * @param value
     */
    public  void stringAdd(String key, String value) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(key, value);
    }

    /**
     * 写入字符
     *
     * @param key
     * @param value
     * @param cacheSecond 缓存时间单位（秒）
     */
    public  void stringAdd(String key, String value,long cacheSecond) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(key, value,cacheSecond,TimeUnit.SECONDS);
    }
    /**
     * 获取字符
     *
     * @param key
     * @return
     */
    public  String stringGet(String key) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(key);
    }

    public  Set<String> keys(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        return keys;
    }

    /**
     * key的值+1
     *
     * @param key
     * @return
     */
    public  int valueIncr(String key) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return Long.valueOf(valueOps.increment(key, 1)).intValue();
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param liveTime
     */
    public  void setExpire(byte[] key, long liveTime) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * key是否存在
     *
     * @param key
     * @return
     */
    public  boolean exists(String key) {
        return (boolean) redisTemplate.execute(new RedisCallback<Object>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    /**
     * 左侧写入list
     *
     * @param key
     * @param value
     */
    public  Long listLeftAdd(String key, String value) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.leftPush(key, value);
    }

    /**
     * 右侧取出并移除
     *
     * @param key
     * @return
     */
    public  String listRightGet(String key) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.rightPop(key);
    }

    /**
     * 获取list长度
     *
     * @param key
     * @return
     */
    public  long listSize(String key) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.size(key);
    }

    /**
     * 获取list
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public  List<String> listGet(String key, int start, int end) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.range(key, start, end);
    }

    /**
     * 通过索引获取list中元素
     *
     * @param key
     * @param index
     * @return
     */
    public  String listGetByIndex(String key, int index) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.index(key, index);
    }

    /**
     * 通过索引来设置元素的值
     *
     * @param key
     * @param index
     * @param value
     */
    public  void listSetByIndex(String key, int index, String value) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        listOps.set(key, index, value);
    }

    /**
     * 从头开始删除count个符合value的元素
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    public  long listLrem(String key, int count, String value) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.remove(key, count, value);
    }

}
