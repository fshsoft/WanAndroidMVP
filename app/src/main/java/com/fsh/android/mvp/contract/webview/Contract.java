package com.fsh.android.mvp.contract.webview;

import com.fsh.android.mvp.base.interfaces.IBaseView;
import com.fsh.android.mvp.bean.collect.Collect;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/02/01
 * Time: 16:11
 */
public class Contract {

    public interface IWebViewModel {
        Observable<Collect> collect(int articleId);

        Observable<Collect> unCollect(int articleId);
    }

    public interface IWebView extends IBaseView {
        void onCollect(Collect collect);

        void onUnCollect(Collect collect);
    }

    public interface IWebViewPresenter {
        void collect(int articleId);

        void unCollect(int articleId);
    }
}
