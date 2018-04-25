package com.example.wenkun.finalproject;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.wenkun.finalproject.MyRemoteViewsFactory;

/**
 * Created by ASUS on 2018/1/8.
 */

public class MyWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
