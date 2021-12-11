package com.qsiny.graduation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(tel, user.tel) && Objects.equals(email, user.email) && Objects.equals(cid, user.cid) && Objects.equals(relName, user.relName) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, tel, email, cid, relName, role);
    }
}
