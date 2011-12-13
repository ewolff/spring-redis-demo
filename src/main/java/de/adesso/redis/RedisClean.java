package de.adesso.redis;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisClean {

	public static void main(String[] args) {
		RedisTemplate<String, Integer> redisTemplate = RedisHelper.setupRedisTemplate();
		for (int i = 0; i < 100000; i++) {
			redisTemplate.delete("player" + i);
			if ((i % 1000) == 0) {
				System.out.println(i);
			}
		}
	}

}
