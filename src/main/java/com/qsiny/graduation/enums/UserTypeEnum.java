package com.qsiny.graduation.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Qin
 * @description: TODO
 * @date 2022/3/13 18:37
 */
@Getter
public enum UserTypeEnum {
    //`user_type` char(1) NOT NULL DEFAULT '0' COMMENT '用户类型（0普通用户，1学生，2老师，3管理员）',
    NORMAL("0","普通用户"),
    STUDENT("1","学生"),
    TEACHER("2","老师"),
    ADMINISTRATOR("3","管理员");

    @EnumValue
    private final String userType;
    private final String userTypeName;

    UserTypeEnum(String userType, String userTypeName) {
        this.userType = userType;
        this.userTypeName = userTypeName;
    }
}
