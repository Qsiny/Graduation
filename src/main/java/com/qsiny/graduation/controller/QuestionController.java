package com.qsiny.graduation.controller;

import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qin
 * @description: TODO
 * @date 2022/3/20 14:20
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/submitQuestion")
    public ResponseResult submitQuestion(Long createId,String question){

        return questionService.addQuestion(createId,question);

    }

    /**
     * 用户查看自己提交的question
     * @param createId
     * @return
     */
    @RequestMapping("/lookQuestionById")
    public ResponseResult lookQuestionById(Long createId){

        return questionService.selectQuestionById(createId);

    }

    /**
     * 管理员查看所有需要回应的请求
     * @return
     */
    @RequestMapping("/lookQuestions")
    public ResponseResult lookQuestions(){

        return questionService.selectQuestions();

    }

    /**
     * 管理员回复请求
     * @return
     */
    @RequestMapping("/activeQuestion")
    public ResponseResult activeQuestion(Long questionId,Long createId, String message){

        return questionService.activeQuestion(questionId,createId,message);

    }
}
