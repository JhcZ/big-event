//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus'

//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({baseURL})

//添加请求拦截器
import {useTokenStore} from '@/stores/token.js'
instance.interceptors.request.use(
    (config)=>{
        //请求前的回调
        const tokenStore = useTokenStore();
        //判断是否存在token
        if(tokenStore.token){
            config.headers.Authorization=tokenStore.token
        }
        return config;
    },
    (err)=>{
        //请求错误的回调
        Promise.reject(err)
    }
    
)

import router from '@/router'
//添加响应拦截器
instance.interceptors.response.use(
    //判断响应状态码
    result=>{
        //先判断响应状态码
        if(result.data.code===0){
            return result.data;
        }

        //操作失败，异步提示信息
        //alert( result.data.msg ? result.data.msg : '服务异常');
        ElMessage.error(result.data.msg ? result.data.msg : '服务异常');
        return Promise.reject(result.msg);
    },
    err=>{
        //先判断响应状态码，如果为401，则证明未登录,需要跳转到登录页面
        if(err.response.status===401){
            ElMessage.error('请先登录')
            router.push('/login')
        }else{
            ElMessage.error('服务异常')
        }
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;