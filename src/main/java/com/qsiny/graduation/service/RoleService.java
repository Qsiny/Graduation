package com.qsiny.graduation.service;

import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
* @author Qsiny
* @description 针对表【sys_role(角色表)】的数据库操作Service
* @createDate 2022-03-16 12:33:43
*/
public interface RoleService{

    ResponseResult addRole(String roleName, String roleKey, String creatorId);

    boolean hasRole(String roleName, String roleKey);

    int connectUserAndRole(Long userId, Long roleId);

    Long selectRoleIdByRoleKey(String roleKey);
}
