package com.rt.rtplasma.utils;

import android.graphics.Color;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.rt.rtplasma.bean.UserInfo;
import com.rt.rtplasma.constants.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RT3
 * on 2017/8/8.
 */

public class BMapManager {


    /**
     * 在地图上添加文字
     *
     * @param baiduMap
     * @param ll
     */
    public static void drawText(BaiduMap baiduMap, LatLng ll) {
        TextOptions textOptions = new TextOptions();
        textOptions.fontColor(Color.BLUE)
                .fontSize(55)
                .rotate(30)
                .text("启奥朗程")
                .position(ll);
        baiduMap.addOverlay(textOptions);

    }

    public static void drawMarker(BaiduMap baiduMap, LatLng ll) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.isPerspective();

//        baiduMap.addOverlay(textOptions);
    }


    public static List<UserInfo> getPersonInfo() {

        List<UserInfo> mUserInfoList = new ArrayList<UserInfo>();

        String[] persons = User.user.split("\n");
        for (int i = 0; i < persons.length; i++) {
            String[] split = persons[i].split("\t");
            UserInfo userInfo = new UserInfo();
            userInfo.setNum(split[0]);
            userInfo.setName(split[1]);
            userInfo.setAddress(split[2]);
            mUserInfoList.add(userInfo);
        }

        return mUserInfoList;
    }
}
