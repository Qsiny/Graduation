package com.qsiny.graduation.service;

import com.qsiny.graduation.pojo.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qsiny.graduation.pojo.ResponseResult;

import java.util.List;

/**
* @author Qin
* @description 针对表【sys_message(消息表)】的数据库操作Service
* @createDate 2022-03-15 22:16:29
*/
public interface MessageService {

    ResponseResult sendMessage(List<String> studentIds, String teacherId,String teacherName, String message);

    ResponseResult teacherSentMessage(String teacherJobId);

    ResponseResult studentMessages(String studentJobId);

    ResponseResult studentCheckMessage(Long messageId);

    ResponseResult updateMessage(Long messageId, String message);
}
