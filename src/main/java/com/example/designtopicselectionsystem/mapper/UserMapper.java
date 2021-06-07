package com.example.designtopicselectionsystem.mapper;

import com.example.designtopicselectionsystem.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> selectAll();

    @Select("select * from user where user_no = #{userId}")
    public User selectById(String userId);

    @Select("select user_icon from user where user_no = #{userId}")
    public String selectIconById(String userId);

    // 模糊搜索
    @Select("select * from user where user_no like #{content} or user_name like #{content}")
    public List<User> searchUser(String content);


    @Insert("insert into user(user_no, password, user_name, identity) values(#{user_no}, #{password}, #{user_name}, #{identity})")
    public int saveUser(User user);

    @Update("update user set password = #{password}, user_name = #{user_name}, identity = #{identity} where user_no = #{user_no}")
    public int updateUser(User user);

    @Update("update user set password = #{password} where user_no = #{userId}")
    public int updatePassword(@Param(value = "userId") String userId, @Param(value = "password") String password);

    @Update("update user set user_icon = #{url} where user_no = #{userId}")
    public int updateIcon(@Param(value = "userId") String userId, @Param(value = "url") String url);

    @Delete("delete from user where user_no = #{userId}")
    public int deleteUser(String userId);

}
