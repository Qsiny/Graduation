package com.qsiny.graduation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qsiny.graduation.pojo.ClassInformation;
import com.qsiny.graduation.service.ClassInformationService;
import com.qsiny.graduation.mapper.ClassInformationMapper;
import org.springframework.stereotype.Service;

/**
* @author Qin
* @description 针对表【sys_class_information(班级表)】的数据库操作Service实现
* @createDate 2022-03-19 14:34:39
*/
@Service
public class ClassInformationServiceImpl extends ServiceImpl<ClassInformationMapper, ClassInformation>
    implements ClassInformationService{

}




