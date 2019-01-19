package com.lmy.header;

public interface AnyHeaderEventListener {
    void onHeaderMove(int offset);
    void onFooterMove(int offset);
    void onHeaderMoveFinished();
    void onFooterMoveFinished();
}
