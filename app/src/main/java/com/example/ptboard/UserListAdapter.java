package com.example.ptboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;

    public UserListAdapter(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.user, null);
        TextView userNum = (TextView) v.findViewById(R.id.userNum);
        TextView userPwd = (TextView) v.findViewById(R.id.userPwd);
        TextView userName = (TextView) v.findViewById(R.id.userName);
        TextView userSex = (TextView) v.findViewById(R.id.userSex);

        userNum.setText(userList.get(i).getUserNum());
        userPwd.setText(userList.get(i).getUserPwd());
        userName.setText(userList.get(i).getUserName());
        userSex.setText(userList.get(i).getUserSex());

        v.setTag(userList.get(i).getUserNum());
        return v;
    }
}
