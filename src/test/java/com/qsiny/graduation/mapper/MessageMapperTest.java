package com.qsiny.graduation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.qsiny.graduation.pojo.Job;
import com.qsiny.graduation.pojo.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Qsiny on 2022/3/16 11:16
 */
@SpringBootTest
public class MessageMapperTest {

    @Autowired
    MessageMapper messageMapper;

    @Test
    public void test(){
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss");
//        String s = dateTimeFormatter.format(now);
        Date now = Date.from(Instant.now());

        Message message = new Message();
        message.setMessageId(System.currentTimeMillis());
        message.setCreateTime(now);
        message.setTeacherName("李老师");
        message.setTeacherId("201806010301");
        message.setMessage("大家不要交作业了");
        message.setStudentId("201806010302");
        int insert = messageMapper.insert(message);
        System.out.println(insert);
    }

    @Test
    public void test2(){
        Map<String,Object> map = new HashMap<>();
        map.put("teacher_name","李老师");
        List<Message> messages = messageMapper.selectByMap(map);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (Message message : messages) {
            Date createTime = message.getCreateTime();
            if(createTime == null){
                continue;
            }

            System.out.println(createTime);
            String format = simpleDateFormat.format(createTime);
//            String format = simpleDateFormat.format(message.getCreateTime());
            System.out.println("format："+format);
            System.out.println(message);
        }
    }

    @Test
    public void test3(){
//        Message messageObj = new Message();
//        messageObj.setMessageId(1647416967202L);
//        messageObj.setMessage("好啊好啊");
//        messageObj.setCreateTime(new Date(System.currentTimeMillis()));
//        int column = messageMapper.updateById(messageObj);
        String messageId = "1647416967202";
        String message = "好啊好啊";
        LambdaUpdateWrapper<Message> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(Message::getCreateTime,new Date(System.currentTimeMillis()))
                .set(Message::getMessage,message).eq(Message::getMessageId,messageId);
        int update = messageMapper.update(null, lambdaUpdateWrapper);
        System.out.println(update);
    }
}
