package com.example.designtopicselectionsystem.domain;

import javax.persistence.*;

// 学院类
@Entity(name = "institute")
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "institute_no")
    private String instituteId; // 学院id

    @Column(name = "institute_name")
    private String instituteName; // 学院名称

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    @Override
    public String toString() {
        return "Institute{" +
                "instituteId='" + instituteId + '\'' +
                ", instituteName='" + instituteName + '\'' +
                '}';
    }
}
