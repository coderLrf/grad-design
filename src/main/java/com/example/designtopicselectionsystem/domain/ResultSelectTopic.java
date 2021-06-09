package com.example.designtopicselectionsystem.domain;

import java.util.List;

public class ResultSelectTopic {

    private Integer title_no;
    private String title_name;
    private String title_desc;
    private String student_name;
    private Integer student_no;
    private String sex;

    public Integer getTitle_no() {
        return title_no;
    }

    public void setTitle_no(Integer title_no) {
        this.title_no = title_no;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getTitle_desc() {
        return title_desc;
    }

    public void setTitle_desc(String title_desc) {
        this.title_desc = title_desc;
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

    @Override
    public String toString() {
        return "ResultSelectTopic{" +
                "title_no=" + title_no +
                ", title_name='" + title_name + '\'' +
                ", title_desc='" + title_desc + '\'' +
                ", student_name='" + student_name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
