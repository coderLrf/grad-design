package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.ResultTopic;
import com.example.designtopicselectionsystem.domain.Teacher;
import com.example.designtopicselectionsystem.domain.Topic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TopicMapper {


    @Select("select * from topic where title_name = #{topicName}")
    public ResultTopic selectByTopicName(String topicName);

    // 查询所有需要审核的课题
    @Select("select * from topic t, teacher tea where t.teacher_no = tea.teacher_no and t.admission = #{type}")
    public List<ResultTopic> selectByAdmission(String type);

    @Select("select * from topic t, teacher tea where t.teacher_no = tea.teacher_no and admission is null")
    public List<ResultTopic> wantExamineTopic();

    // 查询所有不通过审核的课题
    @Select("select * from topic t, teacher tea where t.teacher_no = tea.teacher_no and admission = '否'")
    public List<ResultTopic> selectByAdmissionFalse();

    // 根据课题id返回该课题的定选人数
    @Select("select count(*) from student where topic_no = #{id}")
    public int selectCountByTopicId(Integer id);

    // 查询教师的课题
    @Select("select * from topic t, teacher tea where t.teacher_no = #{teacherId} and t.teacher_no = tea.teacher_no")
    public List<ResultTopic> selectTopicByTeacherId(Integer teacherId);

    // 根据状态查询该教师的课题
    @Select("select * from topic t, teacher tea where t.teacher_no = tea.teacher_no and tea.teacher_no = #{teacherId} and t.admission = #{type}")
    public List<ResultTopic> selectTopicByTeacherIdAndType(@Param("teacherId") Integer teacherId,
                                                           @Param("type") String type);

    // 根据教师id查询课题数量
    @Select("select sum(1) from topic where teacher_no = #{teacherId}")
    public Integer calcTopicCountByTeacherId(Integer teacherId);

    // 插入一条数据
    @Insert("insert into topic(title_name, title_desc, teacher_no) " +
            "values(#{topicName}, #{topicDesc}, #{teacherId})")
    public int saveTopic(Topic topic);

    // 修改一条数据
    @Update("update topic set title_name = #{topicName}, title_desc = #{topicDesc}, teacher_no = #{teacherId} " +
            "where title_no = #{topicId}")
    public void updateTopic(Topic topic);

    // 将一个课题设置不可预选状态
    @Update("update topic set state = 0 where title_no = ${topicId}")
    public void setTopicEnabled(Integer topicId);

    // 通过一个课题
    @Update("update topic set admission = '是' where title_no = ${id}")
    public int passTopicById(Integer id);

    @Update("update topic set admission = '否' where title_no = ${id}")
    public int noPassTopicById(Integer id);

    @Delete("delete from topic where title_no = #{id}")
    public int deleteTopic(Integer id);

}
