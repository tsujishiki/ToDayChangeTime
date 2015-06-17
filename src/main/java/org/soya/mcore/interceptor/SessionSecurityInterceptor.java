package org.soya.mcore.interceptor;

import com.alibaba.fastjson.JSON;
import org.soya.mcore.constant.Context;
import org.soya.mcore.constant.Status;
import org.soya.mcore.dto.ReturnBody;
import org.soya.mcore.model.User;
import org.soya.tdct.service.UserSer;
import org.soya.mcore.util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 验证用户权限
 * Created by FunkySoya on 2015/4/12.
 */
public class SessionSecurityInterceptor implements HandlerInterceptor {

    public String[] allowUrls;//不需要拦截的请求，配置文件中注入

    @Autowired
    UserSer userSer;

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

        ReturnBody rbody = new ReturnBody();

        if (null != allowUrls && allowUrls.length >= 1) {
            for (String url : allowUrls) {
                if (requestUrl.contains(url)) {
                    return true;
                }
            }
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Context.USER);
        if (user == null) {
            rbody.setRedirectUrl("/login");
            rbody.setStatus(Status.REDIRECT);
            response.getWriter().print(JSON.toJSONString(rbody));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
