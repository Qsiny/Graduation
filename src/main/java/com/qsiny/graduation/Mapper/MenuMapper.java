package com.qsiny.graduation.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qsiny.graduation.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Qin
 * @description: TODO
 * @date 2022/3/14 20:12
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(@Param("id") Long id);
}
