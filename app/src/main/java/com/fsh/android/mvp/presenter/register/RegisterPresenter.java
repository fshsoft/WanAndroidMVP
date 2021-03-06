package com.fsh.android.mvp.presenter.register;

import com.fsh.android.mvp.base.presenter.BasePresenter;
import com.fsh.android.mvp.bean.me.RegisterData;
import com.fsh.android.mvp.contract.register.Contract;
import com.fsh.android.mvp.model.RegisterModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/01/26
 * Time: 15:20
 */
public class RegisterPresenter extends BasePresenter<Contract.IRegisterView> implements Contract.IRegisterPresenter {
    Contract.IRegisterModel iRegisterModel;

    public RegisterPresenter() {
        iRegisterModel = new RegisterModel();
    }
    @Override
    public void register(String userName, String passWord) {
        iRegisterModel.register(userName, passWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(RegisterData registerData) {
                        if (isViewAttached()) {
                            getView().onRegister(registerData);
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
