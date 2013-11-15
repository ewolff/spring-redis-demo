package de.adesso.redis;

import org.junit.Test;

import junit.framework.Assert;

public class IntegerSerializerTest {
	
	@Test
	public void testSerializer() {
		IntegerSerializer integerSerializer = new IntegerSerializer();
		Assert.assertEquals(0,  (int) integerSerializer.deserialize(integerSerializer.serialize(0)));
		Assert.assertEquals(1,  (int) integerSerializer.deserialize(integerSerializer.serialize(1)));
		Assert.assertEquals(42,  (int) integerSerializer.deserialize(integerSerializer.serialize(42)));
		Assert.assertEquals(0xABC12345,  (int) integerSerializer.deserialize(integerSerializer.serialize(0xABC12345)));
		Assert.assertNull(integerSerializer.deserialize(null));
		Assert.assertNull(integerSerializer.serialize(null));
	}

}
