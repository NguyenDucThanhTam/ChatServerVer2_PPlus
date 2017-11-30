package com.example.rs.chatserverjava;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class GroupChat extends AppCompatActivity implements View.OnClickListener {

    String TAG = "socket";
    ListView lvMess;
    EditText edtMess;
    ImageButton btnSend;
    Socket socket;
    String host = "192.168.1.10";
    int port = 3000;
    BufferedReader is;
    BufferedWriter os;
    String getName;
    String inputMessenger;
    String actionSetname = "name";
    String actionChat = "chat";
    ArrayList<String> listMess;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        initMapped();

        initDisplay();

        initEventsHandler();

        bundleData();

        new connectServer().execute();

    }

    //setup list view
    private void initDisplay() {
        listMess = new ArrayList<>();
        //đảm bảo list view hoạt động tốt
        listMess.add("begin chat");
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listMess);
        lvMess.setAdapter(adapter);
    }

    private void bundleData() {
        Bundle getBundle = this.getIntent().getExtras();

        getName = getBundle.getString("user name");

        Toast.makeText(this, getName, Toast.LENGTH_SHORT).show();
    }


    //NOTE: set Events and Handler cho Component o day
    private void initEventsHandler() {
        btnSend.setOnClickListener(this);
    }

    //NOTE: Anh xa toan bo Component o day

    private void initMapped() {
        lvMess = (ListView) findViewById(R.id.lv_message);
        edtMess = (EditText) findViewById(R.id.edt_message);
        btnSend = (ImageButton) findViewById(R.id.btn_send_mess);
    }

    private void sendMessage() {
        //NOTE: This method handle when send/plapla message

    }

    private void receiveData() {
        //NOTE: code in here

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // button send
            case R.id.btn_send_mess:
                //đẩy dữ liệu lên server khi nhấn nút send
                //mở một luồng riêng để đẩy dữ liệu đi
                new sendMessage().start();
                break;
        }
    }

    //luồng riêng để gửi tin nhắn
    private class sendMessage extends Thread{
        @Override
        public void run() {
            super.run();
            //biến chứa message lấy từ edittext ra
            String outputMess = edtMess.getText().toString();

//            setMessageListView();

            //NOTE: haven't data so make comment

            //ERROR IN HERE
            try {
                // ghi dữ liệu vào luồng
                //dữ liệu theo cấu trúc <name>%<mess>%<action>
                os.write(getName + "%" + outputMess + "%" + actionChat);
                os.newLine();
                os.flush();
                Log.d(TAG, "sended: " + outputMess);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setMessageListView() {
        String outputMess = edtMess.getText().toString();
        ArrayList<String> arraList = new ArrayList<>();
            arraList.add(outputMess);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arraList);

        lvMess.setAdapter(adapter);

    }
    // mở 1 luồng để đăng kí với server
    private class register extends Thread{
        @Override
        public void run() {
            super.run();

        }
    }

    // mở một luồng xử lí server
    public class connectServer extends AsyncTask<Void, String, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                // connect với server với host và port cài đặt ở trê
                socket = new Socket(host, port);
                // mở luồng đọc ghi dữ liệu
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                // khai báo name cho server
                os.write(getName + "%" + getName + "%" + actionSetname);
                os.newLine();
                os.flush(); // đẩy dũ liệu lên

                Log.d(TAG, "connect sucess");

                //chờ tinh nhắn tới
                while (true) {
                    //chờ đến khi nào nhận được tin nhắn mới chạy tiếp
                    inputMessenger = is.readLine();
                    //update UI
                    publishProgress(inputMessenger);

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "connect error: " + e);
            }
            return null;

        }

        //NOTE cập nhật UI ở đây
        @Override
        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
            //values[0] là messenger nhận được
            listMess.add(values[0]);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
        }
    }

}
