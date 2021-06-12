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
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 用户前端页面进行交互，API接口
 */
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    /*
     * 用户相关Api
     */

    /**
     * 登录接口
     * @param user 用户对象，userID，password，identity
     * @return 登录结果（response）
     */
    @PostMapping("/login") // 登录接口
    public ResponseJson login(HttpServletRequest request,
                              @RequestBody User user) {
        return loginService.login(request, user);
    }

    /**
     * 用户可以进行密码修改
     * @param userId 用户id
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return 修改结果
     */
    @PostMapping("/password/update") // 密码修改
    public ResponseJson passwordUpdate(@RequestParam(value = "userId", required = false) String userId,
                                       @RequestParam(value = "oldPassword", required = false) String oldPassword,
                                       @RequestParam(value = "newPassword", required = false) String newPassword) {
        if(userId == null || oldPassword == null || newPassword == null) {
            return ResponseJsonUtil.error(-1, "参数不能为空.");
        }
        return loginService.passwordUpdate(userId, oldPassword, newPassword);
    }

    // 获取当前登录的用户对象
    @GetMapping("/user/get")
    public ResponseJson getUser(HttpServletRequest request) {
        return userService.getUser(request);
    }

    /**
     * 用户上传头像
     * @param iconUpload 上传的icon
     * @param userId 用户的id
     * @return Response
     */
    @PostMapping("/user/upload_icon/{id}")
    public ResponseJson uploadIcon(@RequestParam("iconUpload") MultipartFile iconUpload,
                                   @PathVariable("id") String userId) {
        return userService.uploadIcon(iconUpload, userId);
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
        return selectTopicService.primaryTopic(topicId, studentId);
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

    /**
     * 获取指导老师的上传的任务书
     * @param topicId 课题的id
     * @return 返回任务书
     */
    @GetMapping("/student/mission")
    public ResponseJson studentMission(@RequestParam("topicId") Integer topicId) {
        if(topicId == null) {
            return ResponseJsonUtil.error(-1, "参数错误.");
        }
        return fileService.selectFilename(topicId);
    }

    /**
     * 下载指导老师上传的任务书
     * @param request 下载
     * @param fileId 任务书id
     * @return 空
     */
    @GetMapping("/student/mission/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request,
                                               @RequestParam("fileId") String fileId) {
        return fileService.fileDownload(request, fileId);
    }

    /**
     * 学生信息保存接口
     * @param iconUpload 学生icon
     * @param student 学生对象
     * @return 状态
     */
    @PostMapping("/student/save")
    public ResponseJson studentSaveMessage(MultipartFile iconUpload,
                                           @RequestBody Student student) {
        if(!iconUpload.isEmpty()) {
            userService.uploadIcon(iconUpload, student.getStudent_no() + "");
        }
        return studentService.studentSaveMessage(student);
    }

    /*
      教师相关Api
     */

    /**
     * 返回预选了该教师的学生列表
     * @param teacherId 教师id
     * @return 返回所有预选该教师课题的学生
     */
    @GetMapping("/teacher/selectPrimary")
    public ResponseJson selectPrimaryTopic(@RequestParam(value = "teacherId", required = false)Integer teacherId) {
        if(teacherId == null) return ResponseJsonUtil.error(-1, "参数不能为空.");
        return selectTopicService.selectPrimaryTopic(teacherId);
    }

    /**
     * 返回该教师定选了该教师课题的所有学生
     * @param teacherId 教师id
     * @return 定选学生列表
     */
    @GetMapping("/teacher/primary/ok")
    public ResponseJson okSelectPrimary(@RequestParam(value = "teacherId", required = false) Integer teacherId) {
        if(teacherId == null) return ResponseJsonUtil.error(-1, "参数不能为空.");
        return selectTopicService.okSelectPrimary(teacherId);
    }

    /**
     * 返回该教师的所有课题，包括没有通过审核
     * @param teacherId 教师id
     * @return 返回课题列表
     */
    @GetMapping("/teacher/list")
    public ResponseJson selectTopicById(@RequestParam(value = "teacherId", required = false)Integer teacherId,
                                        @RequestParam(value = "type", defaultValue = "all") String type) throws FileNotFoundException {
        if(teacherId == null) return ResponseJsonUtil.error(-1, "参数不能为空.");
        List<ResultTopic> topicList = topicService.selectTopicByTeacherId(teacherId, type);
        return ResponseJsonUtil.successData(topicList);
    }

    /**
     * 定选一个学生的课题
     * @param topicId 课题id
     * @param studentId 学生id
     * @return 定选结果
     */
    @PostMapping("/topic/selectPrimary")
    public ResponseJson selectPrimaryStudent(@RequestParam(value = "topicId", required = false)Integer topicId,
                                             @RequestParam(value = "studentId", required = false)Integer studentId) {
        if(topicId == null || studentId == null) {
            return ResponseJsonUtil.error(-1, "参数不能为空.");
        }
        int row = studentService.selectPrimary(topicId, studentId);
        if(row != 0) {
            // 在老师定选了该课题后，需要删除该学生预选的其他课题
            selectTopicService.deleteSelectTopic(studentId); // 删除该学生的其他预选课题
            return ResponseJsonUtil.success("成功定选课题.");
        }
        return ResponseJsonUtil.error(-1, "选题失败.");
    }

    /**
     * 教师可以不定选该学生的课题（退选）
     * @param topicId 课题id
     * @param studentId 学生id
     * @return void
     */
    @PostMapping("/topic/no/passTopic")
    public ResponseJson noPassPrimaryTopic(@RequestParam(value = "topicId", required = false)Integer topicId,
                                           @RequestParam(value = "studentId", required = false)Integer studentId) {
        if(topicId == null || studentId == null) {
            return ResponseJsonUtil.error(-1, "参数不能为空.");
        }
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
     * 教师修改一个课题
     * @param topic 新的课题
     * @return 修改结果
     */
    @PostMapping("/teacher/update")
    public ResponseJson updateTopic(@RequestBody Topic topic) {
        if(topic == null) {
            return ResponseJsonUtil.error(-1, "参数不能为空.");
        }
        return topicService.updateTopic(topic);
    }

    /**
     * 教师可以删除一个课题
     * @param topicId 课题id
     * @return 删除结果
     */
    @PostMapping("/teacher/delete")
    public ResponseJson deleteTopic(@RequestParam("topicId") Integer topicId) {
        if(topicId == null) {
            return ResponseJsonUtil.error(-1, "参数不能为空.");
        }
        return topicService.deleteTopic(topicId);
    }

    /**
     * 用于教师上传任务书
     * @param topicId 课题id
     * @param fileUpload 上传的任务书
     * @return 上传的任务书
     */
    @PostMapping("/teacher/uploadFile/{id}")
    public ResponseJson teacherUploadFile(@PathVariable("id") Integer topicId,
                                          @RequestParam("fileUpload") MultipartFile fileUpload) {
        if(fileUpload == null) {
            return ResponseJsonUtil.error(-1, "参数错误.");
        }
        return fileService.uploadFile(topicId, fileUpload);
    }

    /**
     * 教师信息保存接口
     * @param iconUpload 教师icon
     * @param teacher 教师对象
     * @return 状态
     */
    @PostMapping("/teacher/save")
    public ResponseJson teacherSaveMessage(MultipartFile iconUpload,
                                           @RequestBody Teacher teacher) {
        if(!iconUpload.isEmpty()) {
            userService.uploadIcon(iconUpload, teacher.getTeacher_no() + "");
        }
        return teacherService.teacherSaveMessage(teacher);
    }

    /**
     * 修改职称
     * @param degree 职称名称
     * @param teacherId 教师id
     * @return 修改结果
     */
    @GetMapping("/teacher/update/degree")
    public ResponseJson teacherUpdateDegree(@RequestParam(value = "degree", required = false) String degree,
                                            @RequestParam(value = "teacherId", required = false) Integer teacherId) {
        if(degree == null || teacherId == null) {
            return ResponseJsonUtil.error(-1, "参数错误.");
        }
        return teacherService.teacherUpdateDegree(degree, teacherId);
    }

    /**
     * 修改学院名称
     * @param instituteId 学院id
     * @param teacherId 教师id
     * @return 修改结果
     */
    @GetMapping("/teacher/update/institute")
    public ResponseJson updateInstitute(@RequestParam(value = "instituteId", required = false) Integer instituteId,
                                        @RequestParam(value = "teacherId", required = false) Integer teacherId) {
        if(instituteId == null || teacherId == null) {
            return ResponseJsonUtil.error(-1, "参数错误.");
        }
        return teacherService.updateInstitute(instituteId, teacherId);
    }

    /**
     * 获取所有学院数据
     * @return 学院列表
     */
    @GetMapping("/teacher/get/institute")
    public ResponseJson updateInstitute() {
        return teacherService.getInstituteList();
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
        List<ResultTopic> topicList = topicService.findByAdmissionTrueTow("是");
        return ResponseJsonUtil.successData(topicList);
    }

    /**
     * 查询所有未通过审核的课题，管理员端使用
     * @return  返回查询的结果
     */
    @GetMapping("/topic/no/pass") // 查询所有未通过审核的课题
    public ResponseJson selectTopicNoPass() {
        List<ResultTopic> topicList = topicService.findByAdmissionTrueTow("否");
        return ResponseJsonUtil.successData(topicList);
    }
}
