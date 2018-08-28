package com.lmy.smartrefreshlayout;

import android.graphics.Color;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.lmy.header.AnyHeader;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by painter.g on 2018/3/6.
 * SmartRefreshLayout插件的封装
 * https://github.com/scwang90/SmartRefreshLayout
 */

public class SmartRefreshLayoutManager extends ViewGroupManager<ReactSmartRefreshLayout>{
    //返回给rn的组件名
    protected static final String REACT_CLASS="SmartRefreshLayout";

    private ReactSmartRefreshLayout smartRefreshLayout;
    private RCTEventEmitter mEventEmitter;
    private ThemedReactContext themedReactContext;

    private static final String COMMAND_FINISH_REFRESH_NAME="finishRefresh";
    private static final int COMMAND_FINISH_REFRESH_ID=0;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ReactSmartRefreshLayout createViewInstance(ThemedReactContext reactContext) {
        smartRefreshLayout=new ReactSmartRefreshLayout(reactContext);
        smartRefreshLayout.setEnableLoadMore(false);//暂时禁止上拉加载
        themedReactContext=reactContext;
        mEventEmitter=reactContext.getJSModule(RCTEventEmitter.class);
        return smartRefreshLayout;
    }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder builder = MapBuilder.builder();
        for (Events event : Events.values()) {
            builder.put(event.toString(), MapBuilder.of("registrationName", event.toString()));
        }
        return builder.build();
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                COMMAND_FINISH_REFRESH_NAME,COMMAND_FINISH_REFRESH_ID
        );
    }
    /**
     * 最大显示下拉高度/Header标准高度
     * @param view
     * @param maxDragRate
     */
    @ReactProp(name="maxDragRate",defaultFloat = 2.0f)
    public void setMaxDragRate(ReactSmartRefreshLayout view,float maxDragRate){
        view.setHeaderMaxDragRate(maxDragRate);
    }
    /**
     * 显示下拉高度/手指真实下拉高度=阻尼效果
     * @param view
     * @param dragRate
     */
    @ReactProp(name = "dragRate",defaultFloat = 0.5f)
    public void setDragRate(ReactSmartRefreshLayout view,float dragRate){
        view.setDragRate(dragRate);
    }
    /**
     * 是否使用越界拖动
     * @param view
     * @param overScrollDrag
     */
    @ReactProp(name="overScrollDrag",defaultBoolean = true)
    public void setOverScrollDrag(ReactSmartRefreshLayout view,boolean overScrollDrag){
        view.setEnableOverScrollDrag(overScrollDrag);
    }
    /**
     * 是否启用越界回弹
     * @param view
     * @param overScrollBounce
     */
    @ReactProp(name = "overScrollBounce",defaultBoolean = true)
    public void setOverScrollBounce(ReactSmartRefreshLayout view,boolean overScrollBounce){
        view.setEnableOverScrollBounce(overScrollBounce);
    }
    /**
     * 设置为纯滚动
     * @param view
     * @param pureScroll
     */
    @ReactProp(name = "pureScroll",defaultBoolean = false)
    public void setPureScroll(ReactSmartRefreshLayout view,boolean pureScroll){
        view.setEnablePureScrollMode(pureScroll);
    }
    /**
     * 通过RefreshLayout设置主题色
     * @param view
     * @param primaryColor
     */
    @ReactProp(name = "primaryColor",defaultInt = Color.TRANSPARENT)
    public void setPrimaryColor(ReactSmartRefreshLayout view, int primaryColor){
        view.setPrimaryColors(primaryColor);
    }
    /**
     * 设置headerHeight
     * @param view
     * @param headerHeight
     */
    @ReactProp(name = "headerHeight")
    public void setHeaderHeight(ReactSmartRefreshLayout view,float headerHeight){
        if(headerHeight != 0.0f) {
            view.setHeaderHeight(headerHeight);

        }
    }
    /**
     * 是否启用下拉刷新功能
     * @param view
     * @param enableRefresh
     */
    @ReactProp(name="enableRefresh",defaultBoolean = true)
    public void setEnableRefresh(ReactSmartRefreshLayout view,boolean enableRefresh){
        view.setEnableRefresh(enableRefresh);
    }

    /**
     * 是否启用自动刷新
     * @param view
     * @param autoRefresh
     */
    @ReactProp(name = "autoRefresh",defaultBoolean = false)
    public void setAutoRefresh(ReactSmartRefreshLayout view, ReadableMap autoRefresh){
        boolean isAutoRefresh=false;Integer time=null;
        if(autoRefresh.hasKey("refresh")){
            isAutoRefresh=autoRefresh.getBoolean("refresh");
        }
        if(autoRefresh.hasKey("time")){
            time=autoRefresh.getInt("time");
        }
        if(isAutoRefresh==true){
            if(time!=null && time>0){
                view.autoRefresh(time);
            }else{
                view.autoRefresh();
            }
        }
    }
    @Override
    public void receiveCommand(ReactSmartRefreshLayout root, int commandId, @Nullable ReadableArray args) {
        switch (commandId){
            case COMMAND_FINISH_REFRESH_ID:
                int delayed=args.getInt(0);
                boolean success=args.getBoolean(1);
                if(delayed>=0){
                    root.finishRefresh(delayed,success);
                }else{
                    root.finishRefresh(success);
                }
                break;
            default:break;
        }
    }

    @Override
    public void addView(ReactSmartRefreshLayout parent, View child, int index) {
        switch (index){
            case 0:
                RefreshHeader header;
                if(child instanceof RefreshHeader){
                    header=(RefreshHeader)child;
                }else{
                    header=new AnyHeader(themedReactContext);
                    ((AnyHeader)header).setView(child);
                }
                parent.setRefreshHeader(header);
                //parent.setRefreshHeader(new MaterialHeader(themedReactContext).setShowBezierWave(true));
                break;
            case 1:
                parent.setRefreshContent(child);
                break;
            case 2:
                //RefreshFooter footer=(RefreshFooter)child;
                //parent.setRefreshFooter(footer);
                break;
            default:break;

        }
    }

    @Override
    public void addViews(ReactSmartRefreshLayout parent, List<View> views) {
        super.addViews(parent, views);
    }

    @Override
    protected void addEventEmitters(ThemedReactContext reactContext,final ReactSmartRefreshLayout view) {


        /**
         * 必须设置OnRefreshListener，如果没有设置，
         * 则会自动触发finishRefresh
         *
         * OnRefreshListener和OnSimpleMultiPurposeListener
         * 中的onRefresh都会触发刷新，只需写一个即可
         */
        view.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {

            }
        });
        view.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            private int getTargetId(){
                return view.getId();
            }

            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                WritableMap writableMap = Arguments.createMap();
                writableMap.putDouble("percent",percent);
                writableMap.putDouble("offset",DensityUtil.px2dp(offset));
                writableMap.putDouble("headerHeight",DensityUtil.px2dp(headerHeight));
                mEventEmitter.receiveEvent(getTargetId(),Events.HEADER_PULLING.toString(),writableMap);
            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int extendHeight) {
                WritableMap writableMap = Arguments.createMap();
                writableMap.putDouble("headerHeight",DensityUtil.px2dp(headerHeight));
                mEventEmitter.receiveEvent(getTargetId(),Events.HEADER_RELEASED.toString(),writableMap);
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                WritableMap writableMap = Arguments.createMap();
                writableMap.putDouble("percent",percent);
                writableMap.putDouble("offset",DensityUtil.px2dp(offset));
                writableMap.putDouble("headerHeight",DensityUtil.px2dp(headerHeight));
                mEventEmitter.receiveEvent(getTargetId(),Events.HEADER_RELEASING.toString(),writableMap);
            }

            @Override
            public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                WritableMap writableMap = Arguments.createMap();
                writableMap.putDouble("percent",percent);
                writableMap.putDouble("offset",DensityUtil.px2dp(offset));
                writableMap.putDouble("footerHeight",DensityUtil.px2dp(footerHeight));
                mEventEmitter.receiveEvent(getTargetId(),Events.FOOTER_MOVING.toString(),writableMap);
            }

            @Override
            public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                WritableMap writableMap = Arguments.createMap();
                writableMap.putDouble("percent",percent);
                writableMap.putDouble("offset",DensityUtil.px2dp(offset));
                writableMap.putDouble("footerHeight",DensityUtil.px2dp(footerHeight));
                mEventEmitter.receiveEvent(getTargetId(),Events.FOOTER_MOVING.toString(),writableMap);
            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int extendHeight) {

            }

            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {

            }
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mEventEmitter.receiveEvent(getTargetId(),Events.LOAD_MORE.toString(),null);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mEventEmitter.receiveEvent(getTargetId(),Events.REFRESH.toString(),null);
            }

            @Override
            public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
                switch (newState) {
                    case None:
                    case PullDownToRefresh:
                        mEventEmitter.receiveEvent(getTargetId(),Events.PULL_DOWN_TO_REFRESH.toString(),null);
                        break;
                    case Refreshing:

                        break;
                    case ReleaseToRefresh:
                        mEventEmitter.receiveEvent(getTargetId(),Events.RELEASE_TO_REFRESH.toString(),null);
                        break;
                }

            }
        });
    }
    private int getTargetId(){
        return smartRefreshLayout.getId();
    }
}
