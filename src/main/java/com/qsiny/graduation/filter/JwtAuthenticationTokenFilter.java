package com.qsiny.graduation.filter;

import com.qsiny.graduation.pojo.LoginUser;
import com.qsiny.graduation.utils.JwtUtil;
import com.qsiny.graduation.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Qin
 * @description: TODO
 * @date 2022/3/6 14:30
 */

@Component
public class JwtAuthenticationTokenFilter extends GenericFilter {

    @Autowired
    private RedisCache redisCache;

 /*   @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    }*/

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //如果我有一个jwt需要来进行访问数据
        //获取jwt
        String token = httpServletRequest.getHeader("token");
        //如果没有token 则代表还没有进行登录，可能是登录请求，需要放xing
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        String userId;
        //解析jwt
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            //如果抛出异常 说明解析失败 可能是由于伪造的token
            throw new RuntimeException("token解析出现问题:"+token);
        }

        //说明没问题 能够成功获取和解析,从redis中获取用户信息
        String redisKey = "userid:"+userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        //如果获取不到？ 原因可能是 过期或者是丢失了,那就重新登录一下
        if(loginUser == null){
            throw new RuntimeException("用户消息丢失，请重新登录");
        }
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,authorities);

        //存入securityContext
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
