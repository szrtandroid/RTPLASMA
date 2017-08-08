package com.rt.rtplasma.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rt.rtplasma.activity.BaseActivity;
import com.rt.rtplasma.interfaces.IUIOperaction;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/1/4.
 */

public abstract class BaseFragment extends Fragment implements IUIOperaction {
   
    public BaseActivity mActivity;
    public View mRoot;
    public OkHttpClient client;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mRoot == null) {
            mRoot = View.inflate(mActivity, getLayoutResId(), null);
            ButterKnife.bind(this, mRoot);
            client = new OkHttpClient();
            initView();
            initData();
            initListener();

        } else {
            unbindWithParent(mRoot);
        }
        return mRoot;
    }

    /**
     * 解除控件与其父控件的父子关系
     * @param view
     */
    private void unbindWithParent(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
    }

    /**
     * 查找控件，可以省略强转操作
     * @param id
     * @return
     */
    public <T> T findView(int id) {
        T view = (T) mRoot.findViewById(id);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
