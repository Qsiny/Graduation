package com.qsiny.graduation.mapper;

import com.qsiny.graduation.pojo.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Qin
* @description 针对表【sys_message(消息表)】的数据库操作Mapper
* @createDate 2022-03-15 22:16:29
* @Entity com.qsiny.graduation.pojo.Message
*/
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> showSentMessage(@Param("teacherId") String teacherId);

    int updateMessage(@Param("messageId") String messageId,@Param("message") String message);

}




