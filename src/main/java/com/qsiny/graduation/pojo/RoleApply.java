package com.qsiny.graduation.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 角色权限申请表
 * @TableName sys_role_apply
 */
@TableName(value ="sys_role_apply")
@Data
public class RoleApply implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户申请的角色（0普通用户,1学生,2老师）
     */
    private String userType;

    /**
     * 年级
     */
    private String gradeId;

    /**
     * 学院
     */
    private String facultyId;

    /**
     * 专业
     */
    private String professionId;

    /**
     * 班级
     */
    private String classId;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date applyTime;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date processingTime;

    /**
     * 是否删除（0未通过 1已通过）
     */
    @TableLogic
    private Integer applyFlag;

}