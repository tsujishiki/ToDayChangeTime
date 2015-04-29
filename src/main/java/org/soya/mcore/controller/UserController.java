package org.soya.mcore.controller;

import org.soya.mcore.constant.Status;
import org.soya.mcore.dto.ReturnBody;
import org.soya.mcore.model.User;
import org.soya.mcore.service.UserSer;
import org.soya.mcore.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ReturnBody newUser(@RequestBody User user){
        ReturnBody rbody = new ReturnBody();
        User validUser = userSer.selectByName(user.getUserName());
        if(validUser != null){
            rbody.setStatus(Status.USERNAME_DUPLICATE);
        }else if(userSer.selectByNickName(user.getNickName()) != null){
            rbody.setStatus(Status.NICKNAME_DUPLICATE);
        }else {
            user.setPassword(EncryptUtil.doEncrypt(user.getPassword()));
            user.setCreateDate(new Date());
            user.setUserId("3");
            userSer.addUser(user);
            rbody.setStatus(Status.SUCCESS);
        }

        return rbody;
    }

}
