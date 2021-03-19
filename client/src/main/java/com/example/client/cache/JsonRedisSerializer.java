package com.example.client.cache;

import com.example.client.model.CacheType;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class JsonRedisSerializer implements RedisSerializer<Object> {

  private final ObjectMapper objectMapper;

  public JsonRedisSerializer() {
    final BasicPolymorphicTypeValidator typeValidator =
        BasicPolymorphicTypeValidator.builder().allowIfSubType(CacheType.class).build();
    this.objectMapper =
        JsonMapper.builder()
            .activateDefaultTyping(typeValidator, DefaultTyping.NON_FINAL, As.PROPERTY)
            .build();
  }

  @Override
  public byte[] serialize(Object t) throws SerializationException {
    try {
      return objectMapper.writeValueAsBytes(t);
    } catch (JsonProcessingException e) {
      throw new SerializationException(e.getMessage(), e);
    }
  }

  @Override
  public Object deserialize(byte[] bytes) throws SerializationException {
    if (bytes == null) {
      return null;
    }

    try {
      return objectMapper.readValue(bytes, Object.class);
    } catch (Exception e) {
      throw new SerializationException(e.getMessage(), e);
    }
  }
}
