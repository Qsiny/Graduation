package com.qsiny.graduation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 工作表
 * @TableName sys_job
 */
@TableName(value ="sys_job")
@Data
public class Job implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工作ID
     */
    private String jobId;

    /**
     * 年级
     */
    private String gradeId;

    /**
     * 院系号
     */
    private String facultyId;

    /**
     * 专业号
     */
    private String professionId;

    /**
     * 班号
     */
    private String classId;

    /**
     * 院名
     */
    private String facultyName;

    /**
     * 专业名
     */
    private String professionName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}