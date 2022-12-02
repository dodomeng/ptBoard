package com.example.ptboard;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // private로 MainActivity 클래스의 멤버 변수 만들어줌
    private ListView boardlistview;
    private BoardListAdapter adapter;
    private List<Board> boardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardlistview = (ListView) findViewById(R.id.boardListView);
        boardList = new ArrayList<Board>();
        //listView에 해당 adapter가 매칭이 됨으로써 adapter에 들어간 모든 내용들이 view형태로 list에 들어가서 보여짐
        adapter = new BoardListAdapter(getApplicationContext(), boardList);
        boardlistview.setAdapter(adapter);

        new BackgroundTask().execute();
    }

    //
    class BackgroundTask extends AsynkTask<Void, Void, String> implements com.example.ptboard.BackgroundTask
    {

        String target;

        // target에 해당 url을 적용
        @Override
        protected void onPreExecute() {
            target = "http://nonaphp.dothome.co.kr/boardList.php";
        }

        // 실질적으로 데이터를 얻어올 수 있는 부분
        @Override
        protected String doInBackgroud(Void... voids){
            try{
                // 서버에 접속할 수 있게 URL을 커넥팅
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                // 넘어오는 결과값들을 그대로 저장할 수 있게 해줌
                InputStream inputStream = httpURLConnection.getInputStream();
                // 해당 inputStream에 있는 내용을 버퍼에 담아서 읽을 수 있게 해줌
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                // temp를 만들어서 temp에 하나씩 읽어와서 문자 형태로 저장할 수 있게 해줌
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                // 버퍼에서 받아온 값을 한 줄 씩 읽으면서 temp에 넣으면서 null값이 아닐 때 까지 반복
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp + "\n");
                }
                // 죄다 닫아주고 끊어주면 문자열들이 반환
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        //
        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        // 결과를 처리할 수 있음
        @Override
        public void onPostExcute(String result) {
            try{
                // JSONObject로 해답 결과, result 즉 응답부분을 처리할 수 있게
                JSONObject jsonObject = new JSONObject(result);
                // JSONObject에서 getJSONArray를 받아올 수 있도록 -> response에 각각의 리스트가 담김
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String boardContent,boardName, boardDate, boardUser;
                // count가 jsonArray의 전체 크기보다 작을 때까지
                while(count < jsonArray.length())
                {
                    // 현재 배열의 원소 값을 저장할 수 있게 해줌
                    JSONObject object = jsonArray.getJSONObject(count);
                    // 오브젝트에서 각각에 해당하는 값을 가져와라
                    boardContent = object.getString("boardContent");
                    boardName = object.getString("boardName");
                    boardDate = object.getString("boardDate");
                    boardUser = object.getString("boardUser");
                    // 하나의 게시판에 대한 객체 생성
                    Board board = new Board(boardContent, boardName, boardDate, boardUser);
                    boardList.add(board);
                    count++;
                }
            } catch(Exception e) {

            }
        }
    }
}