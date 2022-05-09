package com.fsh.android.mvp.model;

import com.fsh.android.mvp.base.model.BaseModel;
import com.fsh.android.mvp.bean.me.LogoutData;
import com.fsh.android.mvp.contract.setting.Contract;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/01/16
 * Time: 13:56
 */
public class SettingModel extends BaseModel implements Contract.ILogoutModel {
    public SettingModel() {
        setCookies(true);
    }

    @Override
    public Observable<LogoutData> logout() {
        return mApiServer.logout();
    }
}
