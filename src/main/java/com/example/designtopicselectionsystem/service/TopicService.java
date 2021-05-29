package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.ResultTopic;
import com.example.designtopicselectionsystem.domain.Topic;
import com.example.designtopicselectionsystem.mapper.TopicMapper;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    // 查询所有通过审核的课题 tow
    public List<ResultTopic> findByAdmissionTrueTow() {
        List<ResultTopic> admissionList = topicMapper.selectByAdmission("是");
        return admissionList;
    }

    // 查询所有需要审核的课题
    public List<ResultTopic> wantExamineTopic() {
        List<ResultTopic> topicList = topicMapper.wantExamineTopic();
        return topicList;
    }

    // 查询所有不通过审核的课题
    public List<ResultTopic> findByAdmissionFalse() {
        List<ResultTopic> topicList = topicMapper.selectByAdmission("否");
        return topicList;
    }

    // 查询该课题定选的人数
    public int selectCountByTopicId(Integer id) {
        return topicMapper.selectCountByTopicId(id);
    }

    // 通过一个课题
    public int passTopicById(Integer id) {
        return topicMapper.passTopicById(id);
    }

    public int noPassTopicById(Integer id) {
        return topicMapper.noPassTopicById(id);
    }

    public ResponseJson saveTopic(Topic topic) {
        // 查询该课题是否有重名
        ResultTopic select = topicMapper.selectByTopicName(topic.getTopicName());
        if(select != null) { // 存在该课题
            return ResponseJsonUtil.error(-1, "重复课题名称，添加失败.");
        }
        int row = topicMapper.saveTopic(topic);
        if(row != 0) {
            return ResponseJsonUtil.success("课题添加成功.");
        }
        return ResponseJsonUtil.error(-1, "课题添加失败.");
    }



}
