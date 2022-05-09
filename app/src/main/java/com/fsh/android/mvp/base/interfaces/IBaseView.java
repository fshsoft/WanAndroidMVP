package com.fsh.android.mvp.base.interfaces;

/**
 * Description: 用来指示错误和完成的接口
 */
public interface IBaseView {

    /**
     * 加载中
     */
    void onLoading();

    /**
     * 加载错误回调
     */
    void onLoadFailed();

    /**
     * 加载完成
     */
    void onLoadSuccess();
}
