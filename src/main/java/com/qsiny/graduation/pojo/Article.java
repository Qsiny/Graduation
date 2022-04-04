package com.qsiny.graduation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_article
 */
@TableName(value ="sys_article")
@Data
public class Article implements Serializable {
    /**
     * 文章的唯一ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 作者
     */
    private String author;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章的内容
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}