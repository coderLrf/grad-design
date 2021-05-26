package com.example.designtopicselectionsystem;

import com.example.designtopicselectionsystem.domain.*;
import com.example.designtopicselectionsystem.mapper.*;
import com.example.designtopicselectionsystem.repository.InstituteRepository;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.service.SelectTopicService;
import com.example.designtopicselectionsystem.service.StudentService;
import com.example.designtopicselectionsystem.service.TeacherService;
import com.example.designtopicselectionsystem.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class DesignTopicSelectionSystemApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstituteMapper instituteMapper;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherService teacherService;

    @Test
    void selectById() {
//        Integer id = 2019101010;
//        Student student = studentMapper.selectById(id);
//        System.out.println(student);

//        List<Student> studentList = studentMapper.selectAll();
//        for (Student student : studentList) {
//            System.out.println(student);
//        }

        Student student = new Student();
        student.setStudent_name("钟馗");
        student.setSex("女");
        student.setBirthday(Date.valueOf("2019-05-29"));
        student.setClass_no("5001");
        int save = studentMapper.saveStudent(student);
        System.out.println(save);
    }

    @Test
    void selectTow() {
        List<ResultTeacherUser> allTow = teacherService.findAllTow();
        for (ResultTeacherUser resultTeacher : allTow) {
            System.out.println(resultTeacher);
        }
    }

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Test
    void findByTeacherId() {
//        Integer teacherId = 20190101;
//        List<Topic> list = topicService.findByTeacherId(teacherId);
//        System.out.println(list.toString());

        String topicName = "springboot";
        ResultTopic topic = topicMapper.selectByTopicName(topicName);
        System.out.println(topic);
    }

    @Test
    void findStudentAll() {
        List<ResultStudent> allTow = studentService.findAllTow();
        for (ResultStudent resultStudent : allTow) {
            System.out.println(resultStudent);
        }
    }

    @Test
    void saveStudent() {
        Student student = new Student();
        student.setStudent_name("张菲");
        student.setClass_no("5000");
        student.setBirthday(Date.valueOf("2019-5-6"));
        student.setSex("男");
        studentService.save(student);
    }

    @Test
    void findInstituteAll() {
        List<Institute> all = instituteRepository.findAll();
        for (Institute institute : all) {
            System.out.println(institute);
        }
    }

    @Test
    void selectTopic() {
        List<ResultTopic> byAdmissionTrueTow = topicService.findByAdmissionTrueTow();
        for (ResultTopic topic : byAdmissionTrueTow) {
            System.out.println(topic);
        }
    }

    @Autowired
    private SelectTopicService selectTopicService;

    @Autowired
    private SelectTopicMapper selectTopicMapper;

    @Test
    void selectPrimaryTopic() {
        Integer teacherId = 20190105;
        List<ResultSelectTopic> resultSelectTopics = selectTopicMapper.selectByTeacherId(teacherId);
        for (ResultSelectTopic resultSelectTopic : resultSelectTopics) {
            System.out.println(resultSelectTopic);
        }
    }

    @Test
    void selectPrimaryTopic2() {
        Integer studentId = 2019101012;
        ResultTopic resultTopic = studentService.selectPrimaryTopic(studentId);
        if(resultTopic != null) { // 如果有定选课题
            System.out.println("有定选课题");
        } else {
            System.out.println("没有定选课题");
        }
    }

}
