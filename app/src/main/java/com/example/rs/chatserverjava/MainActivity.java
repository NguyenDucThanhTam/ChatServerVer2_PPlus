package com.example.rs.chatserverjava;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edt_name_user;
    Button btn_join;
    TextInputLayout txt_layout_name;
    String getNameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Anh xa
        initMapped();
        // Method Events Handler
        initEventsHandler();

    }

    private void initEventsHandler() {

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmptyUser();
            }
        });


    }

    private void connectToServer() {
        //NOTE: Code connect type in here
        //Don't write in others
        joinGroupChat();


    }

    private void initMapped() {
        edt_name_user = (TextInputEditText) findViewById(R.id.edt_name_user);
        btn_join = (Button) findViewById(R.id.btn_join);
        txt_layout_name = (TextInputLayout) findViewById(R.id.txtinput_user);

        //Set hint for Text Input Layout
        txt_layout_name.setHint("User Name");
        //Enable check erro for TextInputLayout
        txt_layout_name.setErrorEnabled(true);
    }


    //NOTE: Hide Keyboard when start App
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //NOTE: method JOIN GroupChat

    private void joinGroupChat() {
        Bundle bundle = new Bundle();

        Intent intent = new Intent(MainActivity.this, GroupChat.class);

        //NOTE: Check Empty User


        bundle.putString("user name", getNameUser);

        intent.putExtras(bundle);

        startActivity(intent);

    }

    private void checkEmptyUser() {
        //NOTE: Check User NOT empty in here .....
        getNameUser = edt_name_user.getText().toString().trim();
        if(getNameUser.isEmpty()){
            edt_name_user.setError("Please enter your name");
        } else{
            joinGroupChat();
        }
    }
}
