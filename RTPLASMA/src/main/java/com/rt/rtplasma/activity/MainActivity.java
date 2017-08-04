package com.rt.rtplasma.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rt.rtplasma.R;
import com.rt.rtplasma.fragment.BaseFragment;
import com.rt.rtplasma.fragment.DiscoverFragment;
import com.rt.rtplasma.fragment.HomeFragment;
import com.rt.rtplasma.fragment.MeFragment;
import com.rt.rtplasma.fragment.RunFragment;
import com.rt.rtplasma.utils.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_run)
    RadioButton rbRun;
    @Bind(R.id.rb_discover)
    RadioButton rbDiscover;
    @Bind(R.id.rb_me)
    RadioButton rbMe;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.tv_title)
    TextView mTv_title;

    private List<BaseFragment> mBaseFragment;

    /**
     * 选中的Fragment的对应的位置
     */
    private int position;
    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new HomeFragment());
        mBaseFragment.add(new RunFragment());
        mBaseFragment.add(new DiscoverFragment());
        mBaseFragment.add(new MeFragment());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选项
        rgMain.check(R.id.rb_home);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.rb_home:
                    position = 0;
                    mTv_title.setText("首页");
                    break;
                case R.id.rb_run:
                    position = 1;
                    mTv_title.setText("行动");
                    break;
                case R.id.rb_discover:
                    position = 2;
                    mTv_title.setText("发现");
                    break;
                case R.id.rb_me:
                    position = 3;
                    mTv_title.setText("我");
                    break;
                default:
                    position = 0;
                    mTv_title.setText("首页");
                    break;
            }

            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFrament(mContent, to);
        }
    }


    private BaseFragment getFragment() {
        return mBaseFragment.get(position);
    }

    private void switchFrament(Fragment from, BaseFragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.fl_main, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }
    }

}
