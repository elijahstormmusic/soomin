package com.example.cosu_pra.DTO;

//TODO 채팅방리스트 임의적으로 만들었습니다.
public class ChatData {
    private String databaseReference;
    private String chatName;
    private String chatContent;
    private String chatTime;

    public ChatData(String _dbRef, String _chatName, String _chatContent, String _chatTime) {
        this.databaseReference = _dbRef;
        this.chatName = _chatName;
        this.chatContent = _chatContent;
        this.chatTime = _chatTime;
    }

    public void setName(String chatname) {
        chatName = chatname;
    }

    public void setContent(String chatcontent) {
        chatContent = chatcontent;
    }

    public void setTime(String chattime) {
        chatTime = chattime;
    }

    public String getDatabaseReference() {
        return this.databaseReference;
    }

    public String getName() {
        return this.chatName;
    }

    public String getContent() {
        return this.chatContent;
    }

    public String getTime() {
        return this.chatTime;
    }

}
