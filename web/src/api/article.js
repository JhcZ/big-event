import request from '@/utils/request.js'
import {useTokenStore} from '@/stores/token.js'

//定义文章分类列表查询的函数
export const articleCategoryListService = ()=>{
    /*const tokenStore = useTokenStore();
    //在pinia中定义的响应式数据，不需要用到.value
    return request.get('/category',{headers:{'Authorization': tokenStore.token}});*/
    return request.get('/category');
}

export const articleCategoryAddService = (categoryData)=>{
    return request.post('/category',categoryData)
}

//文章分类修改
export const articleCategoryUpdateService = (categoryData)=>{
    return request.put('/category',categoryData)
}

//删除文章分类
export const articleCategoryDeleteService = (id)=>{
    return request.delete('/category?id='+id)
}

//文章列表查询
export const articleListService = (params)=>{
    return request.get('/article',{params:params})
}

//文章添加
export const articleAddService = (articleData)=>{
    return request.post('/article',articleData);
}