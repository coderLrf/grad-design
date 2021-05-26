package com.example.designtopicselectionsystem.domain;

import javax.persistence.*;
import java.util.List;

// 教师类
// @Entity(name = "teacher")
public class Teacher {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增策略
    private Integer teacher_no; // 教师编号
    private String teacher_name; // 教师姓名
    private String sex; // 教师性别
    private String degree; // 教师学位
    private String institute_no; // 学院编号

    public Teacher() {
    }

    public Teacher(User user) {
        this.teacher_name = user.getUser_name();
    }

    public Integer getTeacher_no() {
        return teacher_no;
    }

    public void setTeacher_no(Integer teacher_no) {
        this.teacher_no = teacher_no;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
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

    public String getInstitute_no() {
        return institute_no;
    }

    public void setInstitute_no(String institute_no) {
        this.institute_no = institute_no;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_no=" + teacher_no +
                ", teacher_name='" + teacher_name + '\'' +
                ", sex='" + sex + '\'' +
                ", degree='" + degree + '\'' +
                ", institute_no='" + institute_no + '\'' +
                '}';
    }
}
