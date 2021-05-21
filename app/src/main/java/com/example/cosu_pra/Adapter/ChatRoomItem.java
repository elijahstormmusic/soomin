package com.example.cosu_pra.Adapter;

import com.example.cosu_pra.DTO.User;
import java.util.List;
import java.util.ArrayList;

//TODO 채팅방리스트 임의적으로 만들었습니다.
public class ChatRoomItem {
    private String databaseReference;
    private String chatName;
    private String chatContent;
    private String chatTime;
    private List<User> userList;

    public ChatRoomItem(String _dbRef, String _chatName, String _chatContent, String _chatTime, List<User> _userList) {
        this.databaseReference = _dbRef;
        this.chatName = _chatName;
        this.chatContent = _chatContent;
        this.chatTime = _chatTime;
        this.userList = _userList;
    }

    public void setRoomName(String chatname) {
        chatName = chatname;
    }

    public void setLastMSG(String chatcontent) {
        chatContent = chatcontent;
    }

    public void setLastTime(String chattime) {
        chatTime = chattime;
    }

    public void setUserList(List<User> _list) {
        userList = _list;
    }

    public String getDatabaseReference() {
        return this.databaseReference;
    }

    public String getRoomName() {
        return this.chatName;
    }

    public String getLastMSG() {
        return this.chatContent;
    }

    public String getLastTime() {
        return this.chatTime;
    }

    public List<User> getUserList() {
        return this.userList;
    }

}
