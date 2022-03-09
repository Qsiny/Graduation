package com.qsiny.graduation.handler;

import com.alibaba.fastjson.JSON;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Qin
 * @description: 校验用户的权限
 * @date 2022/3/9 22:07
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    /**
     * 用户认证失败时方便回显给前端一个JSON子串
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //403代表用户权限不足
        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(),"权限不足，无法访问");

        String response = JSON.toJSONString(responseResult);
        //设置response的相应属性
        WebUtils.renderString(httpServletResponse,response);
    }
}
