package com.qsiny.graduation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 问题表
 * @TableName sys_question
 */
@TableName(value ="sys_question")
@Data
public class Question implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 提出问题的用户ID
     */
    private Long createBy;

    /**
     * 提出时间
     */
    private Date createTime;

    /**
     * 问题(最多一百字)
     */
    private String question;

    /**
     * 解决人
     */
    private String activeBy;

    /**
     * 解决时间
     */
    private Date activeTime;

    /**
     * 解决事情之后的回复
     */
    private String activeMessage;

    /**
     * 是否可用(0代表未解决，1代表以解决)
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}