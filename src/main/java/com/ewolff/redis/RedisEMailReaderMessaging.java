package de.adesso.redis;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisEMailReaderMessaging {

	public void handleMessage(String msg) {
		System.out.println(msg);
	}

	public static void main(String[] args) {
		RedisMessageListenerContainer listenerContainer = RedisHelper
				.setupRedisListenerContainer();
		MessageListenerAdapter listener = new MessageListenerAdapter();
		listener.setDelegate(new RedisEMailReaderMessaging());
		listener.setDefaultListenerMethod("handleMessage");
		listener.setSerializer(new StringRedisSerializer());
		listener.afterPropertiesSet();
		listenerContainer.addMessageListener(listener, new ChannelTopic(
				"emails"));
		listenerContainer.afterPropertiesSet();
		listenerContainer.start();
	}

}
