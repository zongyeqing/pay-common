package com.zong.paycommon.utils.cache.redis.springImpl;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

/**
 * @author 宗叶青 on 2017/8/15/0:24
 */
public class CacheManager extends AbstractCacheManager {

    private Collection<? extends RedisCache> caches;

    public void setCaches(Collection<? extends RedisCache> caches){
        this.caches = caches;
    }
    @Override
    protected Collection<? extends Cache> loadCaches() {
        return this.caches;
    }
}
