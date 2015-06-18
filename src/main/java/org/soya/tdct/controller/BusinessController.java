package org.soya.tdct.controller;

import org.soya.mcore.constant.Context;
import org.soya.mcore.constant.Status;
import org.soya.mcore.controller.BaseController;
import org.soya.mcore.dto.ReturnBody;
import org.soya.mcore.model.User;
import org.soya.tdct.model.Business;
import org.soya.tdct.service.BusinessSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by FunkySoya on 2015/6/18.
 */
@RestController
public class BusinessController extends BaseController {

    @Autowired
    BusinessSer businessSer;

    @RequestMapping(value = {"/business/add"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnBody add(@RequestBody Business business,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute(Context.USER);
        ReturnBody rbody = new ReturnBody();
        business.setCreateDate(new Date());
        business.setCreator(user.getUserId());
        businessSer.add(business);

        rbody.setStatus(Status.REDIRECT);
        rbody.setRedirectUrl("/");

        return rbody;
    }

}
