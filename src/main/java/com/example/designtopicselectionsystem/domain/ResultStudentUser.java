package com.example.designtopicselectionsystem.domain;

import java.sql.Date;

public class ResultStudentUser {

    private String userNo; // 账号
    private String username; // 用户名
    private String identity; // 身份：学生，教师，管理员
    private String sex; // 性别

    private Date birthday; // 出生年月
    private String class_no; // 班级id

    public ResultStudentUser() {
    }

    public ResultStudentUser(User user, Student stu) {
        this.userNo = user.getUser_no();
        this.username = user.getUser_name();
        this.identity = user.getIdentity();
        this.sex = stu.getSex();
        this.birthday = stu.getBirthday();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getClass_no() {
        return class_no;
    }

    public void setClass_no(String class_no) {
        this.class_no = class_no;
    }
}
