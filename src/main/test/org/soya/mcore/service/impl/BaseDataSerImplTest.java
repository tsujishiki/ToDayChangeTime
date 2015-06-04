package org.soya.mcore.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.util.Map;

import static org.junit.Assert.*;

public class BaseDataSerImplTest {

    StringRedisTemplate redisTemplate = null;
    Jedis jedis = null;

    @Before
    public void setUp() throws Exception {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-config.xml");
//        redisTemplate = (StringRedisTemplate)ctx.getBean("redisTemplate");

        jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("12345");
    }

    @Test
    public void testGetListByType() throws Exception {
        ScanResult<Map.Entry<String, String>> gameType = jedis.hscan("gameType", "");
        System.out.print(gameType);
    }

    @Test
    public void testGetMapByCode() throws Exception {

    }

    @Test
    public void runData() throws Exception {
        redisTemplate.delete("gameType");
        redisTemplate.delete("platform");
        redisTemplate.delete("edition");

        BoundHashOperations<String,String,String> gametype = redisTemplate.boundHashOps("gameType");
        gametype.put("ACT","动作游戏");
        gametype.put("AVG","冒险游戏");
        gametype.put("FPS","第一人称视点射击游戏");
        gametype.put("FTG","格斗游戏");
        gametype.put("MUG","音乐游戏");
        gametype.put("PUZ","益智类游戏");
        gametype.put("RAC","赛车游戏");
        gametype.put("RPG","角色扮演游戏");
        gametype.put("RTS","即时战略游戏");
        gametype.put("SLG","模拟/战棋式战略游戏");
        gametype.put("SPG","体育运动游戏");
        gametype.put("STG","射击游戏");
        gametype.put("TAB","桌面游戏");
        gametype.put("ETC","其他类型游戏");
        gametype.put("AVG/ADV","恋爱养成游戏");

        BoundHashOperations<String,String,String> platform = redisTemplate.boundHashOps("platform");
        platform.put("PS4","PS4");
        platform.put("PS3","PS3");
        platform.put("PS2","PS2");
        platform.put("PS","PS");
        platform.put("XBOXONE","XBOXONE");
        platform.put("XBOX360","XBOX360");
        platform.put("XBOX","XBOX");
        platform.put("WIIU","WIIU");
        platform.put("WII","WII");
        platform.put("PSV","PSV");
        platform.put("PSP","PSP");
        platform.put("3DS","3DS");
        platform.put("NDS","NDS");
        platform.put("PC","PC");
        platform.put("SS","SS");
        platform.put("MD","MD");
        platform.put("GBC","GBC");
        platform.put("GBA","GBA");
        platform.put("OTHER","OTHER");

        BoundHashOperations<String,String,String> edition = redisTemplate.boundHashOps("edition");
        edition.put("AS","亚版");
        edition.put("EU","欧版");
        edition.put("AM","美版");
        edition.put("AU","澳版");
        edition.put("CHN","国行");
        edition.put("UK","港版");
        edition.put("TW","台版");
        edition.put("JP","日版");
        edition.put("ROK","韩版");
        edition.put("OTHER","其他");
    }
}