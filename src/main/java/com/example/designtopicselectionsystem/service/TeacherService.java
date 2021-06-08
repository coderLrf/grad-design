package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.*;
import com.example.designtopicselectionsystem.mapper.ResultTeacherMapper;
import com.example.designtopicselectionsystem.mapper.TeacherMapper;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ResultTeacherMapper resultTeacherMapper;

    @Autowired
    private UserService userService;

    public List<Teacher> findAll() {
        List<Teacher> teacherList = teacherMapper.selectAll();
        return teacherList;
    }

    public List<ResultTeacher> findAllOne() {
        List<ResultTeacher> all = resultTeacherMapper.findAll();
        return all;
    }

    public List<ResultTeacher> selectAll() {
        List<ResultTeacher> teacherList1 = resultTeacherMapper.findAll();
        List<ResultTeacher> teacherList2 = resultTeacherMapper.selectAll();
        teacherList1.addAll(teacherList2);
        return teacherList1;
    }

    public List<ResultTeacherUser> findAllTow() {
        List<ResultTeacherUser> all = resultTeacherMapper.findAllAndTopic();
        return all;
    }

    public Teacher findById(Integer id) {
        Teacher teacher = teacherMapper.selectById(id);
        teacher.setUserIcon(userService.selectIconById(id + ""));
        return teacher;
    }

    // 根据关键字搜索用户
    public List<ResultTeacher> searchTeacherByKeyWord(String content) {
        content += "%";
        return resultTeacherMapper.searchTeacher(content);
    }

    // 获取教师最后一条编号
    public int findLastId() {
        int lastId = teacherMapper.selectLastById();
        return lastId;
    }

    // 获取下一个自增id
    public Integer selectNextTeacherId() {
        Integer teacherId = teacherMapper.selectNextTeacherId();
        return teacherId + 1;
    }

    // 查询教师课题每位的学生人数
    public List<TeacherCount> selectTeacherCount() {
        List<TeacherCount> teacherCount = teacherMapper.selectTeacherCount();
        return teacherCount;
    }

    // 添加一名教师
    public int save(Teacher teacher) {
        int save = teacherMapper.saveTeacher(teacher);
        return save;
    }

    public int update(Teacher teacher) {
        int i = teacherMapper.updateTeacher(teacher);
        return i;
    }

    public int delete(Integer id) {
        int i = teacherMapper.deleteTeacher(id);
        return i;
    }

    // 保存教师信息
    public ResponseJson teacherSaveMessage(Teacher teacher) {
        int update = update(teacher);
        if(update > 1) {
            return ResponseJsonUtil.success("信息保存成功.");
        }
        return ResponseJsonUtil.error(-1, "信息保存失败.");
    }

}
