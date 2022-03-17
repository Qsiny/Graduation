package com.qsiny.graduation.controller;

import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.service.RoleApplyService;
import com.qsiny.graduation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用来做权限申请相关操作
 * Created by Qsiny on 2022/3/17 15:04
 * @author Qsiny
 */
@RestController
@RequestMapping("/roleApply")
public class RoleApplyController {

    @Autowired
    private RoleApplyService roleApplyService;

    /**
     * 申请学生角色，这个功能需要设置权限：只有user用户具有这个权限，其他的ROLE不可以有这个权限
     * @param userId
     * @param realName
     * @param email
     * @param gradeId
     * @param facultyId
     * @param professionId
     * @param classId
     * @return
     */
    @RequestMapping("/applyStudentRole")
    public ResponseResult applyStudentRole(Long userId,String realName,String email,String gradeId,String facultyId,String professionId,String classId){
        return roleApplyService.addRoleApply(userId,realName,email,"1",gradeId,facultyId,professionId,classId);

    }

    /**
     * 申请老师角色 这个功能需要设置权限：只有user和student用户具有这个权限，其他的ROLE不可以有这个权限
     * @param userId
     * @param realName
     * @param email
     * @param gradeId
     * @param facultyId
     * @param professionId
     * @return
     */
    @RequestMapping("/applyTeacherRole")
    public ResponseResult applyTeacherRole(Long userId,String realName,String email,String gradeId,String facultyId,String professionId){
        return roleApplyService.addRoleApply(userId,realName,email,"2",gradeId,facultyId,professionId);
    }

    /**
     * 根据老师的学院ID，去查看对应学院的学生申请
     * @param facultyId
     * @return
     */
    @RequestMapping("/showStudentApply")
    public ResponseResult showStudentApply(String facultyId){
        return roleApplyService.showApply(facultyId);
    }

    /**
     * 管理员查看所有老师的申请
     * @return
     */
    @RequestMapping("/showTeacherApply")
    public ResponseResult showStudentApply(){
        return roleApplyService.showApply();
    }

    /**
     * 老师确认学生的请求，并更新学生的信息
     * @param applyId 申请的ID
     * @param teacherJobId
     * @return
     */
    @RequestMapping("/acceptStudentApply")
    public ResponseResult acceptStudentApply(Long applyId,String teacherJobId){
        return roleApplyService.acceptApply(applyId,teacherJobId);
    }

    /**
     * 管理员确认老师的请求
     * @param applyId
     * @param administratorJobId
     * @return
     */
    @RequestMapping("/acceptTeacherApply")
    public ResponseResult acceptTeacherApply(Long applyId,String administratorJobId){
        return roleApplyService.acceptApply(applyId,administratorJobId);
    }






}
