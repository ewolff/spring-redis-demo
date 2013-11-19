package com.ewolff.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

@Configuration
public class RedisTemplateConfiguration {

	@Bean
	public RedisTemplate<String, Integer> redisTemplate(
			JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<String, Integer>();
		redisTemplate.setValueSerializer(new IntegerSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		return redisTemplate;
	}

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

}
