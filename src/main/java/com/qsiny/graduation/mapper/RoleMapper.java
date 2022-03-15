package com.qsiny.graduation.mapper;

import com.qsiny.graduation.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Qsiny
* @description 针对表【sys_role(角色表)】的数据库操作Mapper
* @createDate 2022-03-16 12:33:43
* @Entity com.qsiny.graduation.pojo.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    int connectUserAndRole(@Param("userId") Long userId,@Param("roleId") Long roleId);
}




