package de.adesso.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class IntegerSerializer implements
		RedisSerializer<Integer> {
	@Override
	public byte[] serialize(Integer value)
			throws SerializationException {
		if (value==null) return null;
		return new byte[] { (byte) (value >>> 24),
				(byte) (value >>> 16), (byte) (value >>> 8),
				(byte) ((int) value) };
	}

	@Override
	public Integer deserialize(byte[] b)
			throws SerializationException {
		if (b==null) return null;
		return (b[0] << 24)
	            + ((b[1] & 0xFF) << 16)
	            + ((b[2] & 0xFF) << 8)
	            + (b[3] & 0xFF);
	}
}