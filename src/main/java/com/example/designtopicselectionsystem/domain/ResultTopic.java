package com.example.designtopicselectionsystem.domain;

// 课题类
public class ResultTopic {

    private Integer title_no; // 课题id
    private String title_name; // 课题名称
    private String title_desc; // 课题描述
    private Integer teacher_no; // 教师的id
    private String teacher_name; // 教师姓名
    private String admission; // 是否通过审核
    private String fileName; // 文件的名称
    private File file; // 该课题任务书

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

    public Integer getTeacher_no() {
        return teacher_no;
    }

    public void setTeacher_no(Integer teacher_id) {
        this.teacher_no = teacher_id;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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
