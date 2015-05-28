package org.soya.mcore.util;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2015/5/28.
 */
public class RedisUtil {
    private static String ip= "192.168.0.194";
    private static int port=6379;
    private static Jedis jedis = new Jedis(ip,port);
    private static RedisUtil redis;

    protected RedisUtil(){

    }
    public static RedisUtil getInstance()
    {
        if(redis == null){
            redis = new RedisUtil();
        }
        return redis;
    }

    /**set Object*/
    public String set(String key,Object object)
    {
        return jedis.set(key.getBytes(), SerializeUtil.serialize(object));
    }

    /**get Object*/
    public Object get(String key)
    {
        byte[] value = jedis.get(key.getBytes());
        return SerializeUtil. unserialize(value);
    }

    /**delete a key**/
    public boolean del(String key)
    {
        return jedis.del(key.getBytes())>0;
    }


}
