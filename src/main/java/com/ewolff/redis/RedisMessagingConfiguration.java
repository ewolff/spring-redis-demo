package com.ewolff.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

@Configuration
public class RedisMessagingConfiguration {

	@Bean
	public StringRedisTemplate redisStringTemplate(
			JedisConnectionFactory jedisConnectionFactory) {
		return new StringRedisTemplate(jedisConnectionFactory);
	}

	@Bean
	public JedisConnectionFactory createConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setShardInfo(new JedisShardInfo("localhost",
				6379));
		jedisConnectionFactory.setPoolConfig(new JedisPoolConfig());
		return jedisConnectionFactory;
	}

	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(
			RedisConnectionFactory jedisConnectionFactory,
			MessageListenerAdapter messageListenerAdapter) {
		RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
		redisMessageListenerContainer
				.setConnectionFactory(jedisConnectionFactory);
		redisMessageListenerContainer.addMessageListener(
				messageListenerAdapter, new ChannelTopic("emails"));
		return redisMessageListenerContainer;
	}

	@Bean
	public MessageListenerAdapter messageListenerAdapter(
			RedisEMailReaderMessagingPOJO redisEMailReaderMessaging) {
		MessageListenerAdapter listener = new MessageListenerAdapter();
		listener.setDelegate(redisEMailReaderMessaging);
		listener.setDefaultListenerMethod("handleMessage");
		listener.setSerializer(new StringRedisSerializer());
		return listener;
	}

	@Bean
	public RedisEMailReaderMessagingPOJO redisEMailReaderMessaging() {
		return new RedisEMailReaderMessagingPOJO();
	}
}
