package com.qsiny.graduation.handler;

import com.alibaba.fastjson.JSON;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Qin
 * @description: 验证未通过，说明需要校验身份
 * @date 2022/3/9 22:12
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //401代表用户未授权
        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户未认证，请重新登录");

        String response = JSON.toJSONString(responseResult);
        //设置response的相应属性
        WebUtils.renderString(httpServletResponse,response);
    }
}
