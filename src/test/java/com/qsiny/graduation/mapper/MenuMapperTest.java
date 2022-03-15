package com.qsiny.graduation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qsiny.graduation.pojo.Job;
import com.qsiny.graduation.pojo.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuMapperTest {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private JobMapper jobMapper;

    @Test
    void selectPermsByUserId() {

        List<Message> messages = messageMapper.showSentMessage("201806010301");
        messages.forEach(System.out::println);
    }

    @Test
    void selectJobIds(){
        String gradeId = "2018";
        String professionId = "01";
        String classId ="03";
        //查询数据库
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("job_id").eq("grade_id",gradeId).eq("profession_id",professionId).eq("class_id",classId);
        System.out.println(jobMapper.selectObjs(queryWrapper));

    }
}