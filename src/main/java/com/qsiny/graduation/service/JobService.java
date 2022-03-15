package com.qsiny.graduation.service;

import com.qsiny.graduation.pojo.Job;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Qin
* @description 针对表【sys_job(工作表)】的数据库操作Service
* @createDate 2022-03-15 22:15:40
*/
public interface JobService{
    /**
     * 通过年级专业班级先找到对应的学生ID列表
     * @return
     */
    List<String>  selectStudentsIdByAttributes(String gradeId,String facultyId,String professionId,String classId);

}
