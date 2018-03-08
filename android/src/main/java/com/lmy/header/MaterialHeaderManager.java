package com.lmy.header;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.scwang.smartrefresh.header.MaterialHeader;

/**
 * Created by painter.g on 2018/3/8.
 */

public class MaterialHeaderManager extends ViewGroupManager<MaterialHeader> {
    @Override
    public String getName() {
        return "RCTMaterialHeader";
    }

    @Override
    protected MaterialHeader createViewInstance(ThemedReactContext reactContext) {
        return new MaterialHeader(reactContext);
    }
}
