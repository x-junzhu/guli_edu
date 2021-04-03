package edu.fzu.test.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author JohnCarraway
 * @create 2021-01-10 20:38
 */
public class TestRedis {

    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("192.168.206.128", 6379);

        jedis.set("name", "JohnCarraway");

    }
}
