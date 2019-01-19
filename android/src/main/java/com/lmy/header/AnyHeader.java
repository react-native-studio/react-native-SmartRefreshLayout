package com.lmy.header;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.views.view.ReactViewGroup;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * anyview
 * Created by painter.g on 2018/3/9.
 */

public class AnyHeader extends ReactViewGroup implements RefreshHeader, OnMultiPurposeListener{
    private RefreshKernel mRefreshKernel;
    private int mBackgroundColor;
    private Integer mPrimaryColor;
    private SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;
    private int mDelay = 500;

    private AnyHeaderEventListener mHeaderEventListener;

    private static final String TAG = "smart-any-header";

    public AnyHeader(Context context) {
        super(context);
        initView(context);
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
        mRefreshKernel = kernel;
        //添加关于RefreshLayout的事件的监听
        mRefreshKernel.getRefreshLayout().setOnMultiPurposeListener(this);
        mRefreshKernel.requestDrawBackgroundForHeader(mBackgroundColor);
    }

    private void initView(Context context) {
        setMinimumHeight(DensityUtil.dp2px(60));
    }

    public void setView(View v) {
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
     *
     * @param colors
     */
    @Override
    public void setPrimaryColors(int... colors) {
        if (colors.length > 0) {
            if (!(getBackground() instanceof BitmapDrawable) && mPrimaryColor == null) {
                setPrimaryColor(colors[0]);
                mPrimaryColor = null;
            }
        }
    }

    public AnyHeader setPrimaryColor(@ColorInt int primaryColor) {
        mBackgroundColor = mPrimaryColor = primaryColor;
        if (mRefreshKernel != null) {
            mRefreshKernel.requestDrawBackgroundForHeader(mPrimaryColor);
        }
        return this;
    }

    public AnyHeader setSpinnerStyle(SpinnerStyle style) {
        this.mSpinnerStyle = style;
        return this;
    }

    /**
     * 将RefreshLayout组件主动滚动到某个纵坐标
     * @param params {x: number, y: number, animated: bool}
     *               现阶段有效的字段仅有y, 后续可能会用得上x和animated字段
     */
    public void scrollTo(ReadableMap params) {
        int y = params.getInt("y");
        //如果isAnimator传入false, 表示是属于手指拖拽事件, 组件内部会自动给这个offset打折
        mRefreshKernel.moveSpinner(y, false);
    }

    /**
     * 设置header的监听器
     * @param headerEventListener
     */
    public void setHeaderEventListener(AnyHeaderEventListener headerEventListener) {
        mHeaderEventListener = headerEventListener;
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
        return mDelay;//延迟500毫秒之后再弹回
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

    @Override
    public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
        /*StringBuffer sb = new StringBuffer("onHeaderPulling: percent: ")
                .append(percent + ", ")
                .append("offset: ")
                .append(offset)
                .append(", ")
                .append("headerHeight: ")
                .append(headerHeight)
                .append(", ")
                .append("extendHeight: ")
                .append(extendHeight)
                .append(", ");
        Log.v(TAG, sb.toString());*/
        if (mHeaderEventListener != null){
            mHeaderEventListener.onHeaderMove(offset);
        }
    }

    @Override
    public void onHeaderReleased(RefreshHeader header, int headerHeight, int extendHeight) {

    }

    @Override
    public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
        /*StringBuffer sb = new StringBuffer("onHeaderReleasing: percent: ")
                .append(percent + ", ")
                .append("offset: ")
                .append(offset)
                .append(", ")
                .append("headerHeight: ")
                .append(headerHeight)
                .append(", ")
                .append("extendHeight: ")
                .append(extendHeight)
                .append(", ");
        Log.v(TAG, sb.toString());
        if (percent == 0){
            Log.v(TAG, "onHeaderScrollFinished");
        }*/
        if (mHeaderEventListener != null){
            mHeaderEventListener.onHeaderMove(offset);
            if (percent == 0){
                mHeaderEventListener.onHeaderMoveFinished();
            }
        }
    }

    @Override
    public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int extendHeight) {

    }

    @Override
    public void onHeaderFinish(RefreshHeader header, boolean success) {

    }

    @Override
    public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
        /*StringBuffer sb = new StringBuffer("onFooterPulling: percent: ")
                .append(percent + ", ")
                .append("offset: ")
                .append(offset)
                .append(", ")
                .append("footerHeight: ")
                .append(footerHeight)
                .append(", ")
                .append("extendHeight: ")
                .append(extendHeight)
                .append(", ");
        Log.v(TAG, sb.toString());*/
        if (mHeaderEventListener != null){
            mHeaderEventListener.onFooterMove(offset);
        }
    }

    @Override
    public void onFooterReleased(RefreshFooter footer, int footerHeight, int extendHeight) {
    }

    @Override
    public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
        /*StringBuffer sb = new StringBuffer("onFooterReleasing: percent: ")
                .append(percent + ", ")
                .append("offset: ")
                .append(offset)
                .append(", ")
                .append("footerHeight: ")
                .append(footerHeight)
                .append(", ")
                .append("extendHeight: ")
                .append(extendHeight)
                .append(", ");
        Log.v(TAG, sb.toString());

        if (percent == 0){
            Log.v(TAG, "onFooterScrollFinished");
        }*/
        if (mHeaderEventListener != null){
            mHeaderEventListener.onFooterMove(offset);
            if (percent == 0){
                mHeaderEventListener.onFooterMoveFinished();
            }
        }
    }

    @Override
    public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int extendHeight) {

    }

    @Override
    public void onFooterFinish(RefreshFooter footer, boolean success) {
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {

    }
}
