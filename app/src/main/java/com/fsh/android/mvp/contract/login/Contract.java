package com.fsh.android.mvp.contract.login;

import com.fsh.android.mvp.base.interfaces.IBaseView;
import com.fsh.android.mvp.bean.me.LoginData;

import io.reactivex.Observable;

/**
 * Description: Contract：契约合同
 */
public class Contract {
    public interface ILoginModel {
        /**
         * 登录
         * @param userName
         * @param passWord
         * @return
         */
        Observable<LoginData> login(String userName, String passWord);

    }

    public interface ILoginView extends IBaseView {
        void onLogin (LoginData loginData);

    }

    public interface ILoginPresenter {
        void login (String userName, String passWord);
    }
}
