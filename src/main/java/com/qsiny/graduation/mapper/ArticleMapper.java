package com.qsiny.graduation.mapper;

import com.qsiny.graduation.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Qin
* @description 针对表【sys_article】的数据库操作Mapper
* @createDate 2022-04-04 11:31:21
* @Entity com.qsiny.graduation.pojo.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}




