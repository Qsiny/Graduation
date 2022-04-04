package com.qsiny.graduation.controller;

import com.qsiny.graduation.pojo.ClassInformation;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.service.ClassInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Qin
 * @description: TODO
 * @date 2022/3/19 14:38
 */
@RestController("/class")
public class ClassInformationController {

    @Autowired
    private ClassInformationService classInformationService;

    @RequestMapping("/addClass")
    public ResponseResult addClass(String gradeId,String gradeName,String facultyId,String facultyName,
                                   String professionId,String professionName,String ClassId,String ClassName,
                                   String createId){
        ClassInformation classInformation = new ClassInformation();
        classInformation.setGradeId(gradeId);
        classInformation.setGradeName(gradeName);
        classInformation.setFacultyName(facultyName);
        classInformation.setFacultyId(facultyId);
        classInformation.setProfessionId(professionId);
        classInformation.setProfessionName(professionName);
        classInformation.setClassId(ClassId);
        classInformation.setClassName(ClassName);
        classInformation.setCreateBy(createId);
        classInformation.setCreateTime(new Date(System.currentTimeMillis()));
        boolean flag = classInformationService.save(classInformation);
        if(!flag){
            return new ResponseResult(404,"添加班级失败!");
        }
        return new ResponseResult(200,"添加班级成功!");
    }

    @RequestMapping("/removeClass")
    public ResponseResult removeClass(Long id){
        boolean flag = classInformationService.removeById(id);
        if(!flag){
            return new ResponseResult(404,"删除班级失败!");
        }
        return new ResponseResult(200,"删除班级失败!");
    }

    @RequestMapping("/showClassInformation")
    public ResponseResult showClassInformation(){
        List<ClassInformation> informationList = classInformationService.list();
        if(informationList == null || informationList.size() == 0){
            return new ResponseResult(404,"暂无任何班级信息");
        }
        return new ResponseResult(200,"查询到以下班级信息",informationList);
    }

    
}
