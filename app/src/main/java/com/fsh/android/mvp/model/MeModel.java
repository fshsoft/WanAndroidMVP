package com.fsh.android.mvp.model;

import com.fsh.android.mvp.base.model.BaseModel;
import com.fsh.android.mvp.bean.me.IntegralData;
import com.fsh.android.mvp.contract.me.Contract;

import io.reactivex.Observable;


/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2019/12/31
 * Time: 14:19
 */
public class MeModel extends BaseModel implements Contract.IMeModel {
    public MeModel() {
        setCookies(false);
    }

    @Override
    public Observable<IntegralData> loadIntegralData() {
        return mApiServer.loadIntegralData();
    }

    @Override
    public Observable<IntegralData> refreshIntegralData() {
        return mApiServer.loadIntegralData();
    }


}
