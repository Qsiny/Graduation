package com.qsiny.graduation.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qsiny.graduation.pojo.Job;
import com.qsiny.graduation.service.JobService;
import com.qsiny.graduation.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Qin
* @description 针对表【sys_job(工作表)】的数据库操作Service实现
* @createDate 2022-03-15 22:15:40
*/
@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<String> selectStudentsIdByAttributes(String gradeId, String facultyId,String professionId, String classId) {

        return jobMapper.selectStudentsId(gradeId, facultyId, professionId, classId);
    }
}




