package com.lmy.header;

import android.content.Context;

import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * Created by painter.g on 2018/3/7.
 */

public class ReactClassicsFooter extends ClassicsFooter {
    static {
       // ClassicsFooter.REFRESH_FOOTER_PULLING = "上拉加载更多";
        ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放立即加载";
        ClassicsFooter.REFRESH_FOOTER_LOADING = "正在加载";
        ClassicsFooter.REFRESH_FOOTER_REFRESHING ="正在刷新";
        ClassicsFooter.REFRESH_FOOTER_FINISH = "加载完成";
        ClassicsFooter.REFRESH_FOOTER_FAILED = "加载失败";
        //ClassicsFooter.REFRESH_FOOTER_NOTHING = "没有更多数据了";
    }
    public ReactClassicsFooter(Context context) {
        super(context);
    }
}
