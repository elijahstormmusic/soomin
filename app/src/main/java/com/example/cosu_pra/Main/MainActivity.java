package com.example.cosu_pra.Main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.cosu_pra.ConnectFB.ChatStream;
import com.example.cosu_pra.DTO.ChatRoomList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.cosu_pra.R;
import com.example.cosu_pra.Testing;
import com.example.cosu_pra.DTO.User;


public class MainActivity extends AppCompatActivity {

    private static User client;

    public static void setClientInformation(String email, String psw, String rName, String nName, String userID) {
        client = new User(email, psw, rName, nName, userID);
    }

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (client != null) {
            Log.d("MainActivity", "client info is not null");
            ChatRoomList.init(client.getUserID());
        }
        else {
            Log.d("MainActivity", "client info IS null");
            ChatRoomList.init("member1");   // for test
        }

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());


                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_1);
    }

    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            if (id == R.id.navigation_1) {
                fragment = new Fragment1();
            } else if (id == R.id.navigation_2){
                fragment = new Fragment2();
            }else if (id == R.id.navigation_3){
                fragment = new Fragment3();
            } else if (id == R.id.navigation_4){
                fragment = new Fragment4();
            } else{
                fragment = new Fragment5();
            }

            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();


    }
}