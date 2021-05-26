package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.User;
import com.example.designtopicselectionsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service // 表示当前类为业务逻辑处理类
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 查询所有用户
    public List<User> findAll() {
        List<User> userList = userMapper.selectAll();
        return userList;
    }

    // 根据学号查询用户
    public User findById(String userNo) {
        User user = userMapper.selectById(userNo);
        return user;
    }

    public int saveUser(User user) {
        // 使用md5加密密码
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        int rows = userMapper.saveUser(user);
        return rows;
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int deleteUser(String userId) {
        int i = userMapper.deleteUser(userId);
        return i;
    }

    public int updatePassword(String userId, String password) {
        return userMapper.updatePassword(userId, password);
    }
}
