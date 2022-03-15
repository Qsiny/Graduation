package com.qsiny.graduation.mapper;

import com.qsiny.graduation.pojo.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Qin
* @description 针对表【sys_job(工作表)】的数据库操作Mapper
* @createDate 2022-03-15 22:15:40
* @Entity com.qsiny.graduation.pojo.Job
*/
@Mapper
public interface JobMapper extends BaseMapper<Job> {


    List<String> selectStudentsId(@Param("gradeId") String gradeId,@Param("facultyId") String facultyId,@Param("professionId")String professionId,
                                  @Param("classId") String classId);
}




