package com.example.ptboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<User>();

        adapter = new UserListAdapter(getApplicationContext(), userList);
        listView.setAdapter(adapter);

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String UserNum, UserPwd, UserName, UserSex;
            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                UserNum = object.getString("UserNum");
                UserPwd = object.getString("UserPwd");
                UserName = object.getString("UserName");
                UserSex = object.getString("UserSex");
                User user = new User(UserNum, UserPwd, UserName, UserSex);
                userList.add(user);
                count++;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}