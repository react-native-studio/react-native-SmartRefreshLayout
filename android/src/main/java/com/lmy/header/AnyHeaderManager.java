package com.lmy.header;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

/**
 * Created by painter.g on 2018/3/12.
 */

public class AnyHeaderManager extends ViewGroupManager<AnyHeader> {
    @Override
    public String getName() {
        return "RCTAnyHeader";
    }

    @Override
    protected AnyHeader createViewInstance(ThemedReactContext reactContext) {
        return new AnyHeader(reactContext);
    }
}
