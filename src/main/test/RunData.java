import org.soya.mcore.model.Dictionary;
import org.soya.mcore.util.RedisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/28.
 */
public class RunData {

    public static void main (String[] args){
        Dictionary dictionary = new Dictionary();
        dictionary.setCode("RGB");
        dictionary.setName("角色扮演游戏");

        Dictionary dictionary1 = new Dictionary();
        dictionary1.setCode("ACT");
        dictionary1.setName("动作游戏");

        List<Dictionary> gameType = new ArrayList<>();
        gameType.add(dictionary);
        gameType.add(dictionary1);

//        RedisUtil.getInstance().set("gameType",gameType);

        Object gameType1 = RedisUtil.getInstance().get("gameType");
        List<Dictionary> dict = (List)gameType1;
        System.out.print(dict.get(1).getName());
    }

}
