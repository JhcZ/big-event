package com.jhc.mapper;

import com.jhc.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    //@Insert("insert into user(username,password,create_time,update_time)" +
    //            " values(#{username},#{password},now(),now())")

    //添加文章
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            " values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    //查找文章列表 通过动态sql来实现  ‘ArticleMapper.xml’文件
    List<Article> list(Integer userId, Integer categoryId, String state);

    //通过ID查找文章
    @Select("select * from article where id=#{id}")
    Article findById(Integer id);

    //更新文章
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId} where id=#{id}")
    void update(Article article);

    //根据ID删除文章
    @Delete("delete from article where id=#{id}")
    void deleteById(Integer id);
}
