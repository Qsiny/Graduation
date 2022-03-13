package com.qsiny.graduation.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Qin
 * @description: TODO
 * @date 2022/3/13 18:34
 */
@Getter
public enum UserSexEnum {
//  `sex` char(1) DEFAULT '2' COMMENT '用户性别（0男，1女，2未知）',
    MALE("0","男性"),
    FEMALE("1","女性"),
    NONE("2","未填写");

    @EnumValue
    private final String sex;
    private final String sexName;

    UserSexEnum(String sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
