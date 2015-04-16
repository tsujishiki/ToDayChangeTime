package org.soya.mcore.controller;

import com.google.code.kaptcha.Constants;
import org.soya.mcore.dto.LoginForm;
import org.soya.mcore.model.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by FunkySoya on 2015/4/13.
 */

@RestController
public class LoginController {

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    public ModelAndView login(@RequestBody LoginForm form,HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
        String returunMsg = null;
        String kaptchaSession = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!kaptchaSession.equals(form.getKaptcha())){
            returunMsg = "验证码输入错误！";
            modelMap.put("returnMsg",returunMsg);
            return new ModelAndView("/../index",modelMap);
        }

        User user = new User();
        user.setUserName(form.getUserName());
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        return new ModelAndView("countryviewer");
    }
}
