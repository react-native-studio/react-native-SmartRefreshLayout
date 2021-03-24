package com.lmy.smartrefreshlayout;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.lmy.header.AnyHeaderManager;
import com.lmy.header.ClassicsHeaderManager;
import com.lmy.header.DefaultHeaderManager;
import com.lmy.header.MaterialHeaderManager;
import com.lmy.header.StoreHouseHeaderManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by painter.g on 2018/3/6.
 */

public class SmartRefreshLayoutPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Arrays.<NativeModule>asList(
                new RCTSpinnerStyleModule(reactContext)
        );
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new SmartRefreshLayoutManager(),
                new ClassicsHeaderManager(),
                new StoreHouseHeaderManager(),
                new MaterialHeaderManager(),
                new AnyHeaderManager(),
                new DefaultHeaderManager()
            );
    }
}
