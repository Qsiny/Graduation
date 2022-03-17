package com.qsiny.graduation.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.pojo.RoleApply;
import com.qsiny.graduation.service.RoleApplyService;
import com.qsiny.graduation.mapper.RoleApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Qsiny
* @description 针对表【sys_role_apply(角色权限申请表)】的数据库操作Service实现
* @createDate 2022-03-17 15:02:13
*/
@Service
public class RoleApplyServiceImpl implements RoleApplyService{

    @Autowired
    private RoleApplyMapper roleApplyMapper;

    @Override
    public ResponseResult addRoleApply(Long userId, String realName, String email, int userType, String gradeId, String facultyId, String professionId, String classId) {
        RoleApply roleApply = new RoleApply();
        roleApply.setUserId(userId);
        roleApply.setRealName(realName);
        roleApply.setEamil(email);
        roleApply.setUserType(userType);
        roleApply.setGradeId(gradeId);
        roleApply.setFacultyId(facultyId);
        roleApply.setProfessionId(professionId);
        roleApply.setClassId(classId);
        roleApply.setApplyTime(new Date(System.currentTimeMillis()));
        int column = roleApplyMapper.insert(roleApply);
        if(column == 0){
            return new ResponseResult(404,"申请失败");
        }else{
            return new ResponseResult(200,"申请成功");
        }
    }

    /**
     * 申请老师角色使用此方法
     * @param userId
     * @param realName
     * @param email
     * @param userType
     * @param gradeId
     * @param facultyId
     * @param professionId
     * @return
     */
    @Override
    public ResponseResult addRoleApply(Long userId, String realName, String email, int userType, String gradeId, String facultyId, String professionId) {
       return addRoleApply(userId, realName, email, userType, gradeId, facultyId, professionId,null);
    }


    @Override
    public ResponseResult showApply(String facultyId) {
        LambdaQueryWrapper<RoleApply> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoleApply::getFacultyId,facultyId).eq(RoleApply::getUserType,"1");
        List<RoleApply> studentsApply = roleApplyMapper.selectList(lambdaQueryWrapper);
        if(studentsApply == null || studentsApply.size() == 0){
            return new ResponseResult(404,"暂无学生的申请记录");
        }
        return new ResponseResult(200,"查询到以下学生的学生申请",studentsApply);
    }

    @Override
    public ResponseResult showApply() {
        LambdaQueryWrapper<RoleApply> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoleApply::getUserType,"2");
        List<RoleApply> studentsApply = roleApplyMapper.selectList(lambdaQueryWrapper);
        if(studentsApply == null || studentsApply.size() == 0){
            return new ResponseResult(404,"暂无老师的申请记录");
        }
        return new ResponseResult(200,"查询到以下学生的学生申请",studentsApply);
    }

    /**
     * 通用方法，老师和管理员都通过此方法来确认申请请求
     * @param applyId
     * @param jobId
     * @return
     */
    @Override
    public ResponseResult acceptApply(Long applyId, String jobId) {
        LambdaUpdateWrapper<RoleApply> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(RoleApply::getId,applyId).set(RoleApply::getApprover,jobId)
                .set(RoleApply::getProcessingTime,new Date(System.currentTimeMillis())).set(RoleApply::getApplyFlag,1);
        int column = roleApplyMapper.update(null, lambdaUpdateWrapper);
        if(column == 0){
            return new ResponseResult(404,"此次审批不通过！");
        }else{
            return new ResponseResult(200,"确认成功!");
        }
    }
}




