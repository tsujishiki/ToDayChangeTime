import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.*;

import java.util.Map;

/**
 * Created by Administrator on 2015/5/28.
 */
public class RunData {

    public static void main (String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-config.xml");
        StringRedisTemplate redisTemplate = (StringRedisTemplate)ctx.getBean("redisTemplate");

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

        Map<String, String> entries = gametype.entries();
    }

}
