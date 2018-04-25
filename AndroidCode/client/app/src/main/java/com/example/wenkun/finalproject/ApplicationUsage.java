package com.example.wenkun.finalproject;

/**
 * Created by ASUS on 2018/1/8.
 */

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jeff on 2018/1/5.
 */

public class ApplicationUsage {
    private static ArrayList<ApplicationInfoSet> allInfo = new ArrayList<>();
    /**
     * please first set context, and use the following static method
     */
    public static ArrayList<ApplicationInfoSet> getAllInfo(Context context) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            context.startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
//        }
        UsageStatsManager m = (UsageStatsManager)context.getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis()-24*60*60*1000;
        allInfo.clear();
        List<UsageStats> list = m.queryUsageStats(UsageStatsManager.INTERVAL_BEST, time, System.currentTimeMillis());
        if (list != null) {
            Log.e("List","not null");
            Log.e("Size",Integer.toString(list.size()));
            for (int i = 0; i < list.size(); i++) {
                try {
                    boolean flag = false;
                    String packageName = list.get(i).getPackageName();
                    long packageUseTime = list.get(i).getTotalTimeInForeground();
                    int packageLaunchCount = list.get(i).getClass().getDeclaredField("mLaunchCount").getInt(list.get(i));
                    ApplicationInfoSet info = new ApplicationInfoSet(packageName,packageUseTime,packageLaunchCount);
//                    Log.e("Package " + Integer.toString(i), "[Packagename=" + packageName + ", Times:" + Long.toString(packageUseTime) + ", OpenTime:" + Integer.toString(packageLaunchCount) + "]");
                    for (ApplicationInfoSet appInfoSet : allInfo) {
                        if (appInfoSet.getPackageName().equals(packageName)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        allInfo.add(info);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //  按使用时间降序排序
            Collections.sort(allInfo, new Comparator<ApplicationInfoSet>() {
                @Override
                public int compare(ApplicationInfoSet o1, ApplicationInfoSet o2) {
                    return o2.getPackageUseTime().compareTo(o1.getPackageUseTime());
                }
            });
            for (ApplicationInfoSet appInfoSet : allInfo) {
                //Log.e("Package ", "[Packagename=" + appInfoSet.getPackageName() + ", Times:" + Long.toString(appInfoSet.getPackageUseTime()) + ", OpenTime:" + Integer.toString(appInfoSet.getPackageLaunchCount()) + "]");
            }
            return allInfo;
        } else {
            Log.e("Error", "list null");
            return null;
        }
    }
}
