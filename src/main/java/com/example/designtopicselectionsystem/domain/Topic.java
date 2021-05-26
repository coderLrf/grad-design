package com.example.designtopicselectionsystem.domain;

import javax.persistence.*;

// 课题类
public class Topic {

    private Integer topicId; // 课题id

    private String topicName; // 课题名称

    private String topicDesc; // 课题描述

    private Integer teacherId; // 教师编号

    private String admission; // 是否预选，或则是/否

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", topicName='" + topicName + '\'' +
                ", topicDesc='" + topicDesc + '\'' +
                ", teacherId=" + teacherId +
                ", admission='" + admission + '\'' +
                '}';
    }
}
