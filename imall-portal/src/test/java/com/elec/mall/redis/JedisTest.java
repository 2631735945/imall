package com.elec.mall.redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void testString() {
        //1.连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2.操作redis
        jedis.set("name", "JavaUI");
        String name = jedis.get("name");
        System.out.println(name);
        //3.关闭redis
        jedis.close();
    }
}
