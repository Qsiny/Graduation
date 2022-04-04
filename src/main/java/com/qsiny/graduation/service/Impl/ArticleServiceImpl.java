package com.qsiny.graduation.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qsiny.graduation.pojo.Article;
import com.qsiny.graduation.service.ArticleService;
import com.qsiny.graduation.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Qin
* @description 针对表【sys_article】的数据库操作Service实现
* @createDate 2022-04-04 11:31:21
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article selectPageById(int id) {
        return articleMapper.selectById(id);
    }
}




