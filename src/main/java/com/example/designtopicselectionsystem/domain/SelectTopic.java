package com.example.designtopicselectionsystem.domain;

// 预选课题类
public class SelectTopic {

    private Integer id; // 预选课题id
    private Topic topic; // 课题id
    private Student student; // 学生id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
