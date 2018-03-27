package com.example.wenkun.finalproject.view;

/**
 * Created by ASUS on 2018/1/8.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.example.wenkun.finalproject.ApplicationInfoSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义饼状图
 */
public class PieCharView extends View {
    private int mWidth;
    private int mHeight;
    private int pieSize;
    private String[] pieNames = new String[5];
    private RectF oval;
    private List<Long> useTimeList = new ArrayList<>();
    private List<Float> degreeList = new ArrayList<>();
    private List<Integer> launchTimeList = new ArrayList<>();
    private List<ApplicationInfoSet> appInfoList = new ArrayList<>();
    private int[] paintColors = new int[5];
    private Paint mPaint;

    //  外界获取饼状图数据的方法
    public String[] getPackageNames() {
        return pieNames;
    }
    public List<Long> getUseTimeList() {
        return useTimeList;
    }
    public List<Float> getDegreeList() {
        return degreeList;
    }
    public List<Integer> getLaunchTimeList() {
        return launchTimeList;
    }


    public PieCharView(Context context) {
        this(context, null);
    }

    public PieCharView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieCharView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //  设置数据并刷新
    public void setData(List<ApplicationInfoSet> appInfoList) {
        this.appInfoList = appInfoList;
        paintColors = new int[] {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.WHITE};
        setPieNames();
        setDegreeList();
        setLaunchTimeList();
        postInvalidate();
    }

    //  记录使用时长前三的应用的名字
    private void setPieNames() {
        int count = (appInfoList.size() >= 4) ? 4 : appInfoList.size();
        for (int i = 0; i < count; i++) {
            pieNames[i] = appInfoList.get(i).getPackageName();
            Log.e("Rank " + i, pieNames[i]);
        }
        pieNames[count] = "Others";
    }

    //  求出各部分角度
    private void setDegreeList() {
        useTimeList.clear();
        degreeList.clear();
        Long totalTime = 0L;
        for (ApplicationInfoSet appInfoSet : appInfoList) {
            totalTime += appInfoSet.getPackageUseTime();
        }
        int count = (appInfoList.size() >= 4) ? 4 : appInfoList.size();
        for (int i = 0; i < count; i++) {
            useTimeList.add(appInfoList.get(i).getPackageUseTime());
            degreeList.add(Float.parseFloat(useTimeList.get(i).toString()) / totalTime * 360);
        }
        degreeList.add(360 - degreeList.get(0) - degreeList.get(1) - degreeList.get(2) - degreeList.get(3));
    }
    //  记录应用使用次数
    private void setLaunchTimeList() {
        launchTimeList.clear();
        int count = (appInfoList.size() >= 4) ? 4 : appInfoList.size();
        for (int i = 0; i < count; i++) {
            launchTimeList.add(appInfoList.get(i).getPackageLaunchCount());
        }
    }
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2f);
        mPaint.setColor(Color.WHITE);
        setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackGroundLines(canvas);
        drawArcView(canvas);
    }

    protected void drawArcView(Canvas canvas) {
        float starDegree = -90;
        mPaint.setTextSize(30);

        for (int i = 0; i < degreeList.size(); i++) {
            mPaint.setColor(paintColors[i]);
            //Log.e("listDegree", degreeList.get(i).toString());
            canvas.drawArc(oval, starDegree, degreeList.get(i), true, mPaint);
            starDegree += degreeList.get(i);
        }
    }

    private void drawBackGroundLines(Canvas canvas) {
        mPaint.setColor(Color.WHITE);  //  背景色
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
        canvas.drawLine(0, 0, mWidth, 0, mPaint);
        canvas.drawLine(0, mHeight, mWidth, mHeight, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        oval = new RectF(0, 0, mWidth, mHeight);
    }

}

