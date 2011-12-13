package de.adesso.redis;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StopWatch;

public class RedisMultiWriter {

	private static final int dataSets = 100000;

	
	public static void main(String[] args) {
		RedisTemplate<String, Integer> redisTemplate = RedisHelper.setupRedisTemplate();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < dataSets; i++) {
			map.put("player" + i, i);
		}

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		redisTemplate.opsForValue().multiSet(map);
		stopWatch.stop();
		System.out.println(stopWatch.shortSummary());
		System.out.println("ops per second:"+((dataSets*1000)/(stopWatch.getTotalTimeMillis())));
		Assert.assertEquals(42, (int) redisTemplate.opsForValue().get("player42"));

	}

}
