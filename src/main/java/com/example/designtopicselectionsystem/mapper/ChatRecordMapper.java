package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.ChatRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatRecordMapper {

    // 查询聊天记录
    @Select("select * from chat_record")
    public List<ChatRecord> selectAllRecord();

    // 根据教师id和学生id查询相关留言
    @Select("select * from chat_record where teacher_id = #{teacherId} and student_id = #{studentId} order by create_time asc")
    public List<ChatRecord> selectABRecord(@Param("teacherId") Integer teacherId, @Param("studentId") Integer studentId);

    // 添加一条留言
    @Insert("insert into chat_record(teacher_id, student_id, content, create_time, message_side) " +
            "values(#{teacher_id}, #{student_id}, #{content}, #{create_time}, #{message_side})")
    public void incrementRecord(ChatRecord chatRecord);

}
