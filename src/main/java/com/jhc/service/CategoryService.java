package com.jhc.service;

import com.jhc.pojo.Category;

import java.util.List;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-03-02 17:12
 **/
public interface CategoryService {
    //新增文章分类
    void add(Category category);

    //列表分类查询
    List<Category> list();

    //根据id查询用户文章分类
    Category findById(Integer id);

    void updateById(Category category);

    void deleteById(Integer id);
}