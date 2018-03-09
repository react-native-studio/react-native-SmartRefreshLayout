package com.lmy.header;

import android.graphics.Color;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.scwang.smartrefresh.header.StoreHouseHeader;

/**
 * Created by painter.g on 2018/3/7.
 */

public class StoreHouseHeaderManager extends SimpleViewManager<StoreHouseHeader> {

    private String textStr="STOREHOUSE";
    @Override
    public String getName() {
        return "RCTStoreHouseHeader";
    }

    @Override
    protected StoreHouseHeader createViewInstance(ThemedReactContext reactContext) {
        return new StoreHouseHeader(reactContext);
    }

    /**
     * 设置字体颜色
     * @param view
     * @param textColor
     */
    @ReactProp(name = "textColor")
    public void setTextColor(StoreHouseHeader view,String textColor){
        view.setTextColor(Color.parseColor(textColor));
    }

    /**
     * 设置文字
     * @param view
     * @param text
     */
    @ReactProp(name="text")
    public void setText(StoreHouseHeader view,String text){
        textStr=text;
        view.initWithString(text);
    }

    /**
     * 设置字体尺寸
     * TODO:似乎还没有作用
     * @param view
     * @param fontSize
     */
    @ReactProp(name="fontSize",defaultInt = 25)
    public void setFontSize(StoreHouseHeader view,int fontSize){
        view.initWithString(textStr,fontSize);
    }

    /**
     * 设置线宽
     * @param view
     * @param lineWidth
     */
    @ReactProp(name = "lineWidth")
    public void setLineWidth(StoreHouseHeader view,int lineWidth){
        view.setLineWidth(lineWidth);
    }

    /**
     * 设置dropHeight
     * TODO:似乎还没有作用
     * @param view
     * @param dropHeight
     */
    @ReactProp(name="dropHeight")
    public void setDropHeight(StoreHouseHeader view,int dropHeight){
        view.setDropHeight(dropHeight);
    }
}
