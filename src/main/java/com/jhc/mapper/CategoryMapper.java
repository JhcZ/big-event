package com.jhc.mapper;

import com.jhc.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-03-02 17:13
 **/

@Mapper
public interface CategoryMapper {

    //添加文章分类
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
    "values(#{categoryName},#{categoryAlias},#{id},#{createTime},#{updateTime})")
    public void add(Category category);

    //根据userId查询文章分类列表
    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);

    //根据id查询文章列表
    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    //跟新文章分类
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias} where id=#{id}")
    void updateById(Category category);

    //删除文章分类
    @Delete("delete from category where id=#{id}")
    void deleteById(Integer id);
}