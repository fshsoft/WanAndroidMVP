package com.fsh.android.mvp.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kingja.loadsir.core.LoadService;
import com.fsh.android.mvp.base.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: Base Fragment
 */
public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment{

    protected P mPresenter;

    protected Unbinder unbinder;

    protected abstract int getContentViewId();

    protected View mRootView;

    protected LoadService mLoadService;


    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 创建Presenter
     *
     * @return p
     */
    protected abstract P createPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(getContentViewId(), container, false);
        }
        mPresenter = createPresenter();
        unbinder = ButterKnife.bind(this, mRootView);
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        init();
        return mRootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
