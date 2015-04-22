package org.soya.mcore.utils;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

/**
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
