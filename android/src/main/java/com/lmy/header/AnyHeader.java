package com.lmy.header;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.facebook.react.views.view.ReactViewGroup;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**anyview
 * Created by painter.g on 2018/3/9.
 */

public class AnyHeader extends ReactViewGroup implements RefreshHeader {
    private RefreshKernel mRefreshKernel;
    private int mBackgroundColor;
    private Integer mPrimaryColor;
    private SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;

    public AnyHeader(Context context) {
        super(context);
        initView(context);
    }
    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
        mRefreshKernel = kernel;
        mRefreshKernel.requestDrawBackgroundForHeader(mBackgroundColor);
    }
    private void initView(Context context) {
        setMinimumHeight(DensityUtil.dp2px(60));
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
        return this.mSpinnerStyle;//指定为平移，不能null
    }

    /**
     * 设置主题色
     * @param colors
     */
    @Override
    public void setPrimaryColors(int... colors) {
        if(colors.length>0) {
            if (!(getBackground() instanceof BitmapDrawable) && mPrimaryColor == null) {
                setPrimaryColor(colors[0]);
                mPrimaryColor = null;
            }
        }
    }

    public AnyHeader setPrimaryColor(@ColorInt int primaryColor) {
        mBackgroundColor = mPrimaryColor =primaryColor;
        if (mRefreshKernel != null) {
            mRefreshKernel.requestDrawBackgroundForHeader(mPrimaryColor);
        }
        return this;
    }

    public AnyHeader setSpinnerStyle(SpinnerStyle style){
        this.mSpinnerStyle = style;
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
