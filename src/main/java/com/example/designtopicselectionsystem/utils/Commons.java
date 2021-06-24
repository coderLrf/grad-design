package com.example.designtopicselectionsystem.utils;

import org.springframework.stereotype.Component;

import java.io.File;
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

    /**
     * 时间戳转换为时间对象
     * @param date 时间戳
     * @return 时间格式 yyyy-MM-dd
     */
    public static String dateFormatLong(Long date) {
        System.out.println(date);
        Date now = new Date(date);
        return dateFormat(now);
    }

    /**
     * 返回教师等级
     * @param degree 1 | 2 | 3
     * @return 等级
     */
    public static String getDegree(String degree) {
        switch(degree) {
            case "1":
                degree = "初级教师";
                break;
            case "2":
                degree = "中级教师";
                break;
            case "3":
                degree = "高级教师";
        }
        return degree;
    }

    // 返回文件下载路径
    public static String getDirPath() {
        String dirPath = new File("src\\main\\resources\\static\\upload").getPath();
        return dirPath;
    }

}
