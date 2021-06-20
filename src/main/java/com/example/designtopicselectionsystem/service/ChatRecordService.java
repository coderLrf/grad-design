package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.ChatRecord;
import com.example.designtopicselectionsystem.domain.Student;
import com.example.designtopicselectionsystem.domain.Teacher;
import com.example.designtopicselectionsystem.mapper.ChatRecordMapper;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChatRecordService {

    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    // 插入一条留言
    public ResponseJson incrementRecord(ChatRecord chatRecord) {
        // 查询教师id和学生id是否存在
        Teacher teacher = teacherService.findById(chatRecord.getTeacher_id());
        Student student = studentService.findById(chatRecord.getStudent_id());
        if(teacher == null || student == null) {
            return ResponseJsonUtil.error(-1, "留言失败，id不存在.");
        }
        // 设置创建时间
        chatRecord.setCreate_time(new Date());
        chatRecordMapper.incrementRecord(chatRecord);
        return ResponseJsonUtil.success("留言成功.");
    }

    // 查询教师和学生的留言记录
    public List<ChatRecord> selectABRecord(Integer teacherId, Integer studentId) {
        List<ChatRecord> chatRecords = chatRecordMapper.selectABRecord(teacherId, studentId);
        for (ChatRecord chatRecord : chatRecords) {
            Teacher teacher = teacherService.findById(chatRecord.getTeacher_id());
            Student student = studentService.findById(chatRecord.getStudent_id());
            chatRecord.setTeacher(teacher);
            chatRecord.setStudent(student);
        }
        return chatRecords;
    }

}
