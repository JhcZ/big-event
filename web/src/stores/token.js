//定义store
import {defineStore} from 'pinia'
import {ref} from 'vue'

/*
defineStore函数有两个参数：
    第一个参数：名字，具有唯一性
    第二个参数：函数，函数的内部可以定义状态的所有内容

    返回值：是一个函数

*/
export const useTokenStore = defineStore('token',()=>{
    //定义状态的内容：
    //1.响应式变量
    const token=ref('')

    //2.设置Token的值
    const setToken = (newToken)=>{
        token.value=newToken
    }

    //3.删除Token的值
    const removeToken = ()=>{
        token.value=''
    }

    //返回三个状态量
    return{
        token,setToken,removeToken
    }
},
{
    persist: true  //持久化存储
});