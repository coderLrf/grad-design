package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.*;
import com.example.designtopicselectionsystem.mapper.ChatRecordMapper;
import com.example.designtopicselectionsystem.mapper.UserMapper;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import com.example.designtopicselectionsystem.utils.Commons;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private UserService userService;

    // 插入一条留言
    public ResponseJson incrementRecord(ChatRecord chatRecord) {
        // 查询教师id和学生id是否存在
        Teacher teacher = teacherService.findById(chatRecord.getTeacher_id());
        Student student = studentService.findById(chatRecord.getStudent_id());
        if(teacher == null || student == null) {
            return ResponseJsonUtil.error(-1, "留言失败，id不存在.");
        }
        // 设置创建时间
        chatRecord.setCreate_time(new Date().getTime());
        chatRecordMapper.incrementRecord(chatRecord);
        return ResponseJsonUtil.success("留言成功.");
    }

    // 查询所有留言记录
    public List<ChatRecordAdmin> getChatRecordList() {
        List<ChatRecordAdmin> chatRecords = getChatRecordsAdmin(chatRecordMapper.selectAllRecord());
        // 设置留言方姓名和接收方姓名
        for (ChatRecordAdmin chatRecord : chatRecords) {
            // 取出留言方id
            Integer sideId = chatRecord.getMessage_side();
            // 判断if如果留言方id与教师符合，则表示留言方是教师
            if(sideId == chatRecord.getTeacher_id()) {
                // 设置留言方姓名和接收方姓名
                chatRecord.setRecordName(chatRecord.getTeacher().getTeacher_name());
                chatRecord.setReceiverName(chatRecord.getStudent().getStudent_name());
            } else {
                // 否则留言方为学生，接收方为教师
                chatRecord.setRecordName(chatRecord.getStudent().getStudent_name());
                chatRecord.setReceiverName(chatRecord.getTeacher().getTeacher_name());
            }
        }
        return chatRecords;
    }

    // 根据留言方或者接收方来进行精确搜索
    public List<ChatRecordAdmin> searchRecordPrecise(String content) {
        List<ChatRecordAdmin> chatRecordList = getChatRecordList();
        // 如果关键字为空，返回所有
        if(StringUtils.isBlank(content)) {
            return chatRecordList;
        }
        // 定义一个空的集合用户返回
        List<ChatRecordAdmin> chatRecordRes = new ArrayList<>();
        for (ChatRecordAdmin recordAdmin : chatRecordList) {
            // 如果等于留言方或者接受方相等，则添加chatRecordRes
            if(recordAdmin.getRecordName().equals(content) || recordAdmin.getReceiverName().equals(content)) {
                chatRecordRes.add(recordAdmin);
            }
        }
        return chatRecordRes;
    }

    // 查询教师和学生的留言记录
    public List<ChatRecord> selectABRecord(Integer teacherId, Integer studentId) {
        List<ChatRecord> chatRecords = getChatRecords(chatRecordMapper.selectABRecord(teacherId, studentId));
        return chatRecords;
    }

    // 设置留言记录学生信息和教师信息
    private List<ChatRecord> getChatRecords(List<ChatRecord> chatRecords) {
        if(chatRecords == null) return null;
        for (ChatRecord chatRecord : chatRecords) {
            Integer myUserId;
            // 判断身份
            if(chatRecord.getTeacher_id().equals(chatRecord.getMessage_side())) {
                myUserId = chatRecord.getStudent_id();
            } else {
                myUserId = chatRecord.getTeacher_id();
            }
            User myUser = userService.findById(myUserId + "");
            User messageUser = userService.findById(chatRecord.getMessage_side() + "");
            String path = userService.selectIconById(chatRecord.getMessage_side() + "");
            chatRecord.setMyUser(myUser); // 设置发送方对象
            chatRecord.setMessageUser(messageUser); //设置接收方对象
            chatRecord.setUserPath(path); // 设置用户icon
        }
        return chatRecords;
    }

    // 设置留言记录学生信息和教师信息
    private List<ChatRecordAdmin> getChatRecordsAdmin(List<ChatRecordAdmin> chatRecords) {
        if(chatRecords == null) return null;
        for (ChatRecordAdmin chatRecord : chatRecords) {
            Teacher teacher = teacherService.findById(chatRecord.getTeacher_id());
            Student student = studentService.findById(chatRecord.getStudent_id());
            chatRecord.setTeacher(teacher);
            chatRecord.setStudent(student);
        }
        return chatRecords;
    }

    // 切换留言状态（根据留言id）
    public void toggleRecordState(Integer id) {
        chatRecordMapper.toggleRecordState(id);
    }

}
