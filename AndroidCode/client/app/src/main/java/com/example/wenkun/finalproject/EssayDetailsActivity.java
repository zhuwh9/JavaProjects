package com.example.wenkun.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Wenkun on 2017/12/27.
 */

public class EssayDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay_details);

        final Intent intent = getIntent();
        final Essay essay = intent.getParcelableExtra("essay");

//        final String s_title=essay.getTitle();
//        final String s_details=essay.getDetails();
//        final String s_time=essay.getTime();

        final TextView time=(TextView)findViewById(R.id.time);
        final TextView title=(TextView) findViewById(R.id.title);
        final TextView details=(TextView) findViewById(R.id.details);

        int t=0;
        String action = intent.getAction();
        if (action != null) {
            if (action.equals("addItem")) {

            } else if (action.equals("updateItem")){
                time.setText(essay.getTime());
                t=essay.id;
                title.setText(essay.getTitle());
                details.setText(essay.getDetails());
            }
        }
        final int id = t;
        final Button cancel=(Button)findViewById(R.id.cancel);
        final Button ok=(Button)findViewById(R.id.ok);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title.clearFocus();
                details.clearFocus();
                title.setFocusable(false);
                title.setFocusableInTouchMode(false);
                details.setFocusable(false);
                details.setFocusableInTouchMode(false);
                time.setText(essay.getTime());
                title.setText(essay.getTitle());
                details.setText(essay.getDetails());
                //隐藏软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(cancel.getWindowToken(), 0); //强制隐藏键盘

                cancel.setVisibility(View.INVISIBLE);
                ok.setVisibility(View.INVISIBLE);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    title.clearFocus();
                    details.clearFocus();

                    title.setFocusable(false);
                    title.setFocusableInTouchMode(false);
                    details.setFocusable(false);
                    details.setFocusableInTouchMode(false);

                    cancel.setVisibility(View.INVISIBLE);
                    ok.setVisibility(View.INVISIBLE);

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(ok,InputMethodManager.SHOW_FORCED);
                    imm.hideSoftInputFromWindow(ok.getWindowToken(), 0);

                    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss ");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    time.setText(str);
                    Essay essay1 = new Essay(title.getText().toString(),time.getText().toString(),details.getText().toString(), id);
                    AccountLib al = (AccountLib) getApplicationContext();
                    try {
                        if (intent.getAction().equals("updateItem")) {
                            al.update(essay1);
                            Intent i=new Intent();
                            i.putExtra("title", title.getText().toString());
                            i.putExtra("datetime", time.getText().toString());
                            i.putExtra("data", details.getText().toString());
                            i.putExtra("id", id);
                            i.putExtra("position", intent.getIntExtra("position",-1));
                            setResult(RESULT_OK,i);
                        } else if (intent.getAction().equals("addItem")) {
                            al.add(essay1);
                            Intent i=new Intent();
                            i.putExtra("title", title.getText().toString());
                            i.putExtra("datetime", time.getText().toString());
                            i.putExtra("data", details.getText().toString());
                            i.putExtra("id", id);
                            i.putExtra("position", -1);
                            setResult(RESULT_OK,i);
                        }

                        Toast.makeText(EssayDetailsActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(EssayDetailsActivity.this,"更新失败: " + e,Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setFocusable(true);
                title.setFocusableInTouchMode(true);
                title.requestFocus();
                cancel.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(title,InputMethodManager.SHOW_FORCED);
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setFocusable(true);
                details.setFocusableInTouchMode(true);
                details.requestFocus();
                cancel.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(details,InputMethodManager.SHOW_FORCED);
            }
        });
    }
}