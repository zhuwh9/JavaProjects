package com.example.wenkun.finalproject;

/**
 * Created by ASUS on 2018/1/8.
 */

public class ApplicationInfoSet {
    private String packageName;
    private Long packageUseTime;
    private Integer packageLaunchCount;
    public ApplicationInfoSet(String pn, long put, int lt) {
        this.packageName = pn;
        this.packageUseTime = put;
        this.packageLaunchCount = lt;
    }
    public String getPackageName() {
        return packageName;
    }
    public Long getPackageUseTime() {
        return packageUseTime;
    }
    public Integer getPackageLaunchCount() {
        return packageLaunchCount;
    }
}

