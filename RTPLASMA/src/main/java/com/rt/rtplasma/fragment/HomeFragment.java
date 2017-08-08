package com.rt.rtplasma.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.rt.rtplasma.R;
import com.rt.rtplasma.adapter.HomeAdapter;
import com.rt.rtplasma.view.RecycleViewDivider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by RT3
 * on 2017/8/4.
 */

public class HomeFragment extends BaseFragment {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private String[] mDatas = {"花谢花飞花满天",
            "红消香断有谁怜",
            "游丝软系飘春榭",
            "落絮轻沾扑绣帘",
            "闺中女儿惜春暮",
            "愁绪满怀无释处",
            "手把花锄出绣帘",
            "忍踏落花来复去",
            "柳丝榆荚自芳菲",
            "不管桃飘与李飞",
            "桃李明年能再发",
            "明年闺中知有谁",
            "三月香巢初垒成",
            "梁间燕子太无情",
            "明年花发虽可啄",
            "却不道人去梁空巢也倾",
            "一年三百六十日",
            "风刀霜剑严相逼",
            "明媚鲜妍能几时",
            "一朝漂泊难寻觅",
            "花开易见落难寻",
            "阶前愁杀葬花人"};

    @Override
    public int getLayoutResId() {
        return R.layout.home_fragment;
    }

    @Override
    public void initView() {
        mRecyclerview.setHasFixedSize(true);
//        mRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayout.VERTICAL, false));
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,1));

        HomeAdapter adapter = new HomeAdapter(mActivity, mDatas);
        mRecyclerview.setAdapter(adapter);
        mRecyclerview.addItemDecoration(new RecycleViewDivider(mActivity,LinearLayoutManager.HORIZONTAL));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}
