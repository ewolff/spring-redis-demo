package com.ewolff.redis;

import java.util.concurrent.CountDownLatch;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisEMailReaderMessagingPOJO {

	private boolean called = false;
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public void reset() {
		called = false;
		countDownLatch = new CountDownLatch(1);
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public boolean isCalled() {
		return called;
	}

	public void handleMessage(String msg) {
		System.out.println(msg);
		called = true;
		countDownLatch.countDown();
	}

}
