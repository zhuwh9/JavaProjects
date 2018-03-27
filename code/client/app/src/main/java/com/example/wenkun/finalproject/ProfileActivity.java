package com.example.wenkun.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Wenkun on 2017/12/28.
 */

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_profile);

        final EditText age=(EditText)findViewById(R.id.age);
        final EditText phone=(EditText)findViewById(R.id.phone);
        final EditText email=(EditText)findViewById(R.id.email);
        final EditText local=(EditText)findViewById(R.id.local);
        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age.setFocusable(true);
                age.setFocusableInTouchMode(true);
                age.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(age,InputMethodManager.SHOW_FORCED);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.setFocusable(true);
                phone.setFocusableInTouchMode(true);
                phone.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(phone,InputMethodManager.SHOW_FORCED);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setFocusable(true);
                email.setFocusableInTouchMode(true);
                email.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(email,InputMethodManager.SHOW_FORCED);
            }
        });
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                local.setFocusable(true);
                local.setFocusableInTouchMode(true);
                local.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(local,InputMethodManager.SHOW_FORCED);
            }
        });
    }
}
