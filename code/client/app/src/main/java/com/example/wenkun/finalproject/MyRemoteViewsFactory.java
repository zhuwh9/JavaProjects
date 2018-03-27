package com.example.wenkun.finalproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/1/8.
 */

public class MyRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final Context mContext;
    public static List<Essay> mList = new ArrayList<Essay>();

    public MyRemoteViewsFactory(Context context, Intent intent) {
        this.mContext = context;
        mList = intent.getExtras().getParcelableArrayList("note_list");
        Log.e("listsize",Integer.toString(mList.size()));
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
        mList.clear();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.note_view);
        rv.setTextViewText(R.id.title, mList.get(i).getTitle());
        rv.setTextViewText(R.id.date, mList.get(i).getTime());
        rv.setTextViewText(R.id.content, mList.get(i).getDetails());
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
