package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.domain.User;
import com.example.designtopicselectionsystem.mapper.UserMapper;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    // 根据关键字搜索用户
    public List<User> searchUserByKeyWord(String content) {
        content += "%";
        return userMapper.searchUser(content);
    }

    // 添加用户
    public int saveUser(User user) {
        // 使用md5加密密码
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        int rows = userMapper.saveUser(user);
        return rows;
    }

    // 更新用户
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    // 删除用户
    public int deleteUser(String userId) {
        int i = userMapper.deleteUser(userId);
        return i;
    }

    // 密码修改
    public int updatePassword(String userId, String password) {
        return userMapper.updatePassword(userId, password);
    }

    // 修改用户的icon
    public int updateIcon(String userId, String url) {
        return userMapper.updateIcon(userId, url);
    }

    // 获取用户icon
    public String selectIconById(String userId) {
        return userMapper.selectIconById(userId);
    }

    // 用户上传头像
    public ResponseJson uploadIcon(MultipartFile iconUpload, String userId) {
        // 获取文件名以及后缀名
        String fileName = iconUpload.getOriginalFilename();
        // 重新生成新的文件名（根据具体情况生成对应文件名）
        fileName = UUID.randomUUID() + "_" + fileName;
        try {
            // 指定上传文件本地存储目录（存储到静态资源目录下）
            String dirPath = ResourceUtils.getFile("classpath:").getPath() + "\\static\\upload\\icon\\";
            // 根据目录创建一个file对象
            File dir = new File(dirPath);
            if(!dir.exists()) {
                dir.mkdirs(); // 创建
            }
            iconUpload.transferTo(new File(dirPath + fileName)); // 上传头像
            updateIcon(userId, dirPath + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseJsonUtil.error(-1, "icon上传失败.");
        }
        return ResponseJsonUtil.success("icon上传成功.");
    }

}
