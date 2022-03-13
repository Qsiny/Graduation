package com.qsiny.graduation.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Qin
 * @description: TODO
 * @date 2022/3/13 18:11
 */
@Getter
public enum UserStatusEnum {
    //`status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
    normal("0","正常"),
    disable("1","停用");

    @EnumValue
    private final String status;
    private final String statusName;

    UserStatusEnum(String status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }
}
