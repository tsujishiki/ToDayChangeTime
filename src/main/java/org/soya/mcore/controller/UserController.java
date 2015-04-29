package org.soya.mcore.controller;

import org.soya.mcore.constant.Status;
import org.soya.mcore.dto.ReturnBody;
import org.soya.mcore.model.User;
import org.soya.mcore.service.UserSer;
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
     * ��֤�л����Ƿ����
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
            rbody.setStatus(Status.USERNAMEDUPLICATE);
        }else if(userSer.selectByName(user.getUserName()) != null){
            rbody.setStatus(Status.NICKNAMEDUPLICATE);
        }else {
            userSer.addUser(user);
            user.setCreateDate(new Date().toLocaleString());
            user.setUserId("1");
            rbody.setStatus(Status.SUCCESS);
        }

        return rbody;
    }

}
