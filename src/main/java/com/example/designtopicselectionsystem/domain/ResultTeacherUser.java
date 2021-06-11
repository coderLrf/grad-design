package com.example.designtopicselectionsystem.domain;


import java.util.List;

public class ResultTeacherUser {

    private String userNo; // 账号
    private String username; // 用户名
    private String identity; // 身份：学生，教师，管理员
    private String sex; // 性别
    private String degree; // 职称

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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "ResultTeacherUser{" +
                "userNo='" + userNo + '\'' +
                ", username='" + username + '\'' +
                ", identity='" + identity + '\'' +
                ", sex='" + sex + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
