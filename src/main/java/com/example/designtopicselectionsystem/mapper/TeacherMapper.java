package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.Student;
import com.example.designtopicselectionsystem.domain.Teacher;
import com.example.designtopicselectionsystem.domain.TeacherCount;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("select * from teacher")
    public List<Teacher> selectAll();

    @Select("select * from teacher where teacher_no = #{id}")
    public Teacher selectById(Integer id);

    @Select("select teacher_no from teacher order by teacher_no desc limit 1")
    public int selectLastById();

    // 查询最大的教师号
    @Select("select max(teacher_no) from teacher")
    public Integer selectNextTeacherId();

    // 模糊查询
    @Select("select * from teacher where teacher_no like #{content} or teacher_name like #{content}")
    public List<Teacher> searchTeacher(String content);

    // 查询教师课题每位的学生人数
    @Select("select tea.teacher_name, tea.teacher_no, count(1) as count " +
            "from student stu, topic t, teacher tea " +
            "where stu.topic_no = t.title_no and t.teacher_no = tea.teacher_no group by tea.teacher_name")
    public List<TeacherCount> selectTeacherCount();

    // 插入一条数据
    @Insert("insert into teacher(teacher_name, sex, degree, institute_no) " +
            "values(#{teacher_name}, #{sex}, #{degree}, #{institute_no})")
    @Options(useGeneratedKeys = true, keyProperty = "teacher_no", keyColumn = "teacher_no")
    public int saveTeacher(Teacher teacher);

    // 修改一条数据
    @Update("update teacher set teacher_name = #{teacher_name}, sex = #{sex}, degree = #{degree}, institute_no = #{institute_no} " +
            "where teacher_no = #{teacher_no}")
    public int updateTeacher(Teacher teacher);

    @Delete("delete from teacher where teacher_no = #{id}")
    public int deleteTeacher(Integer id);

}
