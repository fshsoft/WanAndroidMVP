package com.fsh.android.mvp.contract.me;

import com.fsh.android.mvp.base.interfaces.IBaseView;
import com.fsh.android.mvp.bean.me.IntegralData;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2019/12/31
 * Time: 14:15
 */
public class Contract {

    public interface IMeModel {

        /**
         * 加载积分数据
         * @return
         */
        Observable<IntegralData> loadIntegralData();

        /**
         * 刷新积分数据
         * @return
         */
        Observable<IntegralData> refreshIntegralData();

    }

    public interface IMeView extends IBaseView {

        void onLoadIntegralData(IntegralData integral);

        void onRefreshIntegralData(IntegralData integral);
    }

    public interface IMePresenter {
        void loadIntegralData();

        void refreshIntegralData();
    }
}
