package com.example.cosu_pra.DTO;

public class User{

    private String email;
    private String pwd;
    private String realName;
    private String nickName;
    private String userID;

    public User(String email,String pwd,String realName, String nickName, String userID){
        this.email = email;
        this.pwd = pwd;
        this.realName = realName;
        this.nickName = nickName;
        this.userID = userID;
    }

    // getter
    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRealName() {
        return realName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getUserID() { return userID; }

    //setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setUserID(String input) { this.userID = input; }

}