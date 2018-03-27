package com.example.wenkun.finalproject;

/**
 * Created by ASUS on 2018/1/8.
 */

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.example.wenkun.finalproject.adapter.AppListAdapter;
import com.example.wenkun.finalproject.service.FloatWindowService;
import com.example.wenkun.finalproject.view.PieCharView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppManagement extends AppCompatActivity {
    private PieCharView pieCharView;
    private FloatWindow floatWindow;
    private List<ApplicationInfoSet> applicationInfoSets;
    private ArrayList<HashMap<String, String>> listItem = new ArrayList<>();
    private List<Drawable> iconList = new ArrayList<>();
    private ListView listView;
    private AppListAdapter appListAdapter;
    private DecimalFormat df;

    private FloatWindowService.MyBinder myBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("msg", "Connected to service");
//            floatWindowService = ((FloatWindowService.MyBinder) service).getService();
            myBinder = (FloatWindowService.MyBinder) service;

            //  点击piecharview
            pieCharView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (!myBinder.isShowingFloatWindow()) {
                            Toast.makeText(getApplicationContext(), "启动悬浮窗，可长按删除", Toast.LENGTH_SHORT).show();
                            myBinder.initFloatWindowInBinder();
                            myBinder.showFloatWindow();
                        } else {
//                        myBinder.closeFloatWindow();
                            Toast.makeText(getApplicationContext(), "已启动悬浮窗，可长按删除", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceConnection = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_management);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.e("AppManagement","onCreate");
        df = new DecimalFormat("#.##");
        pieCharView = findViewById(R.id.pieInManagement);
        listView = findViewById(R.id.appList);
        initList();
        addDataToPieChartAndListView();
        renewListView();
        mThread.start();
        Log.e("AppManagement","thread start");
        Intent intent = new Intent(this, FloatWindowService.class);
        startService(intent);
        Log.e("AppManagement","start service");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.e("AppManagement","bind service");
    }
    //  初始化listview
    private void initList() {
        appListAdapter = new AppListAdapter(this);
        listView.setAdapter(appListAdapter);
    }
    //  更新listview
    private void renewListView() {
        appListAdapter.setList(listItem);
        appListAdapter.setIconList(iconList);
        appListAdapter.notifyDataSetChanged();
    }
    //  给饼状图添加数据
    private void addDataToPieChartAndListView() {
        listItem.clear();
        addTitle();
        //  pieChart
        PackageManager pm = getPackageManager();
        applicationInfoSets = ApplicationUsage.getAllInfo(this);
        pieCharView.setData(applicationInfoSets);
        //  listView
        for (int i = 0; i < pieCharView.getUseTimeList().size(); i++) {
            HashMap map = new HashMap();
            map.put("time", String.valueOf(pieCharView.getUseTimeList().get(i) / (1000 * 60)) + "min");
            map.put("percent", df.format(pieCharView.getDegreeList().get(i) / 3.6) + "%");
            try{
                iconList.add(pm.getApplicationIcon(pieCharView.getPackageNames()[i]));
                ApplicationInfo info = pm.getApplicationInfo(pieCharView.getPackageNames()[i], 0);
                map.put("packageName", info.loadLabel(pm).toString());
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            listItem.add(map);
        }
    }
    private void addTitle() {
        HashMap map = new HashMap();
        map.put("packageName", "前四应用名称");
        map.put("time", "使用时间");
        map.put("percent", "百分比");
        listItem.add(map);
        iconList.add(getDrawable(R.drawable.tpimg));
    }

    //  定期刷新悬浮窗
    //  定义mHandler
    @SuppressLint("HandlerLeak")
    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 123:
                    //  UI更新
                    addDataToPieChartAndListView();
                    renewListView();
                    break;

            }
        }
    };
    //  定义新线程
    Thread mThread = new Thread() {
        @Override
        public void run() {
            while(true) {
                try  {
                    //  每10秒进行一次刷新
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.obtainMessage(123).sendToTarget();
            }
        }
    };
}
