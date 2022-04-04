package com.qsiny.graduation.service;

import com.qsiny.graduation.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Qin
* @description 针对表【sys_article】的数据库操作Service
* @createDate 2022-04-04 11:31:21
*/
public interface ArticleService extends IService<Article> {

    Article selectPageById(int id);
}
