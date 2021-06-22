package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.ResultTopic;
import com.example.designtopicselectionsystem.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from student")
    public List<Student> selectAll();

    @Select("select * from student where student_no = #{id}")
    public Student selectById(Integer id);

    // 查询该学生是否定选有课题
    @Select("select * from topic t, student stu, teacher tea " +
            "where t.title_no = stu.topic_no and stu.student_no = #{id} and tea.teacher_no = t.teacher_no")
    public ResultTopic selectPrimaryTopic(Integer id);

    // 查询课题id
    @Select("select topic_no from student where student_no = #{studentId}")
    public Integer selectTopicId(Integer studentId);

    // 获得下一个自增id的值
    @Select("select max(student_no) from student")
    public Integer selectNextStudentId();

    // 根据课题id查询该课题总数量
    @Select("select sum(1) from student where topic_no = #{topicId}")
    public Integer calcTopicCountByTopicId(Integer topicId);

    // 插入一条数据
    @Insert("insert into student(student_name, sex, birthday, class_no) " +
            "values(#{student_name}, #{sex}, #{birthday}, #{class_no})")
    @Options(useGeneratedKeys = true, keyProperty = "student_no", keyColumn = "student_no")
    public int saveStudent(Student student);

    // 修改一条数据
    @Update("update student set student_name = #{student_name}, sex = #{sex}, birthday = #{birthday}, class_no = #{class_no} " +
            "where student_no = #{student_no}")
    public int updateStudent(Student student);

    // 确定选择一个课题
    @Update("update student set topic_no = #{topicId} where student_no = #{studentId}")
    public int selectPrimary(@Param("topicId")Integer topicId, @Param("studentId")Integer studentId);

    // 删除课题对应学生清空
    @Update("update student set topic_no = null where topic_no = #{topicId}")
    public void deleteStudentTopic(Integer topicId);

    // 用于学生上传毕业设计
    @Update("update student set file = #{file} where student_no = #{studentId}")
    public void uploadFile(@Param("studentId") Integer StudentId, @Param("file") String file);

    // 根据id删除学生
    @Delete("delete from student where student_no = #{id}")
    public int deleteStudent(Integer id);

    // 根据学生id获取毕业设计
    @Select("select file from student where student_no = #{studentId}")
    public String getFile(Integer studentId);
}
