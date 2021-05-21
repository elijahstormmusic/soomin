package com.example.cosu_pra.DTO;

import com.example.cosu_pra.Adapter.ChatRoomItem;
import com.example.cosu_pra.ConnectFB.ChatStream;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.cosu_pra.DTO.User;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ChatRoomList {
    private static FirebaseFirestore db;

    public final static String CHAT = "ChatRoom";
    public final static String MSG = "Messages";

    private static List<ChatRoomItem> _roomsData;

    public static ChatStream open_chat(String _chat_database_link) {
        ChatStream chat = new ChatStream(_chat_database_link);

        chat.waitMSG();

        return chat;
    }

    public static List<ChatRoomItem> getChatRoomListData() {
        List<ChatRoomItem> displayData = new ArrayList<ChatRoomItem>();

        for (int i=0;i<_roomsData.size();i++) {
            displayData.add(_roomsData.get(i));
        }

        return displayData;
    }

    private static void turnRoomsIntoListData(Task<QuerySnapshot> rooms) {
        rooms.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Chat", document.getData().toString());

                        try {
                            Object roomName = document.get("roomName");
                            Object lastMsg = document.get("lastMSG");
                            Object lastTime = document.get("lastTime");
                            Object userObjectList = document.get("userList");

                            if (roomName == null) {
                                return;
//                                throw new NullPointerException("Room name null");
                            }
                            if (lastMsg == null) {
                                return;
//                                throw new NullPointerException("Last message null");
                            }
                            if (lastTime == null) {
                                return;
//                                throw new NullPointerException("Last time null");
                            }
                            if (userObjectList == null) {
                                return;
//                                throw new NullPointerException("User list null");
                            }

                            List<String> userNameList = Arrays.asList(userObjectList.toString());
                            List<User> userList = new ArrayList<User>();

                            // TODO: once the userlist is filled, draw from the data here
                            for (int i=0;i<userNameList.size();i++) {
                                userList.add(new User(
                                    "email",
                                    "pwd",
                                    "realName",
                                    userNameList.get(i),
                                    "userID"
                                ));
                            }

                            Log.d("Chat", "this is the user list " + userList);

                            _roomsData.add(
                                new ChatRoomItem(
                                    document.getId(),
                                    roomName.toString(),
                                    lastMsg.toString(),
                                    lastTime.toString(),
                                    userList
                                )
                            );
                        }
                        catch (Error e) {
                            Log.e("Chat", "There was an error");
                        }
                    }
                } else {
                    Log.d("ChatData", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    public static void init(String userID) {

        db = FirebaseFirestore.getInstance();
        _roomsData = new ArrayList<ChatRoomItem>();

        turnRoomsIntoListData(
                db.collection(CHAT).whereArrayContains("userList", userID).get()
        );
    }
}
