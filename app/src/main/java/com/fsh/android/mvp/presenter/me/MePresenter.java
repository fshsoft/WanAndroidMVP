package com.fsh.android.mvp.presenter.me;

import com.fsh.android.mvp.base.presenter.BasePresenter;
import com.fsh.android.mvp.bean.me.IntegralData;
import com.fsh.android.mvp.contract.me.Contract;
import com.fsh.android.mvp.model.MeModel;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2019/12/31
 * Time: 14:19
 */
public class MePresenter extends BasePresenter<Contract.IMeView> implements Contract.IMePresenter {
    Contract.IMeModel iMeModel;
    public MePresenter() {
        iMeModel = new MeModel();
    }

    @Override
    public void loadIntegralData() {
        iMeModel.loadIntegralData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IntegralData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(IntegralData integrals) {
                        if (isViewAttached()) {
                            getView().onLoadIntegralData(integrals);
                            getView().onLoadSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (isViewAttached()) {
                            getView().onLoadFailed();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void refreshIntegralData() {
        iMeModel.refreshIntegralData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IntegralData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(IntegralData integrals) {
                        if (isViewAttached()) {
                            getView().onRefreshIntegralData(integrals);
                            getView().onLoadSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (isViewAttached()) {
                            getView().onLoadFailed();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
