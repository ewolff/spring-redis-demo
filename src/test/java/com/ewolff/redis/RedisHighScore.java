package de.adesso.redis;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

public class RedisHighScore {

	private static final int dataSets = 100000;

	@Test
	public void testHighScore() {
		StringRedisTemplate redisTemplate = RedisHelper
				.setupRedisStringTemplate();
		redisTemplate.delete("highscore");
		for (int i = 0; i < dataSets; i++) {
			redisTemplate.opsForZSet().add("highscore", "player" + i, i);
			if ((i % 1000) == 0) {
				System.out.println(i);
			}
		}
		Set<String> result = redisTemplate.opsForZSet().rangeByScore(
				"highscore", 99998.5, 100001.0);
		Assert.assertEquals(1, result.size());
		Assert.assertEquals("player99999", result.iterator().next());

		Set<TypedTuple<String>> topscores = redisTemplate.opsForZSet()
				.reverseRangeWithScores("highscore", 0, 10);
		for (TypedTuple<String> score : topscores) {
			System.out.println(score.getValue() + " " + score.getScore());
		}
	}

}
