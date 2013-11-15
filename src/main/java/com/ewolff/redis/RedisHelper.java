package de.adesso.redis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

public class RedisHelper {

	private static JedisConnectionFactory jedisConnectionFactory;
	
	protected static RedisMessageListenerContainer setupRedisListenerContainer() {
		RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
		listenerContainer.setConnectionFactory(createConnectionFactory());
		
		return listenerContainer;
	}

	protected static RedisTemplate<String, Integer> setupRedisTemplate() {
		RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<String, Integer>();
		redisTemplate.setValueSerializer(new IntegerSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(createConnectionFactory());
		return redisTemplate;
	}

	protected static StringRedisTemplate setupRedisStringTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate(
				createConnectionFactory());
		return redisTemplate;
	}

	private synchronized static JedisConnectionFactory createConnectionFactory() {
		if (jedisConnectionFactory == null) {
			jedisConnectionFactory = new JedisConnectionFactory(
					new JedisPoolConfig());
			jedisConnectionFactory.setShardInfo(new JedisShardInfo("localhost",
					6379));
			jedisConnectionFactory.afterPropertiesSet();
		}
		return jedisConnectionFactory;
	}
	
	

}
