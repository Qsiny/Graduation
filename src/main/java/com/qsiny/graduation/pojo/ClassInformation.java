package com.qsiny.graduation.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 班级表
 * @author Qin
 * @TableName sys_class_information
 */
@TableName(value ="sys_class_information")
@Data
public class ClassInformation implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 年级号
     */
    private String gradeId;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 院系号
     */
    private String facultyId;

    /**
     * 院系名称
     */
    private String facultyName;

    /**
     * 专业号
     */
    private String professionId;

    /**
     * 专业名称
     */
    private String professionName;

    /**
     * 班级号
     */
    private String classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 是否可用(0代表可用，1代表不可用)
     */
    @TableLogic
    private Integer delFlag;

}