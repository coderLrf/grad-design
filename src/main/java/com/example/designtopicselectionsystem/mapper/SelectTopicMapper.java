package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.ResultSelectTopic;
import com.example.designtopicselectionsystem.domain.ResultTopic;
import com.example.designtopicselectionsystem.domain.SelectTopic;
import com.example.designtopicselectionsystem.domain.Topic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SelectTopicMapper {

    // 根据教师id查询教师预选课题的学生
    @Select("select * " +
            "from selectTopic st, topic t, teacher tea, student stu " +
            "where st.title_no = t.title_no and tea.teacher_no = t.teacher_no and stu.student_no = st.student_no and tea.teacher_no = #{teacherId}")
    public List<ResultSelectTopic> selectByTeacherId(Integer teacherId);

    // 查询所有该学生可以预选的课题
    @Select("select t.*, tea.teacher_name from topic t, teacher tea " +
            "where t.teacher_no = tea.teacher_no and admission = '是' and title_no not in(select title_no from selectTopic where student_no = #{studentId});")
    public List<ResultTopic> selectAdmissionTopic(Integer studentId);

    // 查询所有该学生已经预选的课题
    @Select("select t.*, tea.teacher_name from topic t, teacher tea " +
            "where t.teacher_no = tea.teacher_no and title_no in(select title_no from selectTopic where student_no = #{studentId})")
    public List<ResultTopic> alreadySelectTopic(Integer studentId);

    // 预选一个课题
    @Insert("insert into selectTopic(title_no, student_no) values(#{topic.topicId}, #{student.student_no})")
    public int primaryTopic(SelectTopic selectTopic);

    // 删除一个预选课题根据学生
    @Delete("delete from selectTopic where student_no = #{studentId}")
    public int deleteSelectTopic(Integer studentId);

    // 删除一个预选课题根据学生id和教师id
    @Delete("delete from selectTopic where student_no = #{studentId} and title_no = #{topicId}")
    public int deletePrimaryTopic(@Param(value = "topicId")Integer topicId, @Param(value = "studentId")Integer studentId);
}
