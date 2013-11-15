package de.adesso.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisEMailWriterMessaging {

	public static void main(String[] args) {
		StringRedisTemplate redisTemplate = RedisHelper
				.setupRedisStringTemplate();
		for (int i = 0; i < 100; i++) {
			redisTemplate.convertAndSend("emails","some email " + i);
		}
	}

}
