package com.fsh.android.mvp.contract.register;

import com.fsh.android.mvp.base.interfaces.IBaseView;
import com.fsh.android.mvp.bean.me.RegisterData;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * Description: 注册契约类
 *
 * @author: Wangjianxian
 * @date: 2020/01/26
 * Time: 15:15
 */
public class Contract {

    public interface IRegisterModel {
        Observable<RegisterData> register(String userName, String passWord);
    }

    public interface IRegisterView extends IBaseView {
        void onRegister(RegisterData registerData);
    }

    public interface IRegisterPresenter {
        void register(String userName, String passWord);
    }
}
