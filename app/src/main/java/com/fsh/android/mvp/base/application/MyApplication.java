package com.fsh.android.mvp.base.application;

import androidx.appcompat.app.AppCompatDelegate;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.kingja.loadsir.core.LoadSir;
import com.fsh.android.mvp.base.callback.ErrorCallback;
import com.fsh.android.mvp.base.utils.Constant;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * Description: Base Application
 */
public class MyApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //数据库的初始化
        LitePal.initialize(this);
        //工具库的初始化
        Utils.init(this);

        initMode();

        //加载反馈管理框架初始化
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .commit();
    }

    private void initMode() {
        //利用工具库的SPUtils获取值
        boolean isNightMode = SPUtils.getInstance(Constant.CONFIG_SETTINGS).getBoolean
                (Constant.KEY_NIGHT_MODE, false);
        //我的->系统设置->夜间模式的开启
        AppCompatDelegate.setDefaultNightMode(isNightMode ? AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO);
    }
}
