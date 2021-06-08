package com.example.designtopicselectionsystem.controller;

import com.example.designtopicselectionsystem.domain.*;
import com.example.designtopicselectionsystem.domain.Class;
import com.example.designtopicselectionsystem.repository.ClassRepository;
import com.example.designtopicselectionsystem.repository.InstituteRepository;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import com.example.designtopicselectionsystem.service.StudentService;
import com.example.designtopicselectionsystem.service.TeacherService;
import com.example.designtopicselectionsystem.service.TopicService;
import com.example.designtopicselectionsystem.service.UserService;
import com.example.designtopicselectionsystem.utils.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Commons commons;

    @GetMapping("/login")
    public String toLogin() {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        System.out.println("username:" + username);
        System.out.println("password：" + password);
        if(username.equals("admin") && password.equals("123456")) {
            return "index";
        }
        return "login/login";
    }

    @GetMapping("/")
    public String toIndex() {
        return "redirect:index";
    }

    // 重定向到首页
    @GetMapping("/index")
    public String index(Model model) {
        // 请求所有教师数据返回给前端
        List<Teacher> teacherList = teacherService.findAll();
        List<TeacherCount> teacherCount = teacherService.selectTeacherCount();
        String[] teacherNames = new String[teacherList.size()];
        int[] teacherCountList = new int[teacherList.size()];
        int index = 0;
        for (Teacher teacher : teacherList) {
            teacherNames[index] = teacher.getTeacher_name();
            for (TeacherCount count : teacherCount) {
                if(count.getTeacher_no().equals(teacher.getTeacher_no())) {
                    teacherCountList[index] = count.getCount();
                    break;
                }
            }
            index++;
        }
        model.addAttribute("teacherList", teacherNames);
        model.addAttribute("teacherCountList", teacherCountList);
        return "index";
    }

    // 搜索功能
    @GetMapping("/search/{identity}")
    public String search(@PathVariable("identity") String identity,
                         @RequestParam(value = "content", defaultValue = "") String content,
                         Model model) {
        Object list;
        // 根据身份类型判断
        switch(identity) {
            case "student":
                list = studentService.searchStudentByKeyWord(content);
                break;
            case "teacher":
                list = teacherService.searchTeacherByKeyWord(content);
                break;
            case "user":
                list = userService.searchUserByKeyWord(content);
                break;
            default:
                return "/error";
        }
        // 发送给前端
        model.addAttribute(identity + "List", list);
        return identity + "/list";
    }

    /**
     * 以下是学生管理路由控制器
     */

    // 返回到学生列表页面
    @RequestMapping({"/stu/list", "/stu/list.html"})
    public String stuList(Model model) {
        // 请求所有学生数据
        List<ResultStudent> studentList = studentService.selectAll();
        model.addAttribute("commons", commons);
        model.addAttribute("studentList", studentList);
        return "student/list";
    }

    // 返回学生添加页面
    @GetMapping({"/stu/add", "/stu/add.html"})
    public String toStuAddPage(Model model) {
        // 查询所有班级列表
        List<Class> classList = classRepository.findAll();
        model.addAttribute("classList", classList);
        return "student/add";
    }

    @PostMapping("/stu/add")
    public String stuAdd(Student student) {
        int save = studentService.save(student);
        if(save != 0) {
            // 添加用户
            User user = new User();
            user.setPassword("123456");
            user.setIdentity("学生");
            user.setUser_no(student.getStudent_no() + "");
            user.setUser_name(student.getStudent_name());
            userService.saveUser(user);
            return "redirect:list";
        }
        return "redirect:add";
    }

    @GetMapping("/stu/update/{id}")
    public String toStuUpdate(@PathVariable("id") Integer id, Model model) {
        // 获取学生数据
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        // 获取所有班级数据
        List<Class> classList = classRepository.findAll();
        model.addAttribute("classList", classList);
        model.addAttribute("commons", commons);
        return "student/update";
    }

    @PostMapping("/stu/update")
    public String stuUpdate(Student student) {
        int update = studentService.update(student);
        return "redirect:list";
    }

    @GetMapping("/stu/delete/{id}")
    public String stuDelete(@PathVariable("id") Integer id) {
        int delete = studentService.delete(id);
        System.out.println(delete);
        return "redirect:/admin/stu/list";
    }

    /**
     * 以下是教师管理路由控制器
     */

    // 返回到教师管理页面
    @RequestMapping({"/tea/list", "/tea/list.html"})
    public String teaList(Model model) {
        // 请求所有教师数据
        List<ResultTeacher> teacherList = teacherService.selectAll();
        model.addAttribute("teacherList", teacherList);
        return "teacher/list";
    }

    // 返回教师添加页面
    @GetMapping({"/tea/add", "/tea/add.html"})
    public String toTeaAdd(Model model) {
        // 请求教师最后一条编号
        int lastId = teacherService.findLastId();
        model.addAttribute("lastId", lastId);
        // 请求所有学院数据
        List<Institute> instituteList = instituteRepository.findAll();
        model.addAttribute("instituteList", instituteList);

        return "teacher/add";
    }

    @PostMapping("/tea/add")
    public String teaAdd(Teacher teacher) {
        int save = teacherService.save(teacher);
        if(save != 0) {
            // 添加用户
            User user = new User();
            user.setPassword("123456");
            user.setIdentity("教师");
            user.setUser_no(teacher.getTeacher_no() + "");
            user.setUser_name(teacher.getTeacher_name());
            userService.saveUser(user);
            return "redirect:list";
        }
        return "redirect:add";
    }

    @GetMapping("/tea/update/{id}")
    public String toTeaUpdate(@PathVariable("id")Integer id,
                              Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        // 请求所有学院数据
        List<Institute> instituteList = instituteRepository.findAll();
        model.addAttribute("instituteList", instituteList);
        return "teacher/update";
    }

    @PostMapping("/tea/update")
    public String teaUpdate(Teacher teacher) {
        System.out.println(teacher);
        int update = teacherService.update(teacher);
        System.out.println(update);
        return "redirect:list";
    }

    @GetMapping("/tea/delete/{id}")
    public String teaDelete(@PathVariable("id")Integer id) {
        teacherService.delete(id);
        return "redirect:/admin/tea/list";
    }

    /**
     * 以下是课题管理路由控制器
     */

    // 课题列表页面
    @GetMapping("/top/review")
    public String toTopReview(Model model) {
        // 请求所有需要审核的课题
        List<ResultTopic> topicList = topicService.wantExamineTopic();
        model.addAttribute("topicList", topicList);
        return "topic/review";
    }

    @GetMapping("/top/checked")
    public String toTopChecked(Model model) {
        // 查询所有已通过审核的课题
        List<ResultTopic> topicList = topicService.findByAdmissionTrueTow();
        model.addAttribute("topicListed", topicList);
        return "topic/checked";
    }

    @GetMapping("/top/notCheck")
    public String toTopNotCheck(Model model) {
        // 查询所有未通过的课题
        List<ResultTopic> topicList = topicService.findByAdmissionFalse();
        model.addAttribute("notTopicList", topicList);
        return "topic/notCheck";
    }

    @GetMapping("/top/pass/{id}")
    public String passTopic(@PathVariable("id") Integer id,
                            @RequestParam("path") String currentPath) {
        int i = topicService.passTopicById(id); // 通过审核
        return "redirect:/admin/top/" + currentPath;
    }

    @GetMapping("/top/noPass/{id}")
    public String noPassTopic(@PathVariable("id") Integer id,
                              @RequestParam("path") String currentPath) {
        int i = topicService.noPassTopicById(id); // 通过审核
        return "redirect:/admin/top/" + currentPath;
    }

    /**
     * 以下是系统设置路由控制器
     */

    // 系统设置页面
    @RequestMapping({"/setUp", "/setUp.html"})
    public String setUp() {
        return "settings/setUp";
    }

    /**
     * 以下是用户管理路由控制器
     */

    // 用户列表
    @GetMapping("/user/list")
    public String userList(Model model) {
        // 获取用户列表哦
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user/list";
    }

    // 添加用户页面
    @GetMapping("/user/add")
    public String toUserAdd(Model model) {
        // 查询最大学号和最大教师号
        Integer studentId = studentService.selectNextStudentId();
        Integer teacherId = teacherService.selectNextTeacherId();
        // 返回给前端
        model.addAttribute("studentId", studentId);
        model.addAttribute("teacherId", teacherId);
        return "user/add";
    }

    // 添加用户
    @PostMapping("/user/add")
    public String UserAdd(User user) {
        String identity = user.getIdentity();
        if(identity.equals("学生")) {
            // 进行学生添加
            Student student = new Student(user);
            studentService.save(student);
        } else if(identity.equals("教师")) {
            // 进行教师添加
            Teacher teacher = new Teacher(user);
            teacherService.save(teacher);
        }
        int i = userService.saveUser(user);
        if(i != 0) {
            // 根据用户选择的是学生还是教师进行对应添加
            return "redirect:list";
        }
        return "redirect:add";
    }

    @GetMapping("/user/update/{id}")
    public String toUserUpdate(@PathVariable("id")String userId,
                               Model model) {
        // 查询用户数据
        User user = userService.findById(userId);
        // 由于密码使用了md5加密，需要解密后展示到界面
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update")
    public String userUpdate(User user) {
        int row = userService.updateUser(user);
        if(row != 0) {
            return "redirect:list";
        }
        return "redirect:update";
    }

    @GetMapping("/user/delete/{id}")
    public String userDelete(@PathVariable("id") String userId) {
        // 删除对应学生或者教师
        User user = userService.findById(userId);
        // 删除用户
        int row = userService.deleteUser(userId);
        if(row != 0) {
            if(user.getIdentity().equals("学生")) {
                studentService.delete(Integer.parseInt(userId));
            } else if(user.getIdentity().equals("教师")) {
                teacherService.delete(Integer.parseInt(userId));
            }
        }
        return "redirect:/admin/user/list";
    }

}
