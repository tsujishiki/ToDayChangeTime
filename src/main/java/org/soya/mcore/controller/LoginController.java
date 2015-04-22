package org.soya.mcore.controller;

import com.google.code.kaptcha.Constants;
import org.soya.mcore.dto.LoginForm;
import org.soya.mcore.dto.ReturnBody;
import org.soya.mcore.mapper.UserMapper;
import org.soya.mcore.model.User;
import org.soya.mcore.service.UserSer;
import org.soya.mcore.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by FunkySoya on 2015/4/13.
 */

@RestController
public class LoginController {

    @Autowired
    UserSer userSer;

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnBody login(@RequestBody LoginForm form,HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
        String kaptchaSession = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        ReturnBody rbody = new ReturnBody();
        if(!kaptchaSession.equals(form.getKaptcha())){
            rbody.setStatus("F");
            rbody.setMsg("验证码输入错误！");
            return rbody;
        }

        User user = userSer.selectByName(form.getUserName());

        if(user != null){
            if(!user.getPassword().equals(EncryptUtil.doEncrypt(form.getPassword()))){
                rbody.setStatus("F");
                rbody.setMsg("密码错误！");
            }else{
                rbody.setStatus("T");
                rbody.setMsg("Success");
                rbody.setRedirectUrl("/");
                rbody.setData(UUID.randomUUID());
            }
        }else{
            rbody.setStatus("F");
            rbody.setMsg("用户名不存在！");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        return rbody;
    }
}
