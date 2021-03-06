package com.fsh.android.mvp.model;

import com.fsh.android.mvp.base.utils.ApiServer;
import com.fsh.android.mvp.base.utils.Constant;
import com.fsh.android.mvp.bean.searchwords.SearchWordData;
import com.fsh.android.mvp.contract.searchwords.Contract;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2019/12/29
 * Time: 18:37
 */
public class SearchWordModel implements Contract.ISearchModel {
    @Override
    public Observable<SearchWordData> loadSearchWordData() {
        return getApiServer().loadSearchWordData();
    }

    /**
     * 获取请求对象
     * @return 当前的请求对象
     */
    private ApiServer getApiServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        return apiServer;
    }

}
