package com.qsiny.graduation.service;

import com.qsiny.graduation.pojo.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qsiny.graduation.pojo.ResponseResult;

/**
* @author Qin
* @description 针对表【sys_question(问题表)】的数据库操作Service
* @createDate 2022-03-19 12:30:21
*/
public interface QuestionService{

    ResponseResult addQuestion(Long createId, String question);

    ResponseResult selectQuestionById(Long createId);

    ResponseResult selectQuestions();

    ResponseResult activeQuestion(Long questionId,Long createId, String message);
}
