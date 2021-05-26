package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.ResultStudent;
import com.example.designtopicselectionsystem.domain.ResultTopic;
import com.example.designtopicselectionsystem.domain.Student;
import com.example.designtopicselectionsystem.domain.User;
import com.example.designtopicselectionsystem.mapper.ResultStudentMapper;
import com.example.designtopicselectionsystem.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ResultStudentMapper resultStudentMapper;

    @Autowired
    private UserService userService;

    public List<Student> findAll() {
        List<Student> studentList = studentMapper.selectAll();
        return studentList;
    }

    public List<ResultStudent> selectAll() {
        List<ResultStudent> studentList = resultStudentMapper.findAll();
        List<ResultStudent> studentList2 = resultStudentMapper.selectAll();
        studentList.addAll(studentList2);
        return studentList;
    }

    public Student findById(Integer studentNo) {
        return studentMapper.selectById(studentNo);
    }

    public List<ResultStudent> findAllTow() {
        List<ResultStudent> all = resultStudentMapper.findAll();
        return all;
    }

    public Integer selectNextStudentId() {
        Integer studentId = studentMapper.selectNextStudentId();
        return studentId + 1;
    }

    public int save(Student student) {
        return studentMapper.saveStudent(student);
    }

    public int update(Student student) {
        int row = studentMapper.updateStudent(student);
        return row;
    }

    public int delete(Integer id) {
        return studentMapper.deleteStudent(id);
    }

    // 选择一个课题
    public int selectPrimary(Integer topicId, Integer studentId) {
        int row = studentMapper.selectPrimary(topicId, studentId);
        return row;
    }

    // 查询该学生是否有定选的课题，如果有返回该课题
    public ResultTopic selectPrimaryTopic(Integer studentId) {
        ResultTopic resultTopic = studentMapper.selectPrimaryTopic(studentId);
        return resultTopic;
    }


}
