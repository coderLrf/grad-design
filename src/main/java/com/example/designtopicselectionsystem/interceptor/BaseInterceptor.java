package com.example.designtopicselectionsystem.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class BaseInterceptor implements HandlerInterceptor {

    // 访问之前
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        String content = request.getParameter("content");
//        if(content != null) {
//            System.out.println(content);
//        }
//        System.out.println("进来了...");
        return true;
    }

    // 正常访问后
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 用户将封装的Commons工具返回HTML页面
//        System.out.println("拦截了");
//        String content = request.getParameter("content");
//        System.out.println(content);
    }

}
