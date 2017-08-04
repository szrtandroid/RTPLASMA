package com.rt.rtplasma.activity;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2017/8/3.
 */

public class MyApplication extends Application {
    private static Context context;
    private static long mainThreadId;
    private static Handler mainThreadHandler;

    public static Context getContext() {
        return context;
    }

    public static long getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getMainThreadHandler() {
        return mainThreadHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //主线程id
        mainThreadId = android.os.Process.myTid();
        //创建主线程handler
        mainThreadHandler = new Handler();

    }
}
