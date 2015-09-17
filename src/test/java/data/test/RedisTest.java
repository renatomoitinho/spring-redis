package data.test;
/**
 * Created by renato on 17/09/15.
 */

import data.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    @Test public void test01() {
      //  redisTemplate.boundListOps("abc").leftPush("http://projects.spring.io/spring-data-redis/");
      // TemplateBuilder.newTemplate(url,index)


        redisTemplate.opsForValue().append("A", "http://projects.spring.io/spring-data-redis/");
        redisTemplate.opsForValue().append("B", "http://projects.spring.io/spring-data-redis/");
        redisTemplate.opsForValue().append("C", "http://projects.spring.io/spring-data-redis/");
        redisTemplate.opsForValue().append("D", "http://projects.spring.io/spring-data-redis/");
        redisTemplate.opsForValue().append("E", "http://projects.spring.io/spring-data-redis/");

    }

    @Test public void test02(){
        listOps.leftPush("123", "http://projects.spring.io/spring-data-redis/");
    }

}
