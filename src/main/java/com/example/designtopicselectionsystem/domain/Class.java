package com.example.designtopicselectionsystem.domain;

import javax.persistence.*;

// 班级类
@Entity(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_no")
    private Integer classId; // 班级id

    @Column(name = "class_name")
    private String className; // 班级名称

    @Column(name = "major_no")
    private Integer majorId; // 专业id

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }
}
