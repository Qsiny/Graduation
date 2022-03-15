package com.qsiny.graduation.mapper;

import com.qsiny.graduation.pojo.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuMapperTest {

    @Autowired
    private MessageMapper messageMapper;

    @Test
    void selectPermsByUserId() {

        List<Message> messages = messageMapper.showSentMessage("201806010301");
        messages.forEach(System.out::println);
    }
}