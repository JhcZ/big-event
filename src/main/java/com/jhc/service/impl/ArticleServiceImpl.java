package com.jhc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jhc.mapper.ArticleMapper;
import com.jhc.pojo.Article;
import com.jhc.pojo.PageBean;
import com.jhc.pojo.User;
import com.jhc.service.ArticleService;
import com.jhc.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-03-03 16:28
 **/

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建pagebean对象，封装查询到的数据
        PageBean pb = new PageBean();

        //使用分页查询,通过pagehelper插件
        PageHelper.startPage(pageNum,pageSize);

        //使用mapper层的方法查询结果
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId,categoryId,state);
        //Page中提供了方法，在PageHelper分页查询后，获取总记录数以及当前页面的数据
        Page<Article> p = (Page<Article>) as;

        //将数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Article detail(Integer id) {
        Article article = articleMapper.findById(id);
        return article;
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }
}