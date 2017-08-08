package com.rt.rtplasma.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.rt.rtplasma.R;
import com.rt.rtplasma.utils.WebViewManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by RT3
 * on 2017/8/4.
 */

public class DiscoverFragment extends BaseFragment {

    @Bind(R.id.webview)
    WebView mWebview;

    @Override
    public int getLayoutResId() {
        return R.layout.discover_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        String url="http://www.baidu.com/";
        WebViewManager.initWebView(mWebview,url,mActivity);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebview != null) {
            mWebview.removeAllViews();
            mWebview.destroy();
            mWebview = null;
        }
    }
}
