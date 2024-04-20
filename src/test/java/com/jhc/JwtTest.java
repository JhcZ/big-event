package com.jhc;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: jwt令牌测试
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-01-04 19:23
 **/
public class JwtTest {

    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        //生成jwt的代码
        String token =  JWT.create()
                .withClaim("user",claims)  //添加载荷，用户自己自定义的信息
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*12))  //设置令牌验证的超时时间
                    .sign(Algorithm.HMAC256("jhc"));  //指定生成算法并配置相关密钥

        System.out.println(token);
    }

    /*@Test
    public void testParse(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDQzNjgyNjAsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoi5byg5LiJIn19.yWi0b20G0LZh90Yq78adYBytkSwuMHQj43OoOn82XfE";

        //解析时使用的算法要和生成jwt时的算法一致，并且参数部分密钥也需一致，'jhc'
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("jhc")).build();
        //验证token，生成一个解析后的JWT对象
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

        //如果篡改了头部和载荷部分的数据，则验证失败
        //如果超时，则验证失败,都会抛出异常
    }*/
}