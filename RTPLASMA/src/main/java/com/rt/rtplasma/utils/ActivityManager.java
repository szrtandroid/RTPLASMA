package com.rt.rtplasma.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RT3
 * on 2017/8/4.
 * 退出系统
 */

public class ActivityManager {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (activity.isFinishing()) {
                activity.finish();
            }
            System.exit(0);
        }
    }


}
