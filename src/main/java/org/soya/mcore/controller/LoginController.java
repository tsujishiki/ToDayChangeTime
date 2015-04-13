package org.soya.mcore.controller;

import org.soya.tdct.module.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    public ModelAndView login(User user,HttpServletRequest request, HttpServletResponse response){
        //用户校验
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        return new ModelAndView("countryviewer");
    }
}
