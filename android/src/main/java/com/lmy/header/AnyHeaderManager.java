package com.lmy.header;

import android.graphics.Color;
import android.support.annotation.ColorInt;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.lmy.smartrefreshlayout.SpinnerStyleConstants;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by painter.g on 2018/3/12.
 */

public class AnyHeaderManager extends ViewGroupManager<AnyHeader> implements AnyHeaderEventListener {

    private static final int COMMAND_SCROLL_TO = 1;
    public static final String ON_HEADER_MOVE = "onHeaderMove";
    public static final String ON_FOOTER_MOVE = "onFooterMove";
    public static final String ON_HEADER_MOVE_FINISHED = "onHeaderMoveFinished";
    public static final String ON_FOOTER_MOVE_FINISHED = "onFooterMoveFinished";
    private RCTEventEmitter mJsModule;
    private AnyHeader mAnyHeader;

    @Override
    public String getName() {
        return "RCTAnyHeader";
    }

    @Override
    protected AnyHeader createViewInstance(ThemedReactContext reactContext) {
        mAnyHeader = new AnyHeader(reactContext);
        mAnyHeader.setHeaderEventListener(this);
        mJsModule = reactContext.getJSModule(RCTEventEmitter.class);
        return mAnyHeader;
    }

    private int getTargetId(){
        return mAnyHeader.getId();
    }

    /**
     * 设置主调色
     * @param view
     * @param primaryColor
     */
    @ReactProp(name = "primaryColor")
    public void setPrimaryColor(AnyHeader view, String primaryColor){
        if(primaryColor!=null && !"".equals(primaryColor)){
            view.setPrimaryColor(Color.parseColor(primaryColor));
        }
    }

    /**
     * 设置spinnerStyle
     * @param view
     * @param spinnerStyle
     */
    @ReactProp(name = "spinnerStyle")
    public void setSpinnerStyle(AnyHeader view,String spinnerStyle){
        view.setSpinnerStyle(SpinnerStyleConstants.SpinnerStyleMap.get(spinnerStyle));
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "scrollTo", COMMAND_SCROLL_TO
        );
    }

    @Override
    public void receiveCommand(AnyHeader view, int commandId, @Nullable ReadableArray args) {
        super.receiveCommand(view, commandId, args);
        switch (commandId){
            case COMMAND_SCROLL_TO:
                if (args != null) {
                    ReadableMap params = args.getMap(0);
                    view.scrollTo(params);
                }
                break;
            default:break;
        }
    }

    @Override
    public void onHeaderMove(int offset) {
        WritableMap event = Arguments.createMap();
        event.putInt("y", offset);
        mJsModule.receiveEvent(getTargetId(), ON_HEADER_MOVE, event);
    }

    @Override
    public void onFooterMove(int offset) {
        WritableMap event = Arguments.createMap();
        event.putInt("y", offset);
        mJsModule.receiveEvent(getTargetId(), ON_FOOTER_MOVE, event);
    }

    @Override
    public void onHeaderMoveFinished() {
        mJsModule.receiveEvent(getTargetId(), ON_HEADER_MOVE_FINISHED, null);
    }

    @Override
    public void onFooterMoveFinished() {
        mJsModule.receiveEvent(getTargetId(), ON_FOOTER_MOVE_FINISHED, null);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder builder = MapBuilder.builder();
        String registrationName = "registrationName";
        builder.put(ON_HEADER_MOVE, MapBuilder.of(registrationName, ON_HEADER_MOVE));
        builder.put(ON_FOOTER_MOVE, MapBuilder.of(registrationName, ON_FOOTER_MOVE));
        builder.put(ON_HEADER_MOVE_FINISHED, MapBuilder.of(registrationName, ON_HEADER_MOVE_FINISHED));
        builder.put(ON_FOOTER_MOVE_FINISHED, MapBuilder.of(registrationName, ON_FOOTER_MOVE_FINISHED));
        return builder.build();
    }
}
