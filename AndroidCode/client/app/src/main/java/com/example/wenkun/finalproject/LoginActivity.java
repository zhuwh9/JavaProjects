package com.example.wenkun.finalproject;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private String adminUsr = "admin";
    private String adminPwd = "admin";
    private Button login_button;
    private Button forget_password_button;
    private Button register_button;
    private TextInputLayout input_username_area;
    private TextInputLayout input_password_area;
    private EditText input_username;
    private EditText input_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();
        addListener();
    }

    private void addListener() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = input_username.getText().toString();
                String password = input_password.getText().toString();
                if (!username.isEmpty() && !password.isEmpty()) {

                    AccountLib al = (AccountLib) getApplicationContext();

                    try {
                        if (al.login(username, password)) {
                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            intent.putExtra("username",username);
                            intent.putExtra("password",password);
                            startActivity(intent);
                            finish();
                        } else {
                            input_password_area.setEnabled(true);
                            input_password_area.setError("用户名或密码错误");
                        }
                    } catch (Exception e) {

                        input_password_area.setEnabled(true);
                        input_password_area.setError("登录出现异常: " + e);
                    }
                }
                if (username.isEmpty()) {
                    input_username_area.setEnabled(true);
                    input_username_area.setError("用户名为空");
                }
                if (password.isEmpty()) {
                    input_password_area.setEnabled(true);
                    input_password_area.setError("密码为空");
                }
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Register.class));
            }
        });
    }

    private void initComponent() {
        login_button = findViewById(R.id.login_button);
        forget_password_button = findViewById(R.id.forget_password_button);
        register_button = findViewById(R.id.register_button);
        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);
        input_username_area = findViewById(R.id.input_username_area);
        input_password_area = findViewById(R.id.input_password_area);
    }
}
