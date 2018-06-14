package com.lmy.smartrefreshlayout;

import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import java.util.HashMap;

/**
 * Created by macbook on 2018/6/13.
 */

public class SpinnerStyleConstants {
    public static final String TRANSLATE = "translate";
    public static final String FIX_BEHIND = "fixBehind";
    public static final String SCALE = "scale";
    public static final String FIX_FRONT = "fixFront";
    public static final String MATCH_LAYOUT = "matchLayout";
    public static final HashMap<String,SpinnerStyle> SpinnerStyleMap = new HashMap<String,SpinnerStyle>(){{
        put(TRANSLATE,SpinnerStyle.Translate);
        put(FIX_BEHIND,SpinnerStyle.FixedBehind);
        put(SCALE,SpinnerStyle.Scale);
        put(MATCH_LAYOUT,SpinnerStyle.MatchLayout);
        put(FIX_FRONT,SpinnerStyle.FixedFront);
    }};
}
