package com.lmy.header;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.scwang.smartrefresh.layout.internal.pathview.PathsView;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**anyview
 * Created by painter.g on 2018/3/9.
 */

public class AnyHeader extends LinearLayout implements RefreshHeader {
    private TextView mHeaderText;//标题文本
    private PathsView mArrowView;//下拉箭头
    private ImageView mProgressView;//刷新动画视图
    private ProgressDrawable mProgressDrawable;//刷新动画
    private View view;
    private View pullDownToRefreshView;
    private View releaseToRefreshView;
    private View refreshingView;
    protected int mPaddingTop = 20;
    protected int mPaddingBottom = 20;

    public AnyHeader(Context context) {
        super(context);
        initView(context);
    }
    public AnyHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }
    public AnyHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }
    private void initView(Context context) {
        setGravity(Gravity.CENTER_HORIZONTAL);
        setOrientation(LinearLayout.VERTICAL);
        view = new TextView(context);
        //mProgressDrawable = new ProgressDrawable();
       // mArrowView = new PathsView(context);
        //mProgressView = new ImageView(context);
        //mProgressView.setImageDrawable(mProgressDrawable);
       // mArrowView.parserPaths("M20,12l-1.41,-1.41L13,16.17V4h-2v12.17l-5.58,-5.59L4,12l8,8 8,-8z");
       // addView(mProgressView, DensityUtil.dp2px(20), DensityUtil.dp2px(20));
       // addView(mArrowView, DensityUtil.dp2px(20), DensityUtil.dp2px(20));
        addView(new View(context), DensityUtil.dp2px(20), DensityUtil.dp2px(20));
        ((TextView)view).setText("SmartRefreshLayout");
        addView(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setMinimumHeight(DensityUtil.dp2px(60));
    }
    @NonNull
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }
    public void setView(View view){
        if(view !=null){
            setGravity(Gravity.CENTER_HORIZONTAL);
            setOrientation(LinearLayout.VERTICAL);
            removeAllViews();
            addView(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            setMinimumHeight(DensityUtil.dp2px(60));
        }
        //return this;
    }
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }
    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {
        //mProgressDrawable.start();//开始动画
    }
    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        return 500;//延迟500毫秒之后再弹回
    }
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
    }
    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
    }
    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }
    @Override
    public void onPulling(float percent, int offset, int headHeight, int extendHeight) {
    }

    @Override
    public void onReleased(RefreshLayout refreshLayout, int height, int extendHeight) {
    }

    @Override
    public void onReleasing(float percent, int offset, int headHeight, int extendHeight) {
    }
    @Override
    public void setPrimaryColors(@ColorInt int ... colors){
    }
}
