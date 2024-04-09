package com.jhc.service;

import com.jhc.pojo.Result;
import com.jhc.pojo.User;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-01-03 12:45
 **/
public interface UserService {
    //根绝用户名查询用户
    User findByUserName(String username);
    //注册
    void register(String username, String password);

    //更新
    void update(User user);

    //更新用户头像
    void updateAvatar(String avatarUrl);

    //修改用户密码
    void updatePwd(String newPwd);
}