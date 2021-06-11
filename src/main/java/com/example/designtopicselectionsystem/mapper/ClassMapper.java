package com.example.designtopicselectionsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClassMapper {

    @Select("select class_name from class where class_no = #{classId}")
    public String getClassName(Integer classId);
}
