package de.adesso.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisEMailWriterList {

	public static void main(String[] args) {
		StringRedisTemplate redisTemplate = RedisHelper
				.setupRedisStringTemplate();
		redisTemplate.delete("emailList");
		for (int i = 0; i < 100; i++) {
			redisTemplate.opsForList()
					.rightPush("emailList", "some email " + i);
		}
	}

}
