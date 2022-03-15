package com.qsiny.graduation.controller;

import com.qsiny.graduation.mapper.JobMapper;
import com.qsiny.graduation.mapper.MessageMapper;
import com.qsiny.graduation.mapper.UserMapper;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.service.JobService;
import com.qsiny.graduation.service.MessageService;
import com.qsiny.graduation.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Qsiny on 2022/3/16 8:58
 * @author Qsiny
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private JobService jobService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/sendMessage")
    public ResponseResult sendMessage(String gradeId,String facultyId,String professionId,String classId,
                              String teacherId,String teacherName,String message){
        //通过年级专业班级先找到对应的学生ID
        List<String> jobIds = jobService.selectStudentsIdByAttributes(gradeId, facultyId,professionId, classId);
        //再通过批量发消息的功能创建sql语句 存储到数据库
        return messageService.sendMessage(jobIds, teacherId, teacherName, message);
    }

    /**
     * 老师查看已经发送过的消息
     * @param teacherId
     * @return
     */
    @RequestMapping("/teacherCheckMessage")
    public ResponseResult teacherCheckMessage(String teacherId){
        if(!StringUtils.hasText(teacherId)){
            throw new RuntimeException("未传入教师ID");
        }
        return messageService.teacherSentMessage(teacherId);
    }

    /**
     * 老师端的消息重发
     * @param messageId
     * @param message
     * @return
     */
    @RequestMapping("/reSendMessage")
    public ResponseResult reSendMessage(Long messageId,String message){

        return messageService.updateMessage(messageId,message);
    }

    /**
     * 学生查询消息
     * @param studentJobId
     * @return
     */
    @RequestMapping("/studentMessages")
    public ResponseResult studentMessages(String studentJobId){
        if(!StringUtils.hasText(studentJobId)){
            throw new RuntimeException("未传入学生ID");
        }
        return messageService.studentMessages(studentJobId);
    }

    /**
     * 学生确认收到消息
     * @param messageId
     * @return
     */
    @RequestMapping("/studentCheckMessage")
    public ResponseResult studentCheckMessage(Long messageId){
        return  messageService.studentCheckMessage(messageId);
    }
}
