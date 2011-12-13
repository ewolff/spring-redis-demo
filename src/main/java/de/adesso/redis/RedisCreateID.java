package de.adesso.redis;

import org.springframework.data.redis.core.StringRedisTemplate;


public class RedisCreateID {
	
	public static void main(String[] args) {
		StringRedisTemplate redisTemplate = RedisHelper.setupRedisStringTemplate();
		System.out.println(redisTemplate.opsForValue().increment("playerid", 1));
	}

}
