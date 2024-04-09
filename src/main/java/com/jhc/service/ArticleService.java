package com.jhc.service;

import com.jhc.pojo.Article;
import com.jhc.pojo.PageBean;

public interface ArticleService {

    //添加文章
    void add(Article article);

    //条件分页列表查询
    PageBean list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //通过id查询文章详情
    Article detail(Integer id);

    void update(Article article);

    void deleteById(Integer id);
}
