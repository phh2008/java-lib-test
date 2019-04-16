package com.phh.test.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.junit.Test;

import java.time.LocalDate;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test
 * @date 2019/2/19
 */
public class LettuceTest {

    private RedisClient client = RedisClient.create(
            RedisURI.builder()
                    .withHost("192.168.1.227")
                    .withPort(6379)
                    .withPassword("redis2")
                    .build());

    @Test
    public void baseUsage() {
        //RedisClient client = RedisClient.create("redis://redis2@192.168.1.227:6379/1");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisStringCommands sync = connection.sync();
        sync.set("test", "abc321");
        String value = (String) sync.get("test");
        System.out.println("value:::" + value);
    }


    @Test
    public void codec() {
        StatefulRedisConnection<String, Object> connection = client.connect(new SerializedObjectCodec());
        RedisCommands<String, Object> sync = connection.sync();
        Person person = new Person(10, "", LocalDate.of(1987, 3, 6));
        String ret = sync.set("person", person);
        System.out.println("set return:::" + ret);
        Person cache = (Person) sync.get("person");
        cache.setName("李四");
        System.out.println(cache);
    }

    /**
     * 执行lua脚本
     */
    @Test
    public void testEVal() {
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> sync = connection.sync();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object ret = sync.eval(script, ScriptOutputType.BOOLEAN, new String[]{"test"}, "abc321");
        System.out.println(ret);
    }

}
