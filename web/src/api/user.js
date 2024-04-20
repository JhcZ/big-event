//先导入request.js工具
import request from '@/utils/request.js'

//提供注册接口的函数
export const userRegisterService = (registerData)=>{
    //解析registerData，因为registerData数据形式是JSON形式的，而我们需要的是xxx-param-xxx-xxx形式
    //因此需要借助URLSearchParams解析后完成传递
    const params = new URLSearchParams();
    for(let key in registerData){
        params.append(key,registerData[key]);
    }
    return request.post('/user/register',params);
}

//提供登录接口的函数
export const userLoginSerivce = (loginData)=>{
    //与注册接口同理
    const params = new URLSearchParams();
    for(let key in loginData){
        params.append(key,loginData[key]);
    }
    return request.post('/user/login',params);
}

//获取用户详细信息
export const userInfoService = ()=>{
    return request.get('/user/userInfo')
}

//更新用户头像
export const userAvatarUpdateSerivce = (avatarUrl)=>{
    const params = new URLSearchParams();
    params.append('avatarUrl',avatarUrl);
    return request.patch('/user/updateAvatar',params)
}