package com.qsiny.graduation.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.pojo.Role;
import com.qsiny.graduation.service.RoleService;
import com.qsiny.graduation.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Qsiny
* @description 针对表【sys_role(角色表)】的数据库操作Service实现
* @createDate 2022-03-16 12:33:43
*/
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public ResponseResult addRole(String roleName, String roleKey, Long creatorId) {

        Role role = new Role();
        role.setName(roleName);
        role.setRoleKey(roleKey);
        role.setCreateBy(creatorId);
        role.setCreateTime(new Date(System.currentTimeMillis()));
        int column = roleMapper.insert(role);
        if(column == 0){
            return new ResponseResult(404,"添加角色失败");
        }else{
            return new ResponseResult(200,"添加角色成功");
        }
    }

    @Override
    public boolean hasRole(String roleName, String roleKey) {
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Role::getName,roleName).eq(Role::getRoleKey,roleKey);
        Role role = roleMapper.selectOne(lambdaQueryWrapper);
        return role == null;
    }

    @Override
    public int connectUserAndRole(Long userId, Long roleId) {
        return roleMapper.connectUserAndRole(userId, roleId);
    }

    @Override
    public Long selectRoleIdByRoleKey(String roleKey) {
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Role::getRoleKey,roleKey);
        Role role = roleMapper.selectOne(lambdaQueryWrapper);
        return role.getId();
    }


}




