package com.lmy.header;

import android.content.Context;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.components.DeferredReleaser;
import com.lmy.smartrefreshlayout.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.scwang.smartrefresh.header.internal.pathview.PathsView;
import com.scwang.smartrefresh.layout.util.SmartUtil;

/**
 * Created by painter.g on 2018/3/12.
 */

public class DefaultHeader extends RelativeLayout implements RefreshHeader {
    private TextView mHeaderText;//标题文本
    private PathsView mArrowView;//下拉箭头
    private ImageView mProgressView;//刷新动画视图
    private ProgressDrawable mProgressDrawable;//刷新动画
    protected RefreshKernel mRefreshKernel;
    protected int mBackgroundColor;
    protected int mAccentColor;

    public DefaultHeader(Context context) {
        super(context);
        this.initView(context);
    }

    public DefaultHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public DefaultHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }


    private void initView(Context context) {
        RelativeLayout parent = new RelativeLayout(context);
        RelativeLayout.LayoutParams rlParent = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlParent.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);


        RelativeLayout.LayoutParams rlArrowView = new RelativeLayout.LayoutParams(SmartUtil.dp2px(20),SmartUtil.dp2px(20));
        mArrowView = new PathsView(context);
        mArrowView.setId(R.id.arrow_view);
        mArrowView.parserColors(0xff666666);
        mArrowView.parserPaths("M20,12l-1.41,-1.41L13,16.17V4h-2v12.17l-5.58,-5.59L4,12l8,8 8,-8z");
        parent.addView(mArrowView,rlArrowView);


        RelativeLayout.LayoutParams rlHeaderText= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlHeaderText.addRule(RelativeLayout.RIGHT_OF,mArrowView.getId());
        rlHeaderText.leftMargin = SmartUtil.dp2px(20);
        mHeaderText = new TextView(context);
        mHeaderText.setText("下拉开始刷新");
        parent.addView(mHeaderText,rlHeaderText);

        RelativeLayout.LayoutParams rlProgressView = new RelativeLayout.LayoutParams(SmartUtil.dp2px(20),SmartUtil.dp2px(20));
        rlProgressView.addRule(RelativeLayout.ALIGN_RIGHT,mArrowView.getId());
        mProgressDrawable = new ProgressDrawable();
        mProgressView = new ImageView(context);
        mProgressView.setImageDrawable(mProgressDrawable);
        parent.addView(mProgressView,rlProgressView);


        addView(parent,rlParent);
        //addView(mProgressView, SmartUtil.dp2px(20), SmartUtil.dp2px(20));
        //addView(mArrowView, SmartUtil.dp2px(20), SmartUtil.dp2px(20));
        //addView(new View(context), SmartUtil.dp2px(20), SmartUtil.dp2px(20));
        //addView(mHeaderText, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setMinimumHeight(SmartUtil.dp2px(60));
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
    public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {
        mProgressDrawable.start();//开始动画
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        mProgressDrawable.stop();//停止动画
        if (success) {
            mHeaderText.setText("刷新完成");
        } else {
            mHeaderText.setText("刷新失败");
        }
        return 500;//延迟500毫秒之后再弹回
    }
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mHeaderText.setText("下拉开始刷新");
                mArrowView.setVisibility(VISIBLE);//显示下拉箭头
                mProgressView.setVisibility(GONE);//隐藏动画
                mArrowView.animate().rotation(0);//还原箭头方向
                break;
            case Refreshing:
                mHeaderText.setText("正在刷新");
                mProgressView.setVisibility(VISIBLE);//显示加载动画
                mArrowView.setVisibility(GONE);//隐藏箭头
                break;
            case ReleaseToRefresh:
                mHeaderText.setText("释放立即刷新");
                mArrowView.animate().rotation(180);//显示箭头改为朝上
                break;
        }
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
        mRefreshKernel = kernel;
        mRefreshKernel.requestDrawBackgroundFor(this, mBackgroundColor);

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }


    @Override
    public void onReleased(RefreshLayout refreshLayout, int height, int extendHeight) {

    }

    @Override
    public void setPrimaryColors(@ColorInt int... colors) {
    }

    public DefaultHeader setPrimaryColor(@ColorInt int primaryColor) {
        mBackgroundColor = primaryColor;
        if (mRefreshKernel != null) {
            mRefreshKernel.requestDrawBackgroundFor(this, primaryColor);
//            mRefreshKernel.requestDrawBackgroundForHeader(primaryColor);
        }
        return this;
    }
    public DefaultHeader setAccentColor(int accentColor){
        mAccentColor=accentColor;
        if(mArrowView!=null){
            mArrowView.parserColors(accentColor);
        }
        if(mProgressDrawable!=null) {
            mProgressDrawable.setColor(accentColor);
        }
        mHeaderText.setTextColor(accentColor);
        return this;
    }

}
