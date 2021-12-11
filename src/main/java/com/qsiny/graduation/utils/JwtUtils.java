package com.qsiny.graduation.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/21 13:02
 */
public class JwtUtils {


    /**
     * 设置私钥为SING
     */
    private final static String SING = "!Q@W#ADWSDW@@$";

    /**
     * 调用方法可以直接获取token 方便调用 后期也可以直接加入到拦截器中
     * @return
     */
    public static String getToken(){

        //创建一个JWT
        JWTCreator.Builder builder = JWT.create();

        //用来设置过期时间(默认7天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,7);

        //token的设置
        String token = builder.withExpiresAt(calendar.getTime())
                //建议是从数据库获取之后的结果进行注入
                .withClaim("username", "zhangsan")
                .withClaim("userId", 11)
                //设置私钥
                .sign(Algorithm.HMAC256(SING));

        System.out.println(token);
        return token;
    }

    /**
     * 用于校验 以及返回DecodedJWT，可以或许token中的数据
     * 如果出现异常 则 说明token校验出现了问题
     * @param  token
     * @return DecodedJWT
     */
    public static DecodedJWT verify(String token){
        return  JWT.decode(token);
    }

}
