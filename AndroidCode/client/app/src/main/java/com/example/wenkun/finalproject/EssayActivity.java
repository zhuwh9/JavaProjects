package com.example.wenkun.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wenkun on 2017/12/27.
 */

public class EssayActivity  extends AppCompatActivity {

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay);



        final List<Essay> data = new ArrayList<Essay>();
        data.add(new Essay("dummy","19/19","dummy"));



        final EssayAdapter myAdapter=new EssayAdapter(this,data);
        myAdapter.notifyDataSetChanged();

        myAdapter.setOnItemClickListener(new EssayAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                String t="点击"+(position+1)+"个";
                Essay essay=(Essay) myAdapter.getItemData(position);

                Intent intent=new Intent(EssayActivity.this,EssayDetailsActivity.class);
                intent.putExtra("essay",essay);

                startActivity(intent);
                //Toast.makeText(MainActivity.this,t,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                myAdapter.removeData(position);
                myAdapter.notifyDataSetChanged();
                String t="移除第"+(position+1)+"行";
                Toast.makeText(EssayActivity.this,t,Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myAdapter);

        Button addItem=(Button)findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EssayActivity.this,EssayDetailsActivity.class);

                startActivity(intent);
            }
        });
        //手势识别实现左右侧滑
        mGestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {// e1: 第一次按下的位置   e2   当手离开屏幕 时的位置  velocityX  沿x 轴的速度  velocityY： 沿Y轴方向的速度
                //判断竖直方向移动的大小
                if(Math.abs(e1.getRawY() - e2.getRawY())>100){
                    //Toast.makeText(getApplicationContext(), "动作不合法", 0).show();
                    return true;
                }
                if(Math.abs(velocityX)<150){
                    //Toast.makeText(getApplicationContext(), "移动的太慢", 0).show();
                    return true;
                }

                if((e1.getRawX() - e2.getRawX()) >200){// 表示 向右滑动表示下一页
                    //显示下一页
                    Log.e("to left","fuck");
                    Intent intent=new Intent(EssayActivity.this,MainActivity.class);
                    startActivity(intent);
                    return true;
                }

                if((e2.getRawX() - e1.getRawX()) >200){  //向左滑动 表示 上一页
                    //显示上一页

                    return true;//消费掉当前事件  不让当前事件继续向下传递
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    //重写activity的触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //2.让手势识别器生效
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}