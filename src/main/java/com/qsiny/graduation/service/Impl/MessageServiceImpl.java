package com.qsiny.graduation.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qsiny.graduation.pojo.Message;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.service.MessageService;
import com.qsiny.graduation.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* @author Qin
* @description 针对表【sys_message(消息表)】的数据库操作Service实现
* @createDate 2022-03-15 22:16:29
*/
@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public ResponseResult sendMessage(List<String> studentIds, String teacherId,String teacherName, String message) {

        //保证时间的统一 同时用毫秒数来作ID避免重复
        //时间需要在配置文件修改为东八区
        long now = System.currentTimeMillis();
        for (String id : studentIds) {
            Message messageObj = new Message();
            messageObj.setMessageId(now);
            messageObj.setMessage(message);
            messageObj.setStudentId(id);
            messageObj.setTeacherId(teacherId);
            messageObj.setTeacherName(teacherName);
            messageObj.setCreateTime(new Date(now));
            messageMapper.insert(messageObj);
        }

        return new ResponseResult(200,"消息发送成功");
    }

    @Override
    public ResponseResult teacherSentMessage(String teacherJobId) {
        List<Message> messages = messageMapper.showSentMessage(teacherJobId);
        if(messages == null || messages.size() == 0){
            return new ResponseResult(404,"未查询到任何信息");
        }
        return new ResponseResult(200,"查询到以下以发送过的信息",messages);
    }

    @Override
    public ResponseResult updateMessage(Long messageId, String message) {
        //修改信息的消息体和发消息的时间
        LambdaUpdateWrapper<Message> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(Message::getCreateTime,new Date(System.currentTimeMillis()))
                .set(Message::getMessage,message).eq(Message::getMessageId,messageId);
        int column = messageMapper.update(null, lambdaUpdateWrapper);
        if(column == 0){
            return new ResponseResult(404,"修改失败");
        }else{
            return new ResponseResult(200,"以修改成功："+column+"列");
        }
    }

    @Override
    public ResponseResult studentMessages(String studentJobId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentJobId);
        List<Message> messages = messageMapper.selectList(queryWrapper);
        if(messages == null || messages.size() == 0){
            return new ResponseResult(404,"未查询到任何信息");
        }

        return new ResponseResult(200,"查询到以下信息",messages);
    }

    @Override
    public ResponseResult studentCheckMessage(Long messageId) {
        LambdaUpdateWrapper<Message> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Message::getMessageId,messageId).set(Message::getConfirmFlag,1);
        int column = messageMapper.update(null,lambdaUpdateWrapper);
        if(column == 0){
            return new ResponseResult(404,"确认失败");
        }else{
            return new ResponseResult(200,"确认成功");
        }

    }


}




