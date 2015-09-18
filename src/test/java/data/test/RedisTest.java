package data.test;
/**
 * Created by renato on 17/09/15.
 */

import data.config.RedisConfig;
import data.factory.RedisTemplateFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class RedisTest {

    @Autowired
    private RedisTemplateFactory templateFactory;

    @Test
    public void test01() {

        Map<String, String> params = new HashMap<>();
        params.put("A", "http://projects.spring.io/spring-data-redis/");
        params.put("B", "http://projects.spring.io/spring-data-redis/");
        params.put("C", "http://projects.spring.io/spring-data-redis/");
        params.put("D", "http://projects.spring.io/spring-data-redis/");
        params.put("E", "http://projects.spring.io/spring-data-redis/");
        params.put("F", "http://projects.spring.io/spring-data-redis/");
        params.put("G", "http://projects.spring.io/spring-data-redis/");
        params.put("H", "http://projects.spring.io/spring-data-redis/");
        params.put("I", "http://projects.spring.io/spring-data-redis/");

        templateFactory.multiSet(5, params);
    }
}
