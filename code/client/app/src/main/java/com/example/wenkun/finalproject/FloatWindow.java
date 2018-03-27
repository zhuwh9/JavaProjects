package com.example.wenkun.finalproject;

/**
 * Created by ASUS on 2018/1/8.
 */
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Jeff on 2018/1/2.
 */

public class FloatWindow {
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;
    private Context mContext;

    private View mContentView;
    private DisplayMetrics mDisplayMetrics;
    private boolean isShowing;


    //  构造函数
    public FloatWindow(Context context) {
        mContext = context;
        mContentView = null;
        isShowing = false;
    }
    //  获取上下文
    private Context getmContext() {
        return this.mContext;
    }

    private WindowManager getmWindowManager() {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) getmContext().getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    private WindowManager.LayoutParams getmLayoutParams() {
        if (mLayoutParams == null) {
            mLayoutParams = new WindowManager.LayoutParams();
            initLayoutParams();
        }
        return mLayoutParams;
    }

    //  获取显示信息
    public DisplayMetrics getDisplayMetrics() {
        if (mDisplayMetrics == null) {
            mDisplayMetrics = getmContext().getResources().getDisplayMetrics();
        }
        return mDisplayMetrics;
    }

    //  获取当前视图
    private View getmContentView() {
        return this.mContentView;
    }

    //  设置contentView
    public void setmContentView(View contentView) {
        mContentView = contentView;
    }
    //  设置初始位置
    public void setPosition(int x, int y) {
        getmLayoutParams().x = x;
        getmLayoutParams().y = y;
    }
    //  初始化LayoutParams
    private void initLayoutParams() {
        getmLayoutParams().flags = getmLayoutParams().flags
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        getmLayoutParams().dimAmount = 0.2f;
        getmLayoutParams().type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        getmLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
        getmLayoutParams().width = WindowManager.LayoutParams.WRAP_CONTENT;
        getmLayoutParams().gravity = Gravity.END | Gravity.TOP;
        getmLayoutParams().format = PixelFormat.RGBA_8888;
        getmLayoutParams().alpha = 1f;  //  设置整个窗口的透明度
        getmLayoutParams().x = 0;
        getmLayoutParams().y = 200;

        //  使可移动

    }

    //  显示窗口
    public void show() {
        if (getmContentView() != null && !isShowing) {
            getmWindowManager().addView(getmContentView(), getmLayoutParams());
            isShowing = true;
        }
    }
    //  撤销窗口
    public void dismiss() {
        if (getmContentView() != null && isShowing) {
            getmWindowManager().removeView(getmContentView());
            isShowing = false;
        }
    }
    //  删除content
    public void removeContent() {
        mContentView = null;
    }
    //  设置透明度
    public void setAlpha(float a) {
        getmLayoutParams().alpha = a;
    }

}

