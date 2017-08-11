package com.rt.rtplasma.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.rt.rtplasma.interfaces.OnDialogClickListener;
import com.rt.rtplasma.utils.ActivityManager;
import com.rt.rtplasma.view.ConfirmDialog;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/3.
 */

public abstract class BaseActivity extends FragmentActivity {

    private SDKReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        ActivityManager.addActivity(this);
        setBarState();
        initView();
        initData();
        initListener();

        registerBaiD();
    }

    private void registerBaiD() {
        // 注册 SDK 广播监听者
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);
    }

    protected void setBarState() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {

                Toast.makeText(context, "key 验证出错! 错误码,请在 AndroidManifest.xml 文件中检查 key 设置", Toast.LENGTH_SHORT).show();

            } else if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {

                Toast.makeText(context, "key 验证成功! 功能可以正常使用", Toast.LENGTH_SHORT).show();
            } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                Toast.makeText(context, "网络出错", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 返回activity界面的布局文件
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 初始化子控件
     *
     * @return
     */
    protected abstract void initView();

    /**
     * 初始化数据
     *
     * @return
     */
    protected abstract void initData();

    /**
     * 初始化监听器
     *
     * @return
     */
    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 监听返回键弹出对话框，退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            ConfirmDialog dialog = new ConfirmDialog(this, "温馨提示", "确定退出系统吗？", new OnDialogClickListener() {
                @Override
                public void onConfirm(Dialog dialog) {
                    ActivityManager.finishAll();
                }

                @Override
                public void onCancel(Dialog dialog) {
                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
