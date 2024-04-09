package com.jhc.controller;

import com.jhc.pojo.Result;
import com.jhc.pojo.User;
import com.jhc.service.UserService;
import com.jhc.utils.JwtUtil;
import com.jhc.utils.Md5Util;
import com.jhc.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-01-03 12:45
 **/
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        //先查询用户
        User u =  userService.findByUserName(username);
        if(u == null){
            //用户名未被占用，可以注册
            userService.register(username,password);
            return Result.success();
        }else {
            //用户名被占用，注册失败
            return Result.error("用户名被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        //先查找数据库中是否有该用户
        User loginUser = userService.findByUserName(username);
        //验证用户是否存在
        if(loginUser == null){
            return Result.error("用户名错误！");
        }

        //判断密码是否正确，为加密后的MD5密文
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            //先将令牌token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误！");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/

        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params, @RequestHeader("Authorization") String token){
        //首先校验要改写的密码格式是否正确
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) ||!StringUtils.hasLength(rePwd)){
            return Result.error("输入的密码格式不正确");
        }

        //格式正确后，先确认原密码和输入的oldPwd是否一致，首先获取原密码，根据token来获取
        Map<String,Object> map = ThreadLocalUtil.get();
        String userName = (String) map.get("username");
        User loginUser = userService.findByUserName(userName);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写错误");
        }

        //若原密码填写正确，则校验新密码newPwd与确认密码rePwd是否一致
        if(!newPwd.equals(rePwd)){
            return Result.error("两次填写的密码不一致");
        }

        //若通过了以上校验步骤，则允许修改密码
        userService.updatePwd(newPwd);

        //修改密码成功后，删除redis中的旧令牌
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }
}