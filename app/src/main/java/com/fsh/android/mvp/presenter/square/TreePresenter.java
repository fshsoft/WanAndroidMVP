package com.fsh.android.mvp.presenter.square;

import com.fsh.android.mvp.base.presenter.BasePresenter;
import com.fsh.android.mvp.bean.square.TreeData;
import com.fsh.android.mvp.contract.square.Contract;
import com.fsh.android.mvp.model.TreeModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/01/08
 * Time: 11:13
 */
public class TreePresenter extends BasePresenter<Contract.ITreeView> implements Contract.ITreePresenter {

    Contract.ITreeModel iTreeModel;

    public TreePresenter() {
        iTreeModel = new TreeModel();
    }

    @Override
    public void loadTree() {
        iTreeModel.loadTreeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TreeData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(TreeData treeData) {
                        if (isViewAttached()) {
                            getView().loadTreeData(treeData);
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
