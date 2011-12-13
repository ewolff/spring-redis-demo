package de.adesso.redis;

import org.junit.Assert;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StopWatch;


public class RedisWriter {
	
	private static final int dataSets = 100000;

	public static void main(String[] args) {
		RedisTemplate<String, Integer> redisTemplate = RedisHelper.setupRedisTemplate();
		StopWatch stopWatch = new StopWatch();
		for (int i = 0; i < dataSets; i++) {
			String key = "player" + i;
			stopWatch.start();
			redisTemplate.opsForValue().set(key, i);
			stopWatch.stop();
			if ((i % 1000) == 0) {
				System.out.println(i);
			}
		}
		System.out.println(stopWatch.shortSummary());
		Assert.assertEquals(42, (int) redisTemplate.opsForValue().get("player42"));
		System.out.println("ops per second:"+((dataSets*1000)/(stopWatch.getTotalTimeMillis())));
	}


}
