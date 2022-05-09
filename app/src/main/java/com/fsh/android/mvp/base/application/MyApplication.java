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
        LitePal.initialize(this);
        Utils.init(this);
        initMode();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .commit();
    }

    private void initMode() {
        boolean isNightMode = SPUtils.getInstance(Constant.CONFIG_SETTINGS).getBoolean
                (Constant.KEY_NIGHT_MODE, false);
        AppCompatDelegate.setDefaultNightMode(isNightMode ? AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO);
    }
}
