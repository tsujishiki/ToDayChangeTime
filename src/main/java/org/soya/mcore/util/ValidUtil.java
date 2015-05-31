package org.soya.mcore.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * У�鹤����
 * Created by FunkySoya on 2015/5/7.
 */
public class ValidUtil {

    public static boolean validCaptcha(HttpServletRequest request,String captcha){
        if(captcha == null){
            return false;
        }

        if(captcha.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
            return true;
        }

        return false;
    }
}
