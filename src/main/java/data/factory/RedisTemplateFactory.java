package data.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RedisTemplateFactory {

    private static byte[] OK = {1};

    private static String[][] redisList = {
            {"127.0.0.1", "5000"},
            {"127.0.0.1", "5001"},
            {"127.0.0.1", "5002"},
            {"127.0.0.1", "5003"},
            {"127.0.0.1", "5004"},
            {"127.0.0.1", "5005"},
            {"127.0.0.1", "5006"},
            {"127.0.0.1", "5009"}
    };

    @Autowired
    private ApplicationContext applicationContext;

    private Map<Integer, List<RedisTemplate<String, String>>> databases = new HashMap<>();

    public void multiSet(int database, final Map<String, String> dictionary) {
        List<RedisTemplate<String, String>> redisTemplates = redisTemplates(database);
        for (RedisTemplate<String, String> template : redisTemplates) {
            template.opsForValue().multiSet(dictionary);
        }
    }

    public List<RedisTemplate<String, String>> redisTemplates(int database) {
        if (databases.containsKey(database)) {
            return databases.get(database);
        }
        List<RedisTemplate<String, String>> redisTemplates = redisTemplateList(database);
        databases.put(database, redisTemplates);
        return redisTemplates;
    }

    private RedisConnectionFactory redisConnectionFactory(String host, int port, int database) throws RedisConnectionFailureException {
        return applicationContext.getBean(RedisConnectionFactory.class, host, port, database);
    }

    private List<RedisTemplate<String, String>> redisTemplateList(int database) {
        List<RedisTemplate<String, String>> redisTemplates = new ArrayList<>();
        for (String[] strings : redisList) {
            RedisConnectionFactory redisConnectionFactory = redisConnectionFactory(strings[0], Integer.valueOf(strings[1]), database);
            if (!isConnection(redisConnectionFactory)) {
                continue;
            }
            redisTemplates.add(redisTemplate(redisConnectionFactory));
        }
        return redisTemplates;
    }

    private boolean isConnection(RedisConnectionFactory redisConnectionFactory) {
        try {
            RedisConnection connection = redisConnectionFactory.getConnection();
            return Arrays.equals(OK, connection.echo(OK));
        } catch (RedisConnectionFailureException e) {
            return false;
        }
    }

    private RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return applicationContext.getBean(RedisTemplate.class, redisConnectionFactory);
    }

    public void close(int database) {
        if (!databases.containsKey(database)) {
            return;
        }
        databases.get(database).forEach((template) -> template.getConnectionFactory().getConnection().close());
    }

}
