package de.adesso.redis;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.springframework.data.redis.core.StringRedisTemplate;


public class RedisCache {

	public static void main(String[] args) throws Exception {
		StringRedisTemplate redisTemplate = RedisHelper.setupRedisStringTemplate();
		String text="Lots and lots of text";
		redisTemplate.opsForValue().set("cachekey", text, 1000, TimeUnit.MILLISECONDS);
		Assert.assertEquals(text, redisTemplate.opsForValue().get("cachekey"));
		Thread.sleep(1500);
		Assert.assertNull(redisTemplate.opsForValue().get("cachekey"));
	}
	
}
