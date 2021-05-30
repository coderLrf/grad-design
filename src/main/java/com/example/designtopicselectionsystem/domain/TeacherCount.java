package com.example.designtopicselectionsystem.domain;

// 教师的课题人选数
public class TeacherCount {

    private Integer teacher_no; // 教师的id
    private String teacher_name; // 教师的名称
    private int count; // 统计学生的数量

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TeacherCount{" +
                "teacher_no=" + teacher_no +
                ", teacher_name='" + teacher_name + '\'' +
                ", count=" + count +
                '}';
    }
}
