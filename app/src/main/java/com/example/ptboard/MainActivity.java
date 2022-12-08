package com.example.ptboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
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
    private ListView userlistview;
    private UserListAdapter adapter;
    private List<User> userList;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new BackgroundTask().execute();
            }
        });
    }



    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://nonaphp.dothome.co.kr/List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result) {
            Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
            intent.putExtra("userList", result);
            MainActivity.this.startActivity(intent);
        }
    }
}



/*String target;

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
    } */




    /* class GettingPHP extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();

                if ( conn != null ) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        while ( true ) {
                            String line = br.readLine();
                            if ( line == null )
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            try {
                // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                JSONObject jObject = new JSONObject(str);
                // results라는 key는 JSON배열로 되어있다.
                JSONArray results = jObject.getJSONArray("results");
                String zz = "";
                zz += "Status : " + jObject.get("status");
                zz += "\n";
                zz += "Number of results : " + jObject.get("num_result");
                zz += "\n";
                zz += "Results : \n";

                for ( int i = 0; i < results.length(); ++i ) {
                    JSONObject temp = results.getJSONObject(i);
                    zz += "\tdoc_idx : " + temp.get("doc_idx");
                    zz += "\tmember_idx : " + temp.get("member_idx");
                    zz += "\tsubject : " + temp.get("subject");
                    zz += "\tcontent : " + temp.get("content");
                    zz += "\treg_date : " + temp.get("reg_date");
                    zz += "\n\t--------------------------------------------\n";
                }
                tv.setText(zz);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    } */
