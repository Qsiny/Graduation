package com.qsiny.graduation.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/11 12:32
 */
public class WebUtils {
    public static <T>T populateObject(HttpServletRequest request, T t){
        Map<String, String[]> map = request.getParameterMap();

        try {
            BeanUtils.populate(t, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
