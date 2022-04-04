package com.qsiny.graduation.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.qsiny.graduation.mapper.QuestionMapper;
import com.qsiny.graduation.pojo.Question;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Qin
* @description 针对表【sys_question(问题表)】的数据库操作Service实现
* @createDate 2022-03-19 12:30:21
*/
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public ResponseResult addQuestion(Long createId, String question) {
        Question questionObj = new Question();
        questionObj.setQuestion(question);
        questionObj.setCreateTime(new Date(System.currentTimeMillis()));
        questionObj.setCreateBy(createId);
        int column = questionMapper.insert(questionObj);
        if(column == 0){
            return new ResponseResult(404,"问题提交失败！");
        }
        return new ResponseResult(200,"问题提交成功！");
    }

    @Override
    public ResponseResult selectQuestionById(Long createId) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Question::getCreateBy,createId);
        List<Question> questions = questionMapper.selectList(lambdaQueryWrapper);
        if(questions == null || questions.size() == 0){
            return new ResponseResult(200,"暂无发送过问题");
        }
        return new ResponseResult(200,"获取到以下问题",questions);
    }

    @Override
    public ResponseResult selectQuestions() {
        List<Question> questions = questionMapper.selectList(null);
        if(questions == null || questions.size() == 0){
            return new ResponseResult(200,"暂无任务需要回答的问题");
        }
        return new ResponseResult(200,"获取到以下问题",questions);
    }

    @Override
    public ResponseResult activeQuestion(Long questionId,Long activeId, String message) {
        LambdaUpdateWrapper<Question> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Question::getId,questionId).set(Question::getActiveBy,activeId).set(Question::getActiveMessage,message)
                .set(Question::getActiveTime,new Date(System.currentTimeMillis())).set(Question::getStatus,1);

        int column = questionMapper.update(null, lambdaUpdateWrapper);
        if(column == 0){
            return new ResponseResult(404,"回复问题期间出现问题！");
        }
        return new ResponseResult(200,"回复成功！");
    }
}




