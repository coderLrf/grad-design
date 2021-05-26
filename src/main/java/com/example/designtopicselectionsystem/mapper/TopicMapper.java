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
    @Select("select * from topic t, teacher tea where t.teacher_no = tea.teacher_no and admission = #{state}")
    public List<ResultTopic> selectByAdmission(String state);

    @Select("select * from topic t, teacher tea where t.teacher_no = tea.teacher_no and admission is null")
    public List<ResultTopic> wantExamineTopic();

    // 查询所有不通过审核的课题
    @Select("select * from topic t, teacher tea where t.teacher_no = tea.teacher_no and admission = '否'")
    public List<ResultTopic> selectByAdmissionFalse();

    // 插入一条数据
    @Insert("insert into topic(title_name, title_desc, teacher_no) " +
            "values(#{topicName}, #{topicDesc}, #{teacherId})")
    public int saveTopic(Topic topic);

    // 修改一条数据
    @Update("update topic set title_name = #{topicName}, title_desc = #{topicDesc}, teacher_no = #{teacherId} " +
            "where title_no = #{topicId}")
    public int updateTopic(Topic topic);

    // 通过一个课题
    @Update("update topic set admission = '是' where title_no = ${id}")
    public int passTopicById(Integer id);

    @Update("update topic set admission = '否' where title_no = ${id}")
    public int noPassTopicById(Integer id);

    @Delete("delete from topic where title_no = #{id}")
    public int deleteTopic(Integer id);

}
