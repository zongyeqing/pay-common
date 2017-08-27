package com.zong.paycommon.utils.cache.redis.springImpl;

import com.zong.paycommon.utils.cache.redis.RedisUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Component;

/**
 * 根据SPring API 自定义一个缓存类 ，实现Redis 缓存。<br/>
 * @author 宗叶青 on 2017/8/15/0:25
 */

@Component("redisCache")
public class RedisCache implements Cache {

    private String name;

    public RedisUtils  cache = new RedisUtils();

    /**
     * 清空所有的缓存
     */
    public void clear() {
        cache.flushAll();
    }

    @Override
    public void evict(Object key) {
        cache.del(key);
    }

    /**
     * 根据Key值获得缓存数据
     */
    public Cache.ValueWrapper get(Object key) {
        Cache.ValueWrapper result = null;
        Object thevalue = cache.get(key);
        if (thevalue != null) {
            result = new SimpleValueWrapper(thevalue);
        }
        return result;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    /**添加缓存*/
    public void put(Object arg0, Object arg1) {
        cache.save(arg0, arg1,20000);
    }

    public RedisCache() {
    }

    public RedisCache(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
