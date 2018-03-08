package com.lmy.smartrefreshlayout;

import android.support.annotation.NonNull;
import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

    static {
        //设置全局的Header构建器
       /* ReactSmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ReactClassicsHeader(context).setEnableLastTime(false);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        ReactSmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ReactClassicsFooter(context);
            }
        });*/
    }

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
     * 是否启用下拉刷新功能
     * @param view
     * @param enableRefresh
     */
    @ReactProp(name="enableRefresh",defaultBoolean = true)
    public void setEnableRefresh(ReactSmartRefreshLayout view,boolean enableRefresh){
        view.setEnableRefresh(enableRefresh);
    }
    @Override
    public void receiveCommand(ReactSmartRefreshLayout root, int commandId, @Nullable ReadableArray args) {
        switch (commandId){
            case COMMAND_FINISH_REFRESH_ID:
                int delayed=args.getInt(0);
                boolean success=args.getBoolean(1);
                if(delayed>0){
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
                RefreshHeader header=(RefreshHeader)child;
                parent.setRefreshHeader(header);
                break;
            case 1:
                parent.setRefreshContent(child);
                break;
            case 2:
                RefreshFooter footer=(RefreshFooter)child;
                parent.setRefreshFooter(footer);
                break;
            default:break;

        }
    }

    @Override
    public void addViews(ReactSmartRefreshLayout parent, List<View> views) {
        super.addViews(parent, views);
    }

    @Override
    protected void addEventEmitters(ThemedReactContext reactContext, ReactSmartRefreshLayout view) {
        view.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新触发
                mEventEmitter.receiveEvent(getTargetId(),Events.REFRESH.toString(),null);
            }
        });
        view.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多触发
                mEventEmitter.receiveEvent(getTargetId(),Events.LOADMORE.toString(),null);

            }
        });
    }
    private int getTargetId(){
        return smartRefreshLayout.getId();
    }
}
