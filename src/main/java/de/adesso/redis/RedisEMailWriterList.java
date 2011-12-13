package de.adesso.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StopWatch;

public class RedisEMailWriterList {

	public static void main(String[] args) {
		StringRedisTemplate redisTemplate = RedisHelper
				.setupRedisStringTemplate();
		StopWatch stopWatch = new StopWatch();
		redisTemplate.delete("emailList");
		for (int i = 0; i < 100; i++) {
			redisTemplate.opsForList()
					.rightPush("emailList", "some email " + i);
		}
	}

}
