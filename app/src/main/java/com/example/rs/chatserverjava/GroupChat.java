package com.example.rs.chatserverjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GroupChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        initMapped();

        initEventsHandler();

        bundleData();
    }

    private void bundleData() {
        Bundle getBundle = this.getIntent().getExtras();

        String getName = getBundle.getString("user name");

        Toast.makeText(this, getName, Toast.LENGTH_SHORT).show();
    }



    //NOTE: set Events and Handler cho Component o day
     private void initEventsHandler() {

    }

    //NOTE: Anh xa toan bo Component o day

    private void initMapped() {
    }

    private void sendMessage(){
        //NOTE: This method handle when send/plapla message

    }

    private void receiveData(){
        //NOTE: code in here

    }

}
