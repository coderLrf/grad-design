package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {

    @Insert("insert into file(topic_id, file_id) values(#{topicId}, #{fileId})")
    public int uploadFile(@Param("topicId") Integer topicId, @Param("fileId") String fileId);

    @Select("select * from file where topic_id = #{topicId}")
    public File selectFilename(Integer topicId);

}
