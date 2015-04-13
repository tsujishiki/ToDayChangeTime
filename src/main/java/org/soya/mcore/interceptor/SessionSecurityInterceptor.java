package org.soya.mcore.interceptor;

import org.soya.tdct.module.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by FunkySoya on 2015/4/12.
 */
public class SessionSecurityInterceptor implements HandlerInterceptor {

    public SessionSecurityInterceptor() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod hm = (HandlerMethod)handler;
        if("LoginController".equals(hm.getBean().getClass().getSimpleName())  && "login".equals(hm.getMethod().getName())){
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
            response.sendRedirect("/page/error.html");
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
