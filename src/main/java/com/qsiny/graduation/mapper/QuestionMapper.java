package com.qsiny.graduation.mapper;

import com.qsiny.graduation.pojo.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Qin
* @description 针对表【sys_question(问题表)】的数据库操作Mapper
* @createDate 2022-03-19 12:30:21
* @Entity com.qsiny.graduation.pojo.Question
*/
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}




