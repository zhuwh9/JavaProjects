package com.example.wenkun.finalproject;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Wenkun on 2017/12/27.
 */

public class Essay implements Parcelable {
    private String title;
    private String time;
    private String details;
    public Integer id;
    public String getTitle() {return title;}
    public String getTime() {return time;}
    public String getDetails() {return details;}
    public void setTitle(String t0) {title=t0;}
    public void setTime(String t1) {time=t1;}
    public void setDetails(String d) {details=d;}
    public Essay(String _title, String _datetime, String _detail) {
        title=_title;
        time=_datetime;
        details=_detail;
        id = 0;
    }
    public Essay(String _title, String _datetime, String _detail, int _id) {
        title=_title;
        time=_datetime;
        details=_detail;
        id = _id;
    }
    public Essay() {
        id = 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(time);
        dest.writeString(details);
        dest.writeInt(id);
//        String a[]={title,time,details};
//        dest.writeStringArray(a);
    }
    public static final Parcelable.Creator<Essay> CREATOR=new Parcelable.Creator<Essay>() {
        @Override
        public Essay createFromParcel(Parcel source) {
            Essay essay = new Essay();
//            String []a={};
//            source.readStringArray(a);
            essay.setTitle(source.readString());
            essay.setTime(source.readString());
            essay.setDetails(source.readString());
            essay.id = source.readInt();

            return essay;
        }

        @Override
        public Essay[] newArray(int size) {
            return new Essay[0];
        }
    };

}
