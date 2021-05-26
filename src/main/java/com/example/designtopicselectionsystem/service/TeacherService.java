package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.ResultTeacher;
import com.example.designtopicselectionsystem.domain.ResultTeacherUser;
import com.example.designtopicselectionsystem.domain.Teacher;
import com.example.designtopicselectionsystem.mapper.ResultTeacherMapper;
import com.example.designtopicselectionsystem.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ResultTeacherMapper resultTeacherMapper;

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
        return teacherMapper.selectById(id);
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

}
