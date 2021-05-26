package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.ResultSelectTopic;
import com.example.designtopicselectionsystem.domain.ResultTopic;
import com.example.designtopicselectionsystem.domain.SelectTopic;
import com.example.designtopicselectionsystem.domain.Topic;
import com.example.designtopicselectionsystem.mapper.SelectTopicMapper;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectTopicService {

    @Autowired
    private SelectTopicMapper selectTopicMapper;

    @Autowired
    private StudentService studentService;

    public ResponseJson primaryTopic(SelectTopic selectTopic) {
        int row = selectTopicMapper.primaryTopic(selectTopic);
        if(row != 0) { // 预选成功
            return ResponseJsonUtil.success("预选成功.");
        }
        return ResponseJsonUtil.error("参数错误.", "预选失败");
    }

    // 根据教师id查询教师预选课题的学生
    public ResponseJson selectPrimaryTopic(Integer teacherId) {
        List<ResultSelectTopic> selectTopicList = selectTopicMapper.selectByTeacherId(teacherId);
        return ResponseJsonUtil.successData(selectTopicList);
    }

    // 查询所有该学生可以预选的课题
    public ResponseJson selectAdmissionTopic(Integer studentId) {
        List<ResultTopic> topicList = selectTopicMapper.selectAdmissionTopic(studentId);
        return ResponseJsonUtil.successData(topicList);
    }

    public ResponseJson deleteSelectTopic(Integer studentId) {
        int row = selectTopicMapper.deleteSelectTopic(studentId);
        if(row != 0) {
            return ResponseJsonUtil.success("删除成功.");
        }
        return ResponseJsonUtil.error("参数错误.", "删除失败.");
    }

    // 查询已经预选了的课题
    public ResponseJson alreadySelectTopic(Integer studentId) {
        List<ResultTopic> topicList = selectTopicMapper.alreadySelectTopic(studentId);
        return ResponseJsonUtil.successData(topicList);
    }

    // 教师删除预选课题
    public ResponseJson deletePrimaryTopic(Integer topicId, Integer studentId) {
        int row = selectTopicMapper.deletePrimaryTopic(topicId, studentId);
        if(row != 0) {
            return ResponseJsonUtil.success("删除成功.");
        }
        return ResponseJsonUtil.error(-1, "删除失败.");
    }

    // 学生退选课题
    public ResponseJson backSelectTopic(Integer studentId, Integer topicId) {
        int row = selectTopicMapper.deletePrimaryTopic(topicId, studentId);
        if(row != 0) {
            return ResponseJsonUtil.success("退选成功.");
        }
        return ResponseJsonUtil.error(-1, "退选失败.");
    }
}
