package com.fsh.android.mvp.model;

import com.fsh.android.mvp.base.model.BaseModel;
import com.fsh.android.mvp.bean.me.RegisterData;
import com.fsh.android.mvp.contract.register.Contract;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/01/26
 * Time: 15:18
 */
public class RegisterModel extends BaseModel implements Contract.IRegisterModel {
    @Override
    public Observable<RegisterData> register(String userName, String passWord) {
        return mApiServer.register(userName, passWord, passWord);
    }
}
