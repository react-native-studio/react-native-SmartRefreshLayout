package com.lmy.header;

import android.content.Context;

import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * Created by painter.g on 2018/3/7.
 */

public class ReactClassicsHeader extends ClassicsHeader {
    static {
        //ClassicsHeader.REFRESH_HEADER_PULLING = "下拉可以刷新";
        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在刷新";
        ClassicsHeader.REFRESH_HEADER_LOADING = "正在加载";
        ClassicsHeader.REFRESH_HEADER_RELEASE = "释放立即刷新";
        ClassicsHeader.REFRESH_HEADER_FINISH = "刷新完成";
        ClassicsHeader.REFRESH_HEADER_FAILED = "刷新失败";
        //ClassicsHeader.REFRESH_HEADER_UPDATE = "上次更新 M-d HH:mm";
        //ClassicsHeader.REFRESH_HEADER_SECONDARY = "释放进入二楼";
    }

    public ReactClassicsHeader(Context context) {
        super(context);
    }
}
