import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.*;

/**
 * Created by Administrator on 2015/5/28.
 */
public class RunData {

    public static void main (String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-config.xml");
        StringRedisTemplate redisTemplate = (StringRedisTemplate)ctx.getBean("redisTemplate");
        BoundHashOperations<String, String, String> gameType = redisTemplate.boundHashOps("gameType");
        System.out.print(gameType.get("ACT"));
    }

}
