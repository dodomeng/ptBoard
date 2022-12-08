package com.example.ptboard;

public class User {
    String userNum;
    String userPwd;
    String userName;
    String userSex;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public User(String userNum, String userPwd, String userName, String userSex) {
        this.userNum = userNum;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userSex = userSex;
    }
}
