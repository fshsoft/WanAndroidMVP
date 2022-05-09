package com.fsh.android.mvp.base.model;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.fsh.android.mvp.base.utils.ApiServer;
import com.fsh.android.mvp.base.utils.Constant;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 基类Model,处理数据，网络请求
 */
public class BaseModel {

    protected Retrofit mRetrofit;

    protected ApiServer mApiServer;

    public BaseModel()  {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiServer = mRetrofit.create(ApiServer.class);
    }

    /**
     * 设置是否保存Cookies
     * @param isSaveCookies
     */
    public void setCookies(boolean isSaveCookies) {
        OkHttpClient.Builder builder  = new OkHttpClient.Builder();
        builder.addInterceptor(new ReadCookieInterceptor());
        builder.addInterceptor(new WriteCookieInterceptor(isSaveCookies));
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApiServer = mRetrofit.create(ApiServer.class);
    }

    public class ReadCookieInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            Request.Builder builder = chain.request().newBuilder();
            long expire = SPUtils.getInstance("cookie").getLong("cookie_expire", 0);
            if (expire > System.currentTimeMillis()) {
                String cookies = SPUtils.getInstance("cookie").getString("user");
                if (!TextUtils.isEmpty(cookies)) {
                    for (String cookie : cookies.split(";")) {
                        builder.addHeader("Cookie", cookie);
                    }
                    return chain.proceed(builder.build());
                }
            }
            return response;
        }
    }

    public class WriteCookieInterceptor implements Interceptor {
        private boolean mSaveCookie;
        public WriteCookieInterceptor(boolean saveCookie) {
            this.mSaveCookie = saveCookie;
        }
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            if (mSaveCookie) {
                List<String> headers = response.headers("Set-Cookie");
                if (!headers.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    headers.stream().forEach(h -> {
                        sb.append(h).append(";");
                    });
                    SPUtils.getInstance("cookie").put("user", sb.toString());
                    SPUtils.getInstance("cookie").put("cookie_expire", System.currentTimeMillis() + 30 * 24 * 3600 * 1000L);
                }
            }
            return response;
        }
    }
}
