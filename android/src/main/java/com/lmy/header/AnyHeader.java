package com.lmy.header;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**anyview
 * Created by painter.g on 2018/3/9.
 */

public class AnyHeader extends RelativeLayout implements RefreshHeader {
    private RefreshKernel mRefreshKernel;
    private int mBackgroundColor;

    public AnyHeader(Context context) {
        super(context);
    }
    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
        mRefreshKernel = kernel;
       mRefreshKernel.requestDrawBackgroundForHeader(mBackgroundColor);
    }
    private void initView(Context context) {
        //setMinimumHeight(DensityUtil.dp2px(60));
    }
    public void setView(View v){
        addView(v);
    }
    @NonNull
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    public AnyHeader setPrimaryColor(@ColorInt int primaryColor) {
        mBackgroundColor = primaryColor;
        if (mRefreshKernel != null) {
            mRefreshKernel.requestDrawBackgroundForHeader(primaryColor);
        }
        return this;
    }

    @Override
    public void onPulling(float percent, int offset, int height, int extendHeight) {

    }

    @Override
    public void onReleasing(float percent, int offset, int height, int extendHeight) {

    }

    @Override
    public void onReleased(RefreshLayout refreshLayout, int height, int extendHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int extendHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }
    /*@Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            addView(getChildAt(i));
        }
        super.onFinishInflate();
    }*/
}
