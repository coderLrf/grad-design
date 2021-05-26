package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.ResultStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResultStudentMapper {

    @Select("select * " +
            "from student stu, class c, institute i, major m " +
            "where stu.class_no = c.class_no and c.major_no = m.major_no and m.institute_no = i.institute_no")
    public List<ResultStudent> findAll();

    @Select("select * from student where sex is null")
    public List<ResultStudent> selectAll();
}
