package org.trahim.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.trahim.objects.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckUserInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.getRequestURI().contains("check-user")){

            User user = (User) modelAndView.getModel().get("user");

        if (user.getName() == ""||user.getPassword()=="" || !user.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/failed");
        }
    }
    }


}
