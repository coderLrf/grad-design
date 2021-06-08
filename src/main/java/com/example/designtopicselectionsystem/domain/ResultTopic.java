package com.example.designtopicselectionsystem.domain;

// 课题类
public class ResultTopic {

    private Integer title_no; // 课题id
    private String title_name; // 课题名称
    private String title_desc; // 课题描述
    private String teacher_name; // 教师姓名
    private String admission; // 是否通过审核

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

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    @Override
    public String toString() {
        return "ResultTopic{" +
                "title_no=" + title_no +
                ", title_name='" + title_name + '\'' +
                ", title_desc='" + title_desc + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", admission='" + admission + '\'' +
                '}';
    }
}
