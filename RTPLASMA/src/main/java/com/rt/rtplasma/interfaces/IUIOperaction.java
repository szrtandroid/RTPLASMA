package com.rt.rtplasma.interfaces;

import android.view.View;

/**
 * Created by Administrator on 2017/1/4.
 */

public interface IUIOperaction {

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
    public abstract void initView();


    /**
     * 初始化数据
     *
     * @return
     */
    public abstract void initData();


    /**
     * 初始化监听器
     *
     * @return
     */
    public abstract void initListener();
    
}
