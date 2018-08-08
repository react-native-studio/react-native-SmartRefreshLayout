package com.lmy.smartrefreshlayout;

/**
 * Created by lmy2534290808 on 2017/12/2.
 */

public enum Events {
    REFRESH("onSmartRefresh"),//刷新触发
    LOAD_MORE("onLoadMore"),//加载更多触发
    HEADER_PULLING("onHeaderPulling"),//header下拉触发
    HEADER_RELEASING("onHeaderReleasing"),//header刷新完成后触发
    PULL_DOWN_TO_REFRESH("onPullDownToRefresh"),//下拉开始刷新
    RELEASE_TO_REFRESH("onReleaseToRefresh"),//释放刷新
    HEADER_RELEASED("onHeaderReleased"),//释放时进行刷新
    FOOTER_MOVING("onFooterMoving");//footer移动时触发

    private final String mName;

    Events(final String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }
}
