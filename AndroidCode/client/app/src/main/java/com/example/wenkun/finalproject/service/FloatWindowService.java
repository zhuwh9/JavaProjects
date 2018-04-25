package com.example.wenkun.finalproject.service;

/**
 * Created by ASUS on 2018/1/8.
 */

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.wenkun.finalproject.ApplicationInfoSet;
import com.example.wenkun.finalproject.ApplicationUsage;
import com.example.wenkun.finalproject.FloatWindow;
import com.example.wenkun.finalproject.R;
import com.example.wenkun.finalproject.view.PieCharView;

import java.util.ArrayList;
import java.util.List;

public class FloatWindowService extends Service {
    private MyBinder mBinder  = new MyBinder();

    private WindowManager.LayoutParams mLayoutParams;
    private PieCharView piecharView;
    private ArrayList<ImageButton> imgBtns = new ArrayList<>();
    private FloatWindow floatWindowForPie;
    private ArrayList<FloatWindow> floatWindowsForBtn = new ArrayList<>();
    private FrameLayout frameLayout;
    private ArrayList<FrameLayout> frameLayouts = new ArrayList<>();
//    private LinearLayout linearLayout;

    private List<ApplicationInfoSet> applicationInfoSets;
    private boolean hasShowIcon;
    private boolean hasShowPie;

    //  构造函数
    public FloatWindowService() {
        hasShowIcon = false;
        hasShowPie = false;
        //  启动线程
        mThread.start();
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId ) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //  初始化floatWindow
    private void initFloatWindow() {
        applicationInfoSets = ApplicationUsage.getAllInfo(this);

        frameLayout = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.sample_pie_char_view, null);
        piecharView = frameLayout.findViewById(R.id.myPieChart);
        piecharView.setData(applicationInfoSets);
        //  添加监听器
        setOnClickListenerForPieChart();

        floatWindowForPie = new FloatWindow(this);
        floatWindowForPie.setAlpha(0.5f);
        floatWindowForPie.setmContentView(frameLayout);

        for (int i = 0; i < 4; i++) {
            frameLayouts.add((FrameLayout)LayoutInflater.from(this).inflate(
                    R.layout.float_button, null));
        }
        for (FrameLayout fl : frameLayouts) {
            imgBtns.add((ImageButton)fl.findViewById(R.id.imgBtn));
            FloatWindow floatWindow = new FloatWindow(this);
            floatWindow.setmContentView(fl);
            floatWindowsForBtn.add(floatWindow);
        }
        setOnClickListenerForImgBtn();
        setPositionForFloatWindow();
        setLongClickListenerForPieChart();
        hasShowPie = true;
    }

    public class MyBinder extends Binder {
        public void initFloatWindowInBinder() {
            initFloatWindow();
        }
        public void showFloatWindow() {
            floatWindowForPie.show();
        }
        public void closeFloatWindow() {
            dismissFloatWindow();
        }
        public Boolean isShowingFloatWindow() {
            return hasShowPie;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    //  给扇形图添加点击事件
    private void setOnClickListenerForPieChart() {
        piecharView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (hasShowIcon) {
                    Log.e("action:", "dismiss float btn");
                    for(FloatWindow fw : floatWindowsForBtn) {
                        fw.dismiss();
                    }
                    hasShowIcon = false;
                } else {
                    for (int i = 0; i < imgBtns.size(); i++) {
                        ImageButton imgBtn = imgBtns.get(i);
                        try {
                            Log.e("action:", "present float btn");
                            //  根据包名获取应用图标
                            PackageManager pm = getPackageManager();
                            ApplicationInfo info = pm.getApplicationInfo(
                                    applicationInfoSets.get(i).getPackageName(), 0);
                            imgBtn.setImageDrawable(info.loadIcon(pm));
                            floatWindowsForBtn.get(i).show();
                        } catch (PackageManager.NameNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    hasShowIcon = true;
                }
            }
        });
    }
    //  给ImageButton添加点击事件
    private void setOnClickListenerForImgBtn() {
        for (int i = 0; i < 4; i++) {
            ImageButton imgBtn = imgBtns.get(i);
            final String packageName = applicationInfoSets.get(i).getPackageName();
            imgBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //  根据包名启动应用
                    PackageManager packageManager = getPackageManager();
                    Intent intent = packageManager.getLaunchIntentForPackage(packageName);
                    startActivity(intent);
                }
            });
        }
    }
    //  给各个悬浮窗设定位置
    private void setPositionForFloatWindow() {
        floatWindowsForBtn.get(0).setPosition(0, 80);
        floatWindowsForBtn.get(1).setPosition(120, 140);
        floatWindowsForBtn.get(2).setPosition(120,260);
        floatWindowsForBtn.get(3).setPosition(0, 320);
    }
    private void setLongClickListenerForPieChart() {
        piecharView.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "删除悬浮窗", Toast.LENGTH_SHORT).show();
                return dismissFloatWindow();
            }
        });
    }
    public Boolean dismissFloatWindow() {
        for (FloatWindow fl : floatWindowsForBtn) {
            fl.dismiss();
            fl.removeContent();
        }
        floatWindowForPie.dismiss();
        floatWindowForPie.removeContent();
        floatWindowsForBtn.clear();
        imgBtns.clear();
        frameLayouts.clear();
        hasShowIcon = false;
        hasShowPie = false;
        return true;
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
                    applicationInfoSets = ApplicationUsage.getAllInfo(FloatWindowService.this);
                    piecharView.setData(applicationInfoSets);
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
                if (hasShowPie) {
                    mHandler.obtainMessage(123).sendToTarget();
                }
            }
        }
    };
}
