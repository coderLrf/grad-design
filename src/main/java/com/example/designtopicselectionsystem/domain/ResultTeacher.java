package com.example.designtopicselectionsystem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class ResultTeacher {

    private String teacher_name; // 姓名
    private Integer teacher_no; // 编号
    private String sex; // 性别

    private String degree; // 学位
    private String institute_name; // 系部


    public ResultTeacher() {
    }

    public ResultTeacher(String teacher_name, Integer teacher_no, String sex, String degree, String institute_name) {
        this.teacher_name = teacher_name;
        this.teacher_no = teacher_no;
        this.sex = sex;
        this.degree = degree;
        this.institute_name = institute_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public Integer getTeacher_no() {
        return teacher_no;
    }

    public void setTeacher_no(Integer teacher_no) {
        this.teacher_no = teacher_no;
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

    public String getInstitute_name() {
        return institute_name;
    }

    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }
}
