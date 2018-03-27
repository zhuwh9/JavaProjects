package com.example.wenkun.finalproject.adapter;

/**
 * Created by ASUS on 2018/1/8.
 */


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.wenkun.finalproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jeff on 2018/1/8.
 */

public class AppListAdapter extends BaseAdapter {
    private Context context;
    private List<HashMap<String, String>> list = new ArrayList<>();
    private List<Drawable> iconList = new ArrayList<>();

    public AppListAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<HashMap<String, String>> list) {
        this.list = list;
    }
    public void setIconList(List<Drawable> iconList) {
        this.iconList = iconList;
    }
    @Override
    public int getCount() {
        return this.list == null ? 0 : this.list.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list == null ? null : this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView;
        ViewHolder viewHolder;
        if (view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.app_listview_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.appImage = (ImageView)convertView.findViewById(R.id.appImage);
            viewHolder.packageName = (TextView)convertView.findViewById(R.id.appPackage);
            viewHolder.time = (TextView)convertView.findViewById(R.id.appTime);
            viewHolder.percent = (TextView)convertView.findViewById(R.id.appTimePercent);
            convertView.setTag(viewHolder);
        } else {
            convertView = view;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.appImage.setImageDrawable(iconList.get(i));
        viewHolder.packageName.setText(list.get(i).get("packageName"));
        viewHolder.time.setText(list.get(i).get("time"));
        viewHolder.percent.setText(list.get(i).get("percent"));
        return convertView;
    }

    class ViewHolder {
        ImageView appImage;
        TextView packageName;
        TextView time;
        TextView percent;
    }
}

