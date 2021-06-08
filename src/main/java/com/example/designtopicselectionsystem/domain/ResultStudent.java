package com.example.designtopicselectionsystem.domain;

import java.sql.Date;

public class ResultStudent {

    private String student_name; // 姓名
    private Integer student_no; // 学号
    private String sex; // 性别

    private Date birthday; // 出生年月
    private String institute_name; // 系部
    private String class_name; // 班级id
    private String major_name; // 专业名称
    private boolean isSelectTopic; // 是否已选有课题

    public ResultStudent() {
    }

    public ResultStudent(String student_name, Integer student_no, String sex, Date birthday, String institute_name, String class_name, String major_name, boolean isSelectTopic) {
        this.student_name = student_name;
        this.student_no = student_no;
        this.sex = sex;
        this.birthday = birthday;
        this.institute_name = institute_name;
        this.class_name = class_name;
        this.major_name = major_name;
        this.isSelectTopic = isSelectTopic;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Integer getStudent_no() {
        return student_no;
    }

    public void setStudent_no(Integer student_no) {
        this.student_no = student_no;
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

    public String getInstitute_name() {
        return institute_name;
    }

    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public boolean isSelectTopic() {
        return isSelectTopic;
    }

    public void setSelectTopic(boolean selectTopic) {
        isSelectTopic = selectTopic;
    }
}
