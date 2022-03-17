package com.qsiny.graduation.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.qsiny.graduation.enums.UserTypeEnum;
import com.qsiny.graduation.mapper.JobMapper;
import com.qsiny.graduation.mapper.RoleMapper;
import com.qsiny.graduation.mapper.UserMapper;
import com.qsiny.graduation.pojo.Job;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.pojo.RoleApply;
import com.qsiny.graduation.pojo.User;
import com.qsiny.graduation.service.RoleApplyService;
import com.qsiny.graduation.mapper.RoleApplyMapper;
import com.qsiny.graduation.service.RoleService;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private RoleService roleService;

    /**
     * 申请学生角色使用此方法
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
    public ResponseResult addRoleApply(Long userId, String realName, String email, String userType, String gradeId, String facultyId, String professionId, String classId) {
        RoleApply roleApply = new RoleApply();
        roleApply.setUserId(userId);
        roleApply.setRealName(realName);
        roleApply.setEmail(email);
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
    public ResponseResult addRoleApply(Long userId, String realName, String email, String userType, String gradeId, String facultyId, String professionId) {
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
        return new ResponseResult(200,"查询到以下的学生申请",studentsApply);
    }

    @Override
    public ResponseResult showApply() {
        LambdaQueryWrapper<RoleApply> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RoleApply::getUserType,"2");
        List<RoleApply> studentsApply = roleApplyMapper.selectList(lambdaQueryWrapper);
        if(studentsApply == null || studentsApply.size() == 0){
            return new ResponseResult(404,"暂无老师的申请记录");
        }
        return new ResponseResult(200,"查询到以下的老师申请",studentsApply);
    }

    /**
     * 通用方法，老师和管理员都通过此方法来确认申请请求
     * 需要做的事：生成新的jobID以及对应的job表，更新user表，
     * 配置对应的user和teacher角色
     * @param applyId
     * @param approverId
     * @return
     */
    @Override
    public ResponseResult acceptApply(Long applyId, String approverId) {
        //以下是更新用户信息的操作
        //先获取到当前确认更新的对象
        RoleApply roleApply = roleApplyMapper.selectById(applyId);
        Job job = new Job();

        job.setGradeId(roleApply.getGradeId());
        job.setFacultyId(roleApply.getFacultyId());
        job.setProfessionId(roleApply.getProfessionId());
        job.setClassId(roleApply.getClassId());
        //TODO 根据用户的学院ID和专业ID找到对应的学院名和专业名

        //工作ID根据上面的条件进行拼装
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        Integer count = jobMapper.selectCount(queryWrapper);
        String jobId;
        //工作ID根据上面的进行拼接
        //如果是申请的学生权限 则2位数字 如果是老师的话 则是4为数字
        if(UserTypeEnum.STUDENT.getUserType().equals(roleApply.getUserType())){
            jobId = roleApply.getGradeId()+roleApply.getFacultyId()+roleApply.getProfessionId()+roleApply.getClassId()+String.format("%02d",count);
        }else{
            jobId = roleApply.getGradeId()+roleApply.getFacultyId()+roleApply.getProfessionId()+String.format("%04d",count);
        }
        job.setJobId(jobId);
        //新增角色到工作表
        int insert = jobMapper.insert(job);
        if(insert == 0){
            throw new RuntimeException("在确认过程中新增工作表出现错误！");
        }

        //修改用户信息
        User user = new User();
        user.setId(roleApply.getUserId());
        user.setUserType(roleApply.getUserType());
        user.setEmail(roleApply.getEmail());
        user.setRealName(roleApply.getRealName());
        user.setJobId(jobId);
        user.setUpdateBy(approverId);
        user.setUpdateTime(new Date(System.currentTimeMillis()));
        //更新USER表中的字段信息
        int update = userMapper.updateById(user);
        if(update == 0){
            throw new RuntimeException("在确认过程中修改用户信息出现错误！");
        }

        //更新信息放入最后 避免修改状态后 找不到该信息
        LambdaUpdateWrapper<RoleApply> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(RoleApply::getId,applyId).set(RoleApply::getApprover,approverId)
                .set(RoleApply::getProcessingTime,new Date(System.currentTimeMillis())).set(RoleApply::getApplyFlag,1);
        int column = roleApplyMapper.update(null, lambdaUpdateWrapper);
        if(column == 0){
            throw new RuntimeException("确认用户更新时发生错误！");
        }

        //更新用户角色表
        //根据要申请的角色的type
        Long roleKey;
        if(UserTypeEnum.STUDENT.getUserType().equals(roleApply.getUserType())){
            roleKey = roleService.selectRoleIdByRoleKey("student");
        }else{
            roleKey = roleService.selectRoleIdByRoleKey("teacher");
        }
        roleService.connectUserAndRole(roleApply.getUserId(),roleKey);

        return new ResponseResult(200,"确认成功！");
    }
}




