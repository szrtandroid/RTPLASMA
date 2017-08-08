package com.rt.rtplasma.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.rt.rtplasma.R;
import com.rt.rtplasma.adapter.RunAdapter;
import com.rt.rtplasma.bean.PictureBean;
import com.rt.rtplasma.constants.Constants;
import com.rt.rtplasma.utils.OkHttpUtils;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RT3
 * on 2017/8/4.
 */

public class RunFragment extends BaseFragment {

    private static final int PICTURE = 1;
    @Bind(R.id.recyclerview_run)
    RecyclerView recyclerviewRun;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PICTURE:
                    String json= (String) msg.obj;
                    PictureBean pictureBean = new Gson().fromJson(json, PictureBean.class);
                    RunAdapter adapter=new RunAdapter(mActivity,pictureBean);
                    recyclerviewRun.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    public int getLayoutResId() {
        return R.layout.run_fragment;
    }

    @Override
    public void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerviewRun.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void initData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String data = getData(Constants.PICTURE_URL);
                    Message message=Message.obtain();
                    message.obj=data;
                    message.what=PICTURE;
                    mHandler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void initListener() {

    }

    /**
     * get的网络请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    String getData(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
