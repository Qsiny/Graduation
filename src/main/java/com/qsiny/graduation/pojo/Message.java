package com.qsiny.graduation.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 消息表
 * @author Qsiny
 * @TableName sys_message
 */
@TableName(value ="sys_message")
@Data
public class Message implements Serializable {
    /**
     * 消息id
     */
    private Long messageId;

    /**
     * 发信人
     */
    private String teacherName;

    /**
     * 发信人id
     */
    private String teacherId;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 消息实体
     */
    private String message;

    /**
     * 收信人id
     */
    private String studentId;

    /**
     * 确认标识(0代表未确认，1代表以确认)
     */
    @TableLogic
    private Integer confirmFlag;

}