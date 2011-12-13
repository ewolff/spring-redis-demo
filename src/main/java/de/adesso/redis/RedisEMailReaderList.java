package de.adesso.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisEMailReaderList {

	public static void main(String[] args) {
		StringRedisTemplate redisTemplate = RedisHelper
				.setupRedisStringTemplate();
		for (;;) {
			System.out.println(redisTemplate.opsForList().leftPop("emailList",
					100000, TimeUnit.MILLISECONDS));
		}
	}

}
