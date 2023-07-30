package com.redis.caching.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    @Qualifier("Quick1")
    public RedisTemplate<String, Object> template1() {
        RedisTemplate<String, Object> template2 = new RedisTemplate<>();
        template2.setConnectionFactory(connectionFactory());
        template2.setKeySerializer(new StringRedisSerializer());
        template2.setHashKeySerializer(new StringRedisSerializer());
        template2.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template2.setValueSerializer(new JdkSerializationRedisSerializer());
        template2.setEnableTransactionSupport(true);
        template2.afterPropertiesSet();
        return template2;
    }

}
