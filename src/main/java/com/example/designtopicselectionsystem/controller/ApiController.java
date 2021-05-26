package com.example.designtopicselectionsystem.controller;

import com.example.designtopicselectionsystem.domain.*;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import com.example.designtopicselectionsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private SelectTopicService selectTopicService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login") // 登录接口
    public ResponseJson login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/password/update") // 密码修改
    public ResponseJson passwordUpdate(@RequestParam("userId") String userId,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword) {
        return loginService.passwordUpdate(userId, oldPassword, newPassword);
    }

    /**
     * 学生相关Api
     */

    /**
     * 预选一个课题，需要学生的id和课题的id
     * @param topicId 课题id
     * @param studentId 学生id
     * @return 预选结果
     */
    @PostMapping("/student/primary")
    public ResponseJson primaryTopic(@RequestParam(value = "topicId") Integer topicId,
                                     @RequestParam(value = "studentId") Integer studentId) {
        SelectTopic selectTopic = new SelectTopic();
        Student student = new Student();
        Topic topic = new Topic();
        topic.setTopicId(topicId);
        student.setStudent_no(studentId);
        selectTopic.setStudent(student);
        selectTopic.setTopic(topic);
        return selectTopicService.primaryTopic(selectTopic);
    }

    /**
     * 查询该学生可以预选的课题列表
     * @param studentId 学生id
     * @return 返回课题列表
     */
    @GetMapping("/student/admission")
    public ResponseJson selectAdmissionTopic(@RequestParam(value = "studentId") Integer studentId) {
        return selectTopicService.selectAdmissionTopic(studentId);
    }

    @GetMapping("/student/already")
    public ResponseJson alreadySelectTopic(@RequestParam(value = "studentId")Integer studentId) {
        return selectTopicService.alreadySelectTopic(studentId);
    }

    /**
     * 学生退选课题接口
     * @param studentId 学生id
     * @param topicId 课题id
     * @return 退选结果
     */
    @PostMapping("/student/back")
    public ResponseJson backSelectTopic(@RequestParam(value = "studentId")Integer studentId,
                                        @RequestParam(value = "topicId")Integer topicId) {
        return selectTopicService.backSelectTopic(studentId, topicId);
    }

    /**
     * 教师相关Api
     */

    /**
     * 返回学生列表
     * @param teacherId 教师id
     * @return 返回所有预选该教师课题的学生
     */
    @GetMapping("/teacher/selectPrimary")
    public ResponseJson selectPrimaryTopic(@RequestParam(value = "teacherId")Integer teacherId) {
        return selectTopicService.selectPrimaryTopic(teacherId);
    }

    /**
     * 定选一个学生的课题
     * @param topicId 课题id
     * @param studentId 学生id
     * @return 预选结果
     */
    @PostMapping("/topic/selectPrimary")
    public ResponseJson selectPrimaryStudent(@RequestParam(value = "topicId")Integer topicId,
                                             @RequestParam(value = "studentId")Integer studentId) {
        int row = studentService.selectPrimary(topicId, studentId);
        System.out.println("row:" + row);
        if(row != 0) {
            // 在学生选择了该课题后，需要删除该学生预选的其他课题
            selectTopicService.deleteSelectTopic(studentId); // 删除预选课题
            return ResponseJsonUtil.success("成功定选课题.");

        }
        return ResponseJsonUtil.error(-1, "选题失败.");
    }

    /**
     * 教师可以不定选该学生的课题（不定选）
     * @param topicId 课题id
     * @param studentId 学生id
     * @return void
     */
    @PostMapping("/topic/no/passTopic")
    public ResponseJson noPassPrimaryTopic(@RequestParam(value = "topicId")Integer topicId,
                                           @RequestParam(value = "studentId")Integer studentId) {
        return selectTopicService.deletePrimaryTopic(topicId, studentId);
    }

    /**
     * 教师新增一个课题
     * @param topic 课题
     * @return 添加结果
     */
    @PostMapping("/teacher/add/topic")
    public ResponseJson teacherAddTopic(@RequestBody Topic topic) {
        return topicService.saveTopic(topic);
    }

    /**
     * 课题相关Api
     */

    /**
     * 查询所有通过审核的课题，学生端使用
     * @return 返回查询结果
     */
    @GetMapping("/topic/pass") // 查询所有通过审核的课题
    public ResponseJson selectTopicPass() {
        List<ResultTopic> topicList = topicService.findByAdmissionTrueTow();
        return ResponseJsonUtil.successData(topicList);
    }

    /**
     * 查询所有未通过审核的课题，管理员端使用
     * @return  返回查询的结果
     */
    @GetMapping("/topic/no/pass") // 查询所有未通过审核的课题
    public ResponseJson selectTopicNoPass() {
        List<ResultTopic> topicList = topicService.findByAdmissionFalse();
        return ResponseJsonUtil.successData(topicList);
    }
}
