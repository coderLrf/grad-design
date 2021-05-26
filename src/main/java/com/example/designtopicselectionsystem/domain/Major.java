package com.example.designtopicselectionsystem.domain;

import javax.persistence.*;

// 专业类
@Entity(name = "major")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
    @Column(name = "major_no")
    private Integer majorId; // 专业id

    @Column(name = "major_name")
    private String majorName; // 专业名称

    @Column(name = "institute_no")
    private String instituteId; // 学院id

    public Major() {
    }

    public Major(String majorName, String instituteId) {
        this.majorName = majorName;
        this.instituteId = instituteId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorId='" + majorId + '\'' +
                ", majorName='" + majorName + '\'' +
                ", instituteId='" + instituteId + '\'' +
                '}';
    }
}
