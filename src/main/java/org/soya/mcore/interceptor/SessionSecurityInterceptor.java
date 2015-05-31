package org.soya.mcore.interceptor;

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
 * Created by FunkySoya on 2015/4/12.
 */
public class SessionSecurityInterceptor implements HandlerInterceptor {

    public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除

    @Autowired
    UserSer userSer;

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

        ReturnBody rbody = new ReturnBody();

        if(null != allowUrls && allowUrls.length>=1) {
            for (String url : allowUrls) {
                if (requestUrl.contains(url)) {
                    return true;
                }
            }
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
            //自动登录验证
            String token;
            String userName;

            Map<String,String> cookieParam = ParameterUtil.cookiesToMap(request.getCookies());
            token = cookieParam.get("token");
            userName = cookieParam.get("userName");
            if(userName != null) {
                userName = URLDecoder.decode(userName, "utf-8");
            }

            if (token != null){
                user = userSer.selectByName(userName);
                if(user != null && token.equals(user.getToken())){
                    request.getSession().setAttribute("user",user);
                    return true;
                }else{
                    if(requestUrl.contains("/checkLogin")) {
                        rbody.setStatus(Status.FAILED);
                        return false;
                    }else{
                        rbody.setRedirectUrl("/");
                        return false;
                    }
                }
            }else {
                rbody.setRedirectUrl("/");
                return false;
            }
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
