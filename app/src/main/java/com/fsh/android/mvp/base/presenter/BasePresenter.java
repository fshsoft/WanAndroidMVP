package com.fsh.android.mvp.base.presenter;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Description: BasePresenter作用绑定并解析View
 */
public class BasePresenter<V> {
    WeakReference<V> mWeakRef;

    /**
     * RXJava,对订阅请求统一管理
     */
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * 创建弱引用View
     * @param view
     */
    public void attachView(V view) {
        mWeakRef = new WeakReference<V>(view);
    }

    /**
     * 将View取出
     * @return
     */
    protected V getView() {
        return mWeakRef == null ? null : mWeakRef.get();
    }

    /**
     * 判断是否使用弱引用创建View
     * @return
     */
    protected boolean isViewAttached() {
        return mWeakRef != null && mWeakRef.get() != null;
    }

    /**
     * 释放View 并取消订阅
     */
    public void detachView() {
        if (mWeakRef != null) {
            mWeakRef.clear();
            mWeakRef = null;
        }
        mCompositeDisposable.clear();
    }
}
