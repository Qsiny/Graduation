package com.qsiny.graduation.interceptor;

import com.qsiny.graduation.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/5 16:47
 */
@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("当前per拦截地址为{}",request.getRequestURI());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userMsg");
        String uri = request.getRequestURI();
        //如果没登录，并且又访问了不该访问的地址，则将它拦截，并且让他登录

        if(user == null){
            //说明，没有登录过
            request.setAttribute("errorMsg","请先登录");
            request.getRequestDispatcher("/toLogin").forward(request,response);
            return false;
        }

        return true;
    }
}
