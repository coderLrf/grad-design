package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    @Autowired
    private ClassMapper classMapper;

    public String getClassName(Integer classId) {
        return classMapper.getClassName(classId);
    }

}
