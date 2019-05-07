package cn.waynezw.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-21 11:32
 * @Description: Redis 实现分布式锁
 */

@Component
@Slf4j
public class DistributedLockByRedis {
    @Autowired
    private StringRedisTemplate redisTemplate;


    public boolean lock(String key, String value) {
      return true;
    }
}
