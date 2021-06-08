package com.example.designtopicselectionsystem.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 页面数据展示封装类
 */
@Component
public class Commons {

    /**
     * 返回日期
     *
     * @return ``
     */
    public static String dateFormat(Date date) {
        if(date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}
