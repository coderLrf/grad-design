package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.*;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 登录业务逻辑层
@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 用户登录业务逻辑处理
     * @param user 用户对象
     * @return 登录结果
     */
    public ResponseJson login(HttpServletRequest request, User user) {
        // 拿到前端传过来的账号密码，查询数据库看看是否正确
        String userNo = user.getUser_no();
        User u = userService.findById(userNo);
        if(u != null) { // 用户存在
            // 取出数据库密码和前端密码进行对比，由于数据库中的密码是加密后的，需要把前端传过来的密码进行加密对比
            // 使用md5加密密码
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            // 判断密码是否正确
            if(!password.equals(u.getPassword())) {
                return ResponseJsonUtil.error(-1, "密码错误.");
            }
            // 判断该用户身份
            String identity = u.getIdentity();
            if(!user.getIdentity().equals(identity)) {
                return ResponseJsonUtil.error(-1, "身份错误.");
            }
            Object o = null; // 需要返回的数据
            // 根据身份来决定返回的数据
            switch(identity) {
                case "学生":
                    o = studentService.findById(Integer.parseInt(u.getUser_no()));
                    break;
                case "教师":
                    o = teacherService.findById(Integer.parseInt(u.getUser_no()));
                    break;
            }
            // 登录成功
            return ResponseJsonUtil.successData(o, "登录成功.");
        }
        // 用户不存在
        return ResponseJsonUtil.error(-1, "账号不存在.");
    }

    /**
     * 用户密码修改业务逻辑处理
     * @param userId 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改的结果
     */
    public ResponseJson passwordUpdate(String userId, String oldPassword, String newPassword) {
        // 1、根据用户id查找到该用户
        User user = userService.findById(userId);
        if(user == null) {
            return ResponseJsonUtil.error(-1, "账号不存在.");
        }
        // 2、判断输入的旧密码是否相等
        // 将密码加密后匹配
        String md5OldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());

        if(!user.getPassword().equals(md5OldPassword)) { // 如果不相等
            return ResponseJsonUtil.error(-1, "请输入正确的旧密码.");
        }
        // 如果相等，正确的修改密码
        // 使用MD5加密新密码
        String password = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        int row = userService.updatePassword(userId, password); // 密码修改
        if(row != 0) {
            return ResponseJsonUtil.success("密码修改成功.");
        }
        return ResponseJsonUtil.success("密码修改失败.");
    }

}
