package com.fsh.android.mvp.presenter.project;

import com.fsh.android.mvp.base.presenter.BasePresenter;
import com.fsh.android.mvp.bean.db.ProjectClassify;
import com.fsh.android.mvp.contract.project.Contract;
import com.fsh.android.mvp.model.ProjectModel;

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
 * @date: 2019/12/27
 * Time: 14:57
 */
public class ProjectPresenter extends BasePresenter<Contract.IProjectView> implements Contract.IProjectPresenter {
    Contract.IProjectModel iProjectModel;

    public ProjectPresenter() {
        iProjectModel = new ProjectModel();
    }

    @Override
    public void loadProjectClassify() {
        iProjectModel.loadProjectClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProjectClassify>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<ProjectClassify> projectClassify) {
                        if (isViewAttached()) {
                            getView().onLoadProjectClassify(projectClassify);
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
    public void refreshProjectClassify() {
        iProjectModel.refreshProjectClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProjectClassify>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<ProjectClassify> projectClassify) {
                        if (isViewAttached()) {
                            getView().onRefreshProjectClassify(projectClassify);
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
