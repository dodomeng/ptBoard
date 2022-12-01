package com.example.ptboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// BaseAdapter 상속 받아서 사용
// Adapter 만드는 거임
public class NoticeListAdapter extends BaseAdapter {
    
    // Alt+Enter로 메소드 만들어줌
    private Context context;
    // notice가 들어갈 list 만들어줌
    private List<Notice> noticeList;
    
    // constructor로 생성자 만들어줌
    public NoticeListAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // R.layout.notice의 디자인을 사용하게 해줌
        View v = View.inflate(context, R.layout.notice, null);
        // 객체 만들어줌
        TextView noticeText  = (TextView) v.findViewById(R.id.noticeText);
        TextView nameText  = (TextView) v.findViewById(R.id.nameText);
        TextView dateText  = (TextView) v.findViewById(R.id.dateText);
        
        // noticeText를 현재 list에 있는 값으로 넣어줄 수 있게 해줌
        noticeText.setText(noticeList.get(i).getNotice());
        nameText.setText(noticeList.get(i).getName());
        dateText.setText(noticeList.get(i).getDate());
        
        // 태그를 붙여줌 noticeList.get(i)에 있는 getNotice를 줌
        v.setTag(noticeList.get(i).getNotice());
        // 해당 뷰 반환
        return v;
    }


}
