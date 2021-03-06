package com.fsh.android.mvp.presenter.collect;

import com.fsh.android.mvp.base.presenter.BasePresenter;
import com.fsh.android.mvp.bean.db.Collect;
import com.fsh.android.mvp.contract.collect.Contract;
import com.fsh.android.mvp.model.CollectModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/01/01
 * Time: 22:39
 */
public class CollectPresenter extends BasePresenter<Contract.ICollectView> implements Contract.ICollectPresenter {
    Contract.ICollectModel iCollectModel;
    public CollectPresenter() {
        iCollectModel = new CollectModel();
    }
    @Override
    public void loadCollectData(int pageNum) {
        if (isViewAttached() && pageNum == 0) {
            getView().onLoading();
        }
        iCollectModel.loadCollectData(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Collect>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<Collect> collectBean) {
                        if (isViewAttached()) {
                            getView().onLoadCollectData(collectBean);
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
    public void refreshCollectData(int pageNum) {
        iCollectModel.refreshCollectData(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Collect>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<Collect> collectBean) {
                        if (isViewAttached()) {
                            getView().onRefreshCollectData(collectBean);
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
    public void addCollect(String title, String author, String link) {
        iCollectModel.addCollect(title, author, link)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Collect>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Collect addCollect) {
                        if (isViewAttached()) {
                            getView().onAddCollect(addCollect);
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
    public void unCollect(int articleId, int originId) {
        iCollectModel.unCollect(articleId, originId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<com.fsh.android.mvp.bean.collect.Collect>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(com.fsh.android.mvp.bean.collect.Collect collect) {
                        if (isViewAttached()) {
                            getView().onUnCollect(collect, articleId);
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
