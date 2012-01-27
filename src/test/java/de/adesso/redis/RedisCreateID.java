package de.adesso.redis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;


public class RedisCreateID {
	
	@Test
	public  void TestCreateID() {
		StringRedisTemplate redisTemplate = RedisHelper.setupRedisStringTemplate();
		long playerid = redisTemplate.opsForValue().increment("playerid", 1);
		System.out.println(playerid);
		Assert.assertEquals(playerid+1, (long)redisTemplate.opsForValue().increment("playerid", 1));
	}

}
