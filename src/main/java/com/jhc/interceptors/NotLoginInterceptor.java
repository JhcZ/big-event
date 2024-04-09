package com.jhc.interceptors;

import com.jhc.pojo.Result;
import com.jhc.utils.JwtUtil;
import com.jhc.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @description: 未登录用户拦截器
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-01-05 10:56
 **/

@Component
public class NotLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //首先验证Jwt令牌
        String token = request.getHeader("Authorization");
        try{
            //如果该访问对象已登录，则通过
            Map<String, Object> claims = JwtUtil.parseToken(token);

            //从redis中获取令牌
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if(redisToken == null){
                throw new RuntimeException();
            }

            //设置ThreadLocal对象，为了获取userId
            ThreadLocalUtil.set(claims);
            return true;
        }catch (Exception e){
            //未登录，则拦截
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //在完成相应操作之后，清空相应对象的内存
        ThreadLocalUtil.remove();
    }
}