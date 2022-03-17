package com.qsiny.graduation.service;

import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.pojo.RoleApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Qsiny
* @description 针对表【sys_role_apply(角色权限申请表)】的数据库操作Service
* @createDate 2022-03-17 15:02:13
*/
public interface RoleApplyService {

    ResponseResult addRoleApply(Long userId, String realName, String email, String userType, String gradeId, String facultyId, String professionId, String classId);

    ResponseResult addRoleApply(Long userId, String realName, String email, String userType, String gradeId, String facultyId, String professionId);

    ResponseResult showApply(String facultyId);

    ResponseResult showApply();

    ResponseResult acceptApply(Long applyId, String teacherJobId);
}
