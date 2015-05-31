package org.soya.mcore.util;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数转换
 * Created by FunkySoya on 2015/4/23.
 */
public class ParameterUtil {

    public static Map<String,String> cookiesToMap(Cookie[] cookies){
        Map<String,String> map = new HashMap<String,String>();
        if (cookies != null){
            for (Cookie cookie : cookies){
                map.put(cookie.getName(),cookie.getValue());
            }
        }

        return map;
    }

}
