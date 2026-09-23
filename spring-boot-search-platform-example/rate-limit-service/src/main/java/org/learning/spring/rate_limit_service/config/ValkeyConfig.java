package org.learning.spring.rate_limit_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ValkeyConfig {

    @Bean
    public ReactiveRedisTemplate<String, Boolean> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        GenericToStringSerializer valueSerializer = new GenericToStringSerializer<>(Boolean.class);


        RedisSerializationContext serializationContext = RedisSerializationContext
                .<String, Boolean>newSerializationContext()
                .key(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
                .value(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer))
                .hashKey(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
                .hashValue(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
                .build();

        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);
    }
}
