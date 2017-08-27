package com.zong.paycommon.utils.cache.redis;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * redis 缓存工具
 * @author 宗叶青 on 2017/8/14/23:06
 */
public class RedisUtils {

    private static final Logger LOGGER = Logger.getLogger(RedisUtils.class);

    /**默认缓存时间*/
    private static final int DEFAULT_CACHE_SECONDS = 60 * 60;

    /**连接池*/
    private static JedisSentinelPool jedisSentinelPool;

    /**
     * 释放redis资源
     * @param jedis jedis对象
     */
    private static void releaseResource(Jedis jedis){
        if(jedis != null){
            jedisSentinelPool.returnResource(jedis);
        }
    }

    /**
     * 删除redis中的所有key
     */
    public static void flushAll(){
        Jedis jedis = null;
        try{
            jedis = jedisSentinelPool.getResource();
            jedis.flushAll();
        }catch (Exception e){
            LOGGER.error("cache清空失败 ：" + e);
        }finally {
            releaseResource(jedis);
        }
    }

    /**
     * 使用默认过期时间缓存对象
     * @param key 键
     * @param object 缓存对象
     * @return 结果
     */
    public static Boolean save(Object key, Object object){
        return save(key, object, DEFAULT_CACHE_SECONDS);
    }

    /**
     * 缓存对象
     * @param key
     * @param object
     * @param seconds
     * @return
     */
    public static Boolean save(Object key, Object object, int seconds){
        Jedis jedis = null;
        try{
            jedis = jedisSentinelPool.getResource();
            jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(object));
            jedis.expire(SerializeUtils.serialize(key), seconds);
            return true;
        }catch (Exception e){
            LOGGER.error("cache保存失败： " + e);
            return false;
        }finally{
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存获取redis缓存中的值
     * @param key 键
     * @return
     */
    public static Object get(Object key){
        Jedis jedis = null;
        try{
            jedis = jedisSentinelPool.getResource();
            byte[] obj = jedis.get(SerializeUtils.serialize(key));
            return obj == null ? null : SerializeUtils.unSerialize(obj);
        }catch (Exception e){
            LOGGER.error("cache获取失败：" + e);
            return null;
        }finally{
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static Boolean del(Object key) {
        Jedis jedis = null;
        try {
            // System.out.println(key);
            jedis = jedisSentinelPool.getResource();
            jedis.del(SerializeUtils.serialize(key));
            return true;
        } catch (Exception e) {
            LOGGER.error("Cache删除失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param keys
     * @return
     * @throws Exception
     */
    public static Boolean del(Object... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.del(SerializeUtils.serialize(keys));
            return true;
        } catch (Exception e) {
            LOGGER.error("Cache删除失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     *
     * @param key
     * @param seconds
     *            超时时间（单位为秒）
     * @return
     */
    public static Boolean expire(Object key, int seconds) {

        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.expire(SerializeUtils.serialize(key), seconds);
            return true;
        } catch (Exception e) {
            LOGGER.error("Cache设置超时时间失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 添加一个内容到指定key的hash中
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Boolean addHash(String key, Object field, Object value){
        Jedis jedis = null;
        try{
            jedis = jedisSentinelPool.getResource();
            jedis.hset(SerializeUtils.serialize(key), SerializeUtils.serialize(field), SerializeUtils.serialize(value));
            return true;
        } catch (Exception e) {
            LOGGER.error("Cache保存失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 从指定hash中拿一个对象
     *
     * @param key
     * @param field
     * @return
     */
    public static Object getHash(Object key, Object field) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            byte[] obj = jedis.hget(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
            return SerializeUtils.unSerialize(obj);
        } catch (Exception e) {
            LOGGER.error("Cache读取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 从hash中删除指定filed的值
     *
     * @param key
     * @param field
     * @return
     */
    public static Boolean delHash(Object key, Object field) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            long result = jedis.hdel(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
            return result == 1 ? true : false;
        } catch (Exception e) {
            LOGGER.error("Cache删除失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 拿到缓存中所有符合pattern的key
     *
     * @param pattern
     * @return
     */
    public static Set<byte[]> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            Set<byte[]> allKey = jedis.keys(("*" + pattern + "*").getBytes());
            return allKey;
        } catch (Exception e) {
            LOGGER.error("Cache获取失败：" + e);
            return new HashSet<byte[]>();
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 获得hash中的所有key value
     *
     * @param key
     * @return
     */
    public static Map<byte[], byte[]> getAllHash(Object key) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            Map<byte[], byte[]> map = jedis.hgetAll(SerializeUtils.serialize(key));
            return map;
        } catch (Exception e) {
            LOGGER.error("Cache获取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 判断一个key是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exists(Object key) {
        Jedis jedis = null;
        Boolean result = false;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.exists(SerializeUtils.serialize(key));
            return result;
        } catch (Exception e) {
            LOGGER.error("Cache获取失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    public void setJedisSentinelPool(JedisSentinelPool jedisSentinelPool) {
        RedisUtils.jedisSentinelPool = jedisSentinelPool;
    }

    public static JedisSentinelPool getJedisSentinelPool() {
        return jedisSentinelPool;
    }

}
