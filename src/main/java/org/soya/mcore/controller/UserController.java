package org.soya.mcore.controller;

import org.soya.mcore.constant.Status;
import org.soya.mcore.dto.RegisterForm;
import org.soya.mcore.dto.ReturnBody;
import org.soya.mcore.model.User;
import org.soya.mcore.service.UserSer;
import org.soya.mcore.util.EncryptUtil;
import org.soya.mcore.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by FunkySoya on 2015/4/28.
 */

@RestController
public class UserController {

    @Autowired
    UserSer userSer;

    /**
     * @param user
     * @return
     * ��֤�л����Ƿ����?
     */
    @RequestMapping(value = {"/register/validUserName"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnBody ValidUserName(@RequestBody User user){
        ReturnBody rbody = new ReturnBody();
        User validUser = userSer.selectByName(user.getUserName());
        if(validUser == null){
            rbody.setStatus(Status.SUCCESS);
        }else{
            rbody.setStatus(Status.FAILED);
        }
        return rbody;
    }

    @RequestMapping(value = {"/register/new"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnBody newUser(@RequestBody RegisterForm form,HttpServletRequest request){
        ReturnBody rbody = new ReturnBody();

        if(!ValidUtil.validCaptcha(request,form.getKaptcha())){
            rbody.setStatus(Status.CAPTCHA_INVALID);
            return rbody;
        }

        User formUser = form.getUser();
        User validUser = userSer.selectByName(formUser.getUserName());
        if(validUser != null){
            rbody.setStatus(Status.USERNAME_DUPLICATE);
        }else if(userSer.selectByNickName(formUser.getNickName()) != null){
            rbody.setStatus(Status.NICKNAME_DUPLICATE);
        }else {
            formUser.setPassword(EncryptUtil.doEncrypt(formUser.getPassword()));
            formUser.setCreateDate(new Date());
            userSer.addUser(formUser);
            rbody.setStatus(Status.DEFER_MESSAGE);
            rbody.setMsg("注册成功");
        }

        return rbody;
    }

}
