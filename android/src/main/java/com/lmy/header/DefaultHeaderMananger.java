package com.lmy.header;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

/**
 * Created by painter.g on 2018/3/12.
 */

public class DefaultHeaderMananger extends SimpleViewManager<DefaultHeader> {
    @Override
    public String getName() {
        return "RCTDefaultHeader";
    }

    @Override
    protected DefaultHeader createViewInstance(ThemedReactContext reactContext) {
        return new DefaultHeader(reactContext);
    }
}
