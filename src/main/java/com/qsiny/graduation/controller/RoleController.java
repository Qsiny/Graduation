package com.qsiny.graduation.controller;

import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qin
 * @description: TODO
 * @date 2022/3/16 20:03
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/addRole")
    public ResponseResult addRole(String roleName,String roleKey,String creatorId){
        if(roleService.hasRole(roleName,roleKey)){
            return new ResponseResult(404,"已经存在此同名同关键字的角色了");
        }
       return roleService.addRole(roleName,roleKey,creatorId);
    }
}
