package de.adesso.redis;

import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;


public class RedisCreateID {
	
	@Test
	public  void TestCreateID() {
		StringRedisTemplate redisTemplate = RedisHelper.setupRedisStringTemplate();
		System.out.println(redisTemplate.opsForValue().increment("playerid", 1));
	}

}
