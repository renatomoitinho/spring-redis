package data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisShardInfo;


@Configuration
@ComponentScan(basePackages = "data")
public class RedisConfig {

    @Bean
    @Scope("prototype")
    public RedisConnectionFactory redisConnectionFactory(String host, int port, int database) {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(new JedisShardInfo(host, port));
        connectionFactory.setUsePool(true);
        connectionFactory.afterPropertiesSet();
        connectionFactory.setDatabase(database);
        return connectionFactory;
    }

    @Bean
    @Scope("prototype")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
