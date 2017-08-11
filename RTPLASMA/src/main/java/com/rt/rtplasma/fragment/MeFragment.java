package com.rt.rtplasma.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.rt.rtplasma.R;
import com.rt.rtplasma.activity.LocationActivity;

import butterknife.Bind;

/**
 * Created by RT3
 * on 2017/8/4.
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.btn_location)
    Button mBtnLocation;

    @Override
    public int getLayoutResId() {
        return R.layout.me_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mBtnLocation.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_location:
                Intent intent = new Intent(mActivity, LocationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
