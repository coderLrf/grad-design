package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.File;
import com.example.designtopicselectionsystem.domain.ResultTopic;
import com.example.designtopicselectionsystem.domain.Topic;
import com.example.designtopicselectionsystem.mapper.FileMapper;
import com.example.designtopicselectionsystem.mapper.SelectTopicMapper;
import com.example.designtopicselectionsystem.mapper.TopicMapper;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private SelectTopicMapper selectTopicMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private FileMapper fileMapper;

    // 查询所有通过审核的课题 tow
    public List<ResultTopic> findByAdmissionTrueTow(String type) {
        List<ResultTopic> admissionList = topicMapper.selectByAdmission(type);
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

    // 通过教师id查询课题（所有）
    public List<ResultTopic> selectTopicByTeacherId(Integer teacherId, String type) throws FileNotFoundException {
        List<ResultTopic> resultTopicList;
        if(type.equals("ok")) {
            resultTopicList = topicMapper.selectTopicByTeacherIdAndType(teacherId, "是");
        } else if(type.equals("pass")) {
            resultTopicList = topicMapper.selectTopicByTeacherIdAndType(teacherId, "否");
        } else {
            resultTopicList = topicMapper.selectTopicByTeacherId(teacherId);
        }
        // 根据课题id查询文件
        for (ResultTopic resultTopic : resultTopicList) {
            File file = fileMapper.selectFilename(resultTopic.getTitle_no());
            if(file == null) continue; // 如果文件为空，则跳过循环
            String filename = file.getFile_id().substring(file.getFile_id().lastIndexOf("_") + 1); // 获取文件名
            String dirPath = "\\static\\upload\\file\\";
            file.setFilePath(dirPath + file.getFile_id());
            resultTopic.setFileName(filename);
            resultTopic.setFile(file);
        }
        return resultTopicList;
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
        // 查询该教师的课题总数，课题数量超过4个则不能添加
        Integer count = calcTopicCountByTeacherId(topic.getTeacherId());
        if(count >= 4) {
            return ResponseJsonUtil.error(-1, "你的课题已经达到上限，不能添加了哟~");
        }
        int row = topicMapper.saveTopic(topic);
        if(row != 0) {
            return ResponseJsonUtil.success("课题添加成功.");
        }
        return ResponseJsonUtil.error(-1, "课题添加失败.");
    }

    private Integer calcTopicCountByTeacherId(Integer teacherId) {
        Integer count = topicMapper.calcTopicCountByTeacherId(teacherId);
        if(count == null) return 0;
        return count;
    }

    // 根据课题名称和教师名称进行模糊查询课题
    public List<ResultTopic> selectTopicFuzzy(String identity, String content) {
        String con = StringUtils.isBlank(content) ? "" : "%" + content + "%";
        content = "%" + content + "%";
        String state;
        if(identity.equals("review")) { // 审核中
            return selectTopicMapper.selectTopicFuzzyNull(content, con);
        } else if(identity.equals("checked")) { // 已通过审核
            state = "是";
        } else { // 未通过审核
            state = "否";
        }
        return selectTopicMapper.selectTopicFuzzy(content, con, state);
    }

    // 教师修改一个课题
    public ResponseJson updateTopic(Topic topic) {
        topicMapper.updateTopic(topic);
        return ResponseJsonUtil.success("修改成功.");
    }

    // 将一个课题设置不可预选状态
    public void setTopicEnabled(Integer topicId) {
        topicMapper.setTopicEnabled(topicId);
    }

    // 教师删除一个课题
    public ResponseJson deleteTopic(Integer topicId) {
        // 删除课题之前，先将定选了该课题的学生清空和预选了该课题的学生进行删除
        studentService.deleteStudentTopic(topicId);
        selectTopicMapper.deleteSelectTopicByTopicId(topicId);
        topicMapper.deleteTopic(topicId);
        return ResponseJsonUtil.success("删除成功.");
    }
}
