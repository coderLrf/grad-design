package com.example.designtopicselectionsystem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;

// 学生表
// @Entity(name = "student")
public class Student {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_no; // 学号
    private String student_name; // 姓名
    private String sex; // 性别
    private Date birthday; // 出生年月
    private String class_no; // 班级id
    private Integer topic_no; // 课题编号，定选后的课题

    public Student() {
    }

    public Student(User user) {
        this.student_name = user.getUser_name();
    }

    public Integer getStudent_no() {
        return student_no;
    }

    public void setStudent_no(Integer student_no) {
        this.student_no = student_no;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
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

    public Integer getTopic_no() {
        return topic_no;
    }

    public void setTopic_no(Integer topic_no) {
        this.topic_no = topic_no;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_no=" + student_no +
                ", student_name='" + student_name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", class_no='" + class_no + '\'' +
                '}';
    }
}
