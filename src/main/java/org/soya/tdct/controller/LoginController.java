package org.soya.tdct.controller;

import com.alibaba.fastjson.JSON;
import org.soya.mcore.constant.Context;
import org.soya.mcore.constant.Status;
import org.soya.mcore.controller.BaseController;
import org.soya.mcore.dto.LoginForm;
import org.soya.mcore.dto.ReturnBody;
import org.soya.mcore.model.User;
import org.soya.mcore.util.ParameterUtil;
import org.soya.tdct.service.UserSer;
import org.soya.mcore.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

                userSer.updateToken(user.getUserId(), token);

                HttpSession session = request.getSession();
                session.setAttribute(Context.USER, user);
            }
        }else{
            rbody.setStatus(Status.FAILED);
            rbody.setMsg("用户名不存在！");
        }
        return rbody;
    }

    /**
     * 自动登录验证
     * @param request
     * @return
     */
    @RequestMapping(value = {"/autoLogin"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnBody autoLogin(HttpServletRequest request) throws UnsupportedEncodingException {
        String token;
        String userName;
        User user ;
        ReturnBody rbody = new ReturnBody();

        Map<String, String> cookieParam = ParameterUtil.cookiesToMap(request.getCookies());
        user = (User)request.getSession().getAttribute(Context.USER);
        token = cookieParam.get("token");
        userName = cookieParam.get("userName");
        if(user != null){
            rbody.setData(user.getNickName());
            rbody.setStatus(Status.SUCCESS);
        }else if (token != null) {
            userName = URLDecoder.decode(userName, "utf-8");
            user = userSer.selectByName(userName);
            if (user != null && token.equals(user.getToken())) {
                request.getSession().setAttribute(Context.USER, user);
                rbody.setData(user.getNickName());
                rbody.setStatus(Status.SUCCESS);
            } else {
                rbody.setStatus(Status.FAILED);
            }
        }else{
            rbody.setStatus(Status.FAILED);
        }

        return rbody;
    }

    @RequestMapping(value = {"/logoff"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnBody logoff(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(Context.USER);

        ReturnBody rbody = new ReturnBody();
        rbody.setStatus(Status.SUCCESS);
        return rbody;
    }
}
