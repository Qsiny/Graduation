package com.qsiny.graduation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/4 23:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
    private String tel;
    private String email;
    private String cid;
    private String relName;
    private String role;

    public User(String name, String password, String tel) {
        this.name = name;
        this.password = password;
        this.tel = tel;
    }
}
