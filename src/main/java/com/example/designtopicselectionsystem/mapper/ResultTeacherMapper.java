package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.ResultStudent;
import com.example.designtopicselectionsystem.domain.ResultTeacher;
import com.example.designtopicselectionsystem.domain.ResultTeacherUser;
import com.example.designtopicselectionsystem.domain.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResultTeacherMapper {

    // 查询institute_no不为空的数据
    @Select("select * " +
            "from teacher t, institute i " +
            "where t.institute_no = i.institute_no")
    public List<ResultTeacher> findAll();

    // 查询institute_no为空的数据
    @Select("select * from teacher where institute_no is null")
    public List<ResultTeacher> selectAll();

    @Select("select * from topic t, teacher tea where t.teacher_no = tea.teacher_no")
    public List<ResultTeacherUser> findAllAndTopic();

    // 模糊查询
    @Select("select * from teacher where teacher_no like #{content} or teacher_name like #{content}")
    public List<ResultTeacher> searchTeacher(String content);
}
