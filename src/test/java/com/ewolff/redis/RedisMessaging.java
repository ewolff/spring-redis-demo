package com.ewolff.redis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisMessagingConfiguration.class)
public class RedisMessaging {

	@Autowired
	RedisEMailReaderMessagingPOJO eMailReaderMessaging;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Before
	public void setUp() {
		eMailReaderMessaging.reset();
	}

	@Test(timeout = 2000)
	public void receive() throws InterruptedException {
		assertFalse(eMailReaderMessaging.isCalled());
		stringRedisTemplate.convertAndSend("emails", "messaging");
		eMailReaderMessaging.getCountDownLatch().await();
		assertTrue(eMailReaderMessaging.isCalled());
	}

}
