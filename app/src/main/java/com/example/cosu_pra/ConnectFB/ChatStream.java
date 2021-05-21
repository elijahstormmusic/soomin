package com.example.cosu_pra.ConnectFB;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.cosu_pra.DTO.ChatData;
import com.example.cosu_pra.DTO.Chatroom;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.*;

public class ChatStream {
    private FirebaseFirestore db;

    public final static String CHAT = "ChatRoom";
    public final static String MSG = "Messages";
    private final String _database_reference;

    public List<String> chat_info;

    public ChatStream(String _input_database) {
        _database_reference = _input_database;

        db = FirebaseFirestore.getInstance();
        chat_info = new ArrayList<String>();
    }

    public void makeCharRoom(List<String> userList) {


        Chatroom chm = new Chatroom(userList);

        db.collection(CHAT).add(chm);
    }

    public void addChat(String roomID, String userID, String msg) {
//        ChatData chat = new ChatData(userID, msg);

//        db.collection(CHAT).document(roomID)
//                .collection(MSG).add(chat);
    }

    public void addChat(String roomID, ChatData chat) {
        db.collection(CHAT).document(roomID)
                .collection(MSG).add(chat);
    }

    public void waitMSG() {
        db.collection(CHAT).document(_database_reference)
                .collection(MSG).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value,
                                @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w("test2", "Listen failed.", error);
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            Log.d("test2", "New : " + dc.getDocument().getData());
                            chat_info.add("created from database: " + dc.getDocument().getData().get("msg"));
                            break;
                        case MODIFIED:
                            Log.d("test2", "Modified : " + dc.getDocument().getData());
                            break;
                        case REMOVED:
                            Log.d("test2", "Removed : " + dc.getDocument().getData());
                            break;
                    }
                }
                for (QueryDocumentSnapshot doc : value) {

                    chat_info.add("created from document value: " + doc.get("msg").toString());

                    Log.d("test2", doc.get("msg").toString());

                }
            }
        });

    }
}
