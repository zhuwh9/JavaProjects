package com.example.wenkun.finalproject;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    private TextInputLayout input_username_area;
    private TextInputLayout input_password_area;
    private TextInputLayout input_mailbox_area;
    private EditText input_username;
    private EditText input_password;
    private EditText input_mailbox;
    private Button register_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponent();
        Log.e("Register","1");

       register_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String username = input_username.getText().toString();
               String password = input_password.getText().toString();
               String mailbox = input_mailbox.getText().toString();
               if (!username.isEmpty() && !password.isEmpty() && !mailbox.isEmpty()) {


                   AccountLib al = (AccountLib) getApplicationContext();

                   try {
                       if (al.register(username, password, mailbox)) {
                           finish();
                       } else {
                           input_password_area.setEnabled(true);
                           input_password_area.setError("注册失败");
                       }
                   } catch (Exception e) {

                       input_password_area.setEnabled(true);
                       input_password_area.setError("注册出现异常: " + e);
                   }
               }
           }
       });
    }
    private void initComponent() {
        register_button = findViewById(R.id.register_button);
        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);
        input_mailbox = findViewById(R.id.input_mailbox);
        input_username_area = findViewById(R.id.input_username_area);
        input_password_area = findViewById(R.id.input_password_area);
        input_mailbox_area = findViewById(R.id.input_mailbox_area);
    }
}
