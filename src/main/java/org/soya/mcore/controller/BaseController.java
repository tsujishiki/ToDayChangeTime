package org.soya.mcore.controller;

import org.soya.mcore.constant.Status;
import org.soya.mcore.dto.ReturnBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Created by Administrator on 2015/6/1.
 */
public class BaseController {

    /**
     * 统一异常处理
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ReturnBody exp(HttpServletRequest request, Exception ex){
        ReturnBody rbody = new ReturnBody();
        rbody.setStatus(Status.ERROR);
        rbody.setMsg(ex.getMessage());

        return  rbody;
    }
}
