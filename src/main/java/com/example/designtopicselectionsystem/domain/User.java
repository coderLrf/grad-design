package com.example.designtopicselectionsystem.domain;

// 用户表
public class User {

    private String user_no; // 账号
    private String password; // 密码
    private String user_name; // 用户名
    private String identity; // 身份：学生，教师，管理员
    private String userIcon; // 用户的icon

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_no='" + user_no + '\'' +
                ", password='" + password + '\'' +
                ", user_name='" + user_name + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }
}
