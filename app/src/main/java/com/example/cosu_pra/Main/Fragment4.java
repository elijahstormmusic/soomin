package com.example.cosu_pra.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cosu_pra.Adapter.ChatRoomAdapter;
import com.example.cosu_pra.ChattingActivity;
import com.example.cosu_pra.Adapter.ChatRoomItem;
import com.example.cosu_pra.ConnectFB.ChatStream;
import com.example.cosu_pra.DTO.ChatRoomList;
import com.example.cosu_pra.DTO.Chatroom;
import com.example.cosu_pra.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
//참고 : https://itqna.net/questions/87594/how-create-custom-listview-inside-fragment

public class Fragment4 extends Fragment {
    ChatStream chatHelper;
    SharedPreferences sh_Pref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment4, container, false);
        ListView chatRoomView = (ListView) v.findViewById(R.id.mainlist);

        List<ChatRoomItem> chatroomList = ChatRoomList.getChatRoomListData();
        ChatRoomAdapter adapter = new ChatRoomAdapter(getActivity(), chatroomList);

        for (ChatRoomItem item : chatroomList) {
            adapter.addItem(item);
        }
        chatRoomView.setAdapter(adapter);

        return v;
    }

    //TODO 단톡방 클릭 이벤트
    public void onListItemClick(ListView l, View v, int position, long id) {
        String strText = (String) l.getItemAtPosition(position);

        Log.d("listview: ", position + ": " +strText);

            // ChatStream links to database and updates on change
            // replace "Ij2L74mfcmWTIjDRwsBL" with the ChatRoomItem.databaseReference
        ChatStream chat = ChatRoomList.open_chat("Ij2L74mfcmWTIjDRwsBL");

        Log.d("test2", "start running");

        Log.d("test2", "chat info size: " + chat.chat_info.size());
        if (chat.chat_info.size() > 0) {
            Log.d("test2", "chat info get 0: " + chat.chat_info.get(0));
        }

        // TODO: the data draws correctly, but the UI is not finished

        Intent intent = new Intent(getActivity(), ChattingActivity.class);
        startActivity(intent);
    }


}
