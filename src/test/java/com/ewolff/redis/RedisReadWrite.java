package com.ewolff.redis;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = RedisTemplateConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisReadWrite {

	@Autowired
	RedisTemplate<String, Integer> redisTemplate;

	@Before
	public void setUp() {
		redisTemplate.delete("player42|gauntlet");
		redisTemplate.delete("player42|pacman");
		assertNull(redisTemplate.opsForValue().get("player42|gauntlet"));
		assertNull(redisTemplate.opsForValue().get("player42|pacman"));
	}

	@Test
	public void readWrite() {
		redisTemplate.opsForValue().set("player42|gauntlet", 42);
		redisTemplate.opsForValue().set("player42|pacman", 23);
		assertEquals(42,
				(int) redisTemplate.opsForValue().get("player42|gauntlet"));
		assertEquals(23,
				(int) redisTemplate.opsForValue().get("player42|pacman"));
		redisTemplate.keys("player42|*");
		assertThat(redisTemplate.keys("player42|*"),
				containsInAnyOrder("player42|gauntlet", "player42|pacman"));
	}

}
