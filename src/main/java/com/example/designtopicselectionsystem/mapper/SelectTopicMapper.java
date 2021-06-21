package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SelectTopicMapper {

    // 根据教师id查询教师预选课题的学生
    @Select("select * " +
            "from selectTopic st, topic t, teacher tea, student stu " +
            "where st.title_no = t.title_no and tea.teacher_no = t.teacher_no " +
            "and stu.student_no = st.student_no and tea.teacher_no = #{teacherId}")
    public List<ResultSelectTopic> selectByTeacherId(Integer teacherId);

    // 查询所有该学生可以预选的课题
    @Select("select t.*, tea.teacher_name from topic t, teacher tea " +
            "where t.teacher_no = tea.teacher_no and t.state = 1 and admission = '是' and title_no not in(select title_no from selectTopic where student_no = #{studentId})")
    public List<ResultTopic> selectAdmissionTopic(Integer studentId);

    // 查询所有该学生已经预选的课题
    @Select("select t.*, tea.teacher_name from topic t, teacher tea " +
            "where t.teacher_no = tea.teacher_no and title_no in(select title_no from selectTopic where student_no = #{studentId})")
    public List<ResultTopic> alreadySelectTopic(Integer studentId);

    // 查询该学生定选的课题
    @Select("select * from topic t, student stu, teacher tea " +
            "where t.title_no = stu.topic_no and stu.student_no = #{id} and tea.teacher_no = t.teacher_no")
    public ResultTopic okTopicByStudentId(Integer id);

    // 查询该教师已经定选课题的所有学生
    @Select("select * from student stu, topic t " +
            "where t.teacher_no = #{teacherId} and t.title_no = stu.topic_no")
    public List<ResultSelectTopic> okSelectPrimary(Integer teacherId);

    // 根据课题名称模糊查询课题
    @Select("select * from topic t, teacher tea " +
            "where t.title_name like #{content} and t.teacher_no = tea.teacher_no and t.admission = #{state} or t.teacher_no = " +
            "(select teacher_no from teacher where teacher_name like #{con}) and t.teacher_no = tea.teacher_no and t.admission = #{state} order by t.title_no")
    public List<ResultTopic> selectTopicFuzzy(@Param("content") String content, @Param("con") String con, @Param("state") String state);

    // 根据课题名称模糊查询课题
    @Select("select * from topic t, teacher tea " +
            "where t.title_name like #{content} and t.teacher_no = tea.teacher_no and t.admission is null or t.teacher_no = " +
            "(select teacher_no from teacher where teacher_name like #{con}) and t.teacher_no = tea.teacher_no and t.admission is null order by t.title_no")
    public List<ResultTopic> selectTopicFuzzyNull(@Param("content") String content, @Param("con")String con);

    // 根据学生id查询预选课题的次数
    @Select("select sum(1) from selectTopic where student_no = #{studentId}")
    public Integer calcTopicCount(Integer studentId);

    // 预选一个课题
    @Insert("insert into selectTopic(title_no, student_no) values(#{topic.topicId}, #{student.student_no})")
    public int primaryTopic(SelectTopic selectTopic);

    // 删除一个预选课题根据学生id
    @Delete("delete from selectTopic where student_no = #{studentId}")
    public int deleteSelectTopicByStudentId(Integer studentId);

    // 删除一个预选课题根据课题id
    @Delete("delete from selectTopic where title_no = #{topicId}")
    public int deleteSelectTopicByTopicId(Integer topicId);

    // 删除一个预选课题根据学生id和教师id
    @Delete("delete from selectTopic where student_no = #{studentId} and title_no = #{topicId}")
    public int deletePrimaryTopic(@Param(value = "topicId")Integer topicId, @Param(value = "studentId")Integer studentId);
}
