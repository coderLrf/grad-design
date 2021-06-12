package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.Institute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InstituteMapper {

    @Select("select institute_name from institute where institute_no = #{instituteId}")
    public String findInstituteById(String instituteId);

    @Select("select * from institute")
    public List<Institute> selectAll();

}
