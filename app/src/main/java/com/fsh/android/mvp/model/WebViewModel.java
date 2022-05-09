package com.fsh.android.mvp.model;

import com.fsh.android.mvp.base.model.BaseModel;
import com.fsh.android.mvp.bean.collect.Collect;
import com.fsh.android.mvp.contract.webview.Contract;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/02/01
 * Time: 16:15
 */
public class WebViewModel extends BaseModel implements Contract.IWebViewModel {
    public WebViewModel(){
        setCookies(false);
    }
    @Override
    public Observable<Collect> collect(int articleId) {
        return mApiServer.onCollect(articleId);
    }

    @Override
    public Observable<Collect> unCollect(int articleId) {
        return mApiServer.unCollect(articleId);
    }
}
