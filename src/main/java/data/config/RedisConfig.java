package data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by renato on 17/09/15.
 */
@Configuration
@ComponentScan(basePackages = "data")
public class RedisConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName("localhost");
        cf.setPort(6379);
        cf.setUsePool(true);
        cf.afterPropertiesSet();
        cf.setDatabase(1);
        return cf;
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate rt = new RedisTemplate();
        rt.setConnectionFactory(redisConnectionFactory());
        rt.setDefaultSerializer(new StringRedisSerializer());
        return rt;
    }
}
