package com.example.designtopicselectionsystem.controller;

import com.example.designtopicselectionsystem.domain.*;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import com.example.designtopicselectionsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(maxAge = 3600)
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
    private FileService fileService;

    @PostMapping("/login") // 登录接口
    public ResponseJson login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/password/update") // 密码修改
    public ResponseJson passwordUpdate(@RequestParam(value = "userId", required = false) String userId,
                                       @RequestParam(value = "oldPassword", required = false) String oldPassword,
                                       @RequestParam(value = "newPassword", required = false) String newPassword) {
        if(userId == null || oldPassword == null || newPassword == null) {
            return ResponseJsonUtil.error(-1, "参数不能为空.");
        }
        return loginService.passwordUpdate(userId, oldPassword, newPassword);
    }

    /*
     * 学生相关Api
     */

    /**
     * 预选一个课题，需要学生的id和课题的id
     * @param topicId 课题id
     * @param studentId 学生id
     * @return 预选结果
     */
    @PostMapping("/student/primary")
    public ResponseJson primaryTopic(@RequestParam(value = "topicId", required = false) Integer topicId,
                                     @RequestParam(value = "studentId", required = false) Integer studentId) {
        if(topicId == null || studentId == null) return ResponseJsonUtil.error(-1, "参数不能为空.");
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
    public ResponseJson selectAdmissionTopic(@RequestParam(value = "studentId", required = false) Integer studentId) {
        if(studentId == null) return ResponseJsonUtil.error(-1, "参数不能为空.");
        return selectTopicService.selectAdmissionTopic(studentId);
    }

    /**
     * 查询该学生已经预选的课题
     * @param studentId 学生id
     * @return 预选的课题
     */
    @GetMapping("/student/already")
    public ResponseJson alreadySelectTopic(@RequestParam(value = "studentId", required = false)Integer studentId) {
        if(studentId == null) return ResponseJsonUtil.error(-1, "参数不能为空.");
        return selectTopicService.alreadySelectTopic(studentId);
    }

    /**
     * 学生退选课题接口
     * @param studentId 学生id
     * @param topicId 课题id
     * @return 退选结果
     */
    @PostMapping("/student/back")
    public ResponseJson backSelectTopic(@RequestParam(value = "studentId", required = false)Integer studentId,
                                        @RequestParam(value = "topicId", required = false)Integer topicId) {
        if(studentId == null || topicId == null) {
            return ResponseJsonUtil.error(-1, "参数不能为空.");
        }
        return selectTopicService.backSelectTopic(studentId, topicId);
    }

    @GetMapping("/student/mission")
    public ResponseJson studentMission(@RequestParam("topicId") Integer topicId) {
        if(topicId == null) {
            return ResponseJsonUtil.error(-1, "参数错误.");
        }
        return fileService.selectFilename(topicId);
    }

    @GetMapping("/student/mission/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request,
                                               @RequestParam("fileId") String fileId) {
        return fileService.fileDownload(request, fileId);
    }

    /*
      教师相关Api
     */

    /**
     * 返回学生列表
     * @param teacherId 教师id
     * @return 返回所有预选该教师课题的学生
     */
    @GetMapping("/teacher/selectPrimary")
    public ResponseJson selectPrimaryTopic(@RequestParam(value = "teacherId", required = false)Integer teacherId) {
        if(teacherId == null) return ResponseJsonUtil.error(-1, "参数不能为空.");
        return selectTopicService.selectPrimaryTopic(teacherId);
    }

    /**
     * 定选一个学生的课题
     * @param topicId 课题id
     * @param studentId 学生id
     * @return 预选结果
     */
    @PostMapping("/topic/selectPrimary")
    public ResponseJson selectPrimaryStudent(@RequestParam(value = "topicId", required = false)Integer topicId,
                                             @RequestParam(value = "studentId", required = false)Integer studentId) {
        if(topicId == null || studentId == null) {
            return ResponseJsonUtil.error(-1, "参数不能为空.");
        }
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
        if(topic == null) {
            return ResponseJsonUtil.error(-1, "参数不能为空.");
        }
        return topicService.saveTopic(topic);
    }

    /**
     * 用于教师上传任务书
     * @param topicId 课题id
     * @param fileUpload 上传的任务书
     * @return 上传结果
     */
    @PostMapping("/teacher/uploadFile/{id}")
    public ResponseJson teacherUploadFile(@PathVariable("id") Integer topicId,
                                          MultipartFile fileUpload) {
        if(fileUpload == null) {
            return ResponseJsonUtil.error(-1, "参数错误.");
        }
        return fileService.uploadFile(topicId, fileUpload);
    }

    /*
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
