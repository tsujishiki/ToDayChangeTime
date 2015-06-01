package org.soya.tdct.controller;

import org.soya.mcore.constant.Status;
import org.soya.mcore.controller.BaseController;
import org.soya.mcore.dto.LoginForm;
import org.soya.mcore.dto.ReturnBody;
import org.soya.mcore.model.User;
import org.soya.tdct.service.UserSer;
import org.soya.mcore.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by FunkySoya on 2015/4/13.
 */

@RestController
public class LoginController extends BaseController {

    @Autowired
    UserSer userSer;

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnBody login(@RequestBody LoginForm form,HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
        ReturnBody rbody = new ReturnBody();
//        if(!ValidUtil.validCaptcha(request,form.getKaptcha())){
//            rbody.setStatus(Status.FAILED);
//            rbody.setMsg("验证码输入错误！");
//            return rbody;
//        }

        User user = userSer.selectByName(form.getUserName());
        Map data = new HashMap();
        if(user != null){
            if(!user.getPassword().equals(EncryptUtil.doEncrypt(form.getPassword()))){
                rbody.setStatus(Status.FAILED);
                rbody.setMsg("密码错误！");
            }else{
                rbody.setStatus(Status.SUCCESS);
                rbody.setMsg("Success");
                rbody.setRedirectUrl("/");
                String token = UUID.randomUUID().toString();
                data.put("token", token);
                data.put("nickName",user.getNickName());
                data.put("userName",user.getUserName());
                rbody.setData(data);

                userSer.updateToken(user.getUserId(),token);
            }
        }else{
            rbody.setStatus(Status.FAILED);
            rbody.setMsg("用户名不存在！");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",user);

        return rbody;
    }

    @RequestMapping(value = {"/checkLogin"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnBody checkLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        ReturnBody rbody = new ReturnBody();
        rbody.setStatus(Status.SUCCESS);
        rbody.setData(user.getNickName());
        return rbody;
    }

    @RequestMapping(value = {"/logoff"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnBody logoff(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        ReturnBody rbody = new ReturnBody();
        rbody.setStatus(Status.SUCCESS);
        return rbody;
    }
}
