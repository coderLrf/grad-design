package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Insert("insert into file(topic_id, file_id) values(#{topicId}, #{fileId})")
    public int uploadFile(@Param("topicId") Integer topicId, @Param("fileId") String fileId);

    @Select("select * from file where topic_id = #{topicId}")
    public File selectFilename(Integer topicId);

    @Update("update file set file_id = #{fileName} where topic_id = #{topicId}")
    public void updateFile(@Param("topicId") Integer topicId, @Param("fileName") String fileName);

    @Delete("delete from file where topic_id = #{topicId}")
    public void deleteFile(Integer topicId);

}
