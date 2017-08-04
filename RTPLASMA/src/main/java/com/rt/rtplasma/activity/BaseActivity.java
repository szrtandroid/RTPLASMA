package com.rt.rtplasma.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;

import com.rt.rtplasma.interfaces.OnDialogClickListener;
import com.rt.rtplasma.utils.ActivityManager;
import com.rt.rtplasma.view.ConfirmDialog;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/3.
 */

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        ActivityManager.addActivity(this);
        setBarState();
        initView();
        initData();
        initListener();
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
