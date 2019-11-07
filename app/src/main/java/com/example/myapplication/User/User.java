package com.example.myapplication.User;

import java.util.Date;

public class User {
    private int userId;         //用户登录ID
    private String userName;    //用户昵称
    private String name;        //用户名字
    private String sex;         //性别
    private Date  birthday;     //生日
    private int age;            //年龄
    private String userCollege; //所在学院
    private String userMajor;   //所在专业
    private String direction;   //选择方向
    private String passWord;    //密码
    private int idendity;       //身份：= 0 为高中生；= 1 为大学生以上 ；= -1 为管理员

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserCollege() {
        return userCollege;
    }

    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getIdendity() {
        return idendity;
    }

    public void setIdendity(int idendity) {
        this.idendity = idendity;
    }
}
