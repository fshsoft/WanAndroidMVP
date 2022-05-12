package com.fsh.android.mvp.base.callback;


import com.kingja.loadsir.callback.Callback;
import com.fsh.android.mvp.R;

/**
 * Description: 网络错误提示界面
 */
public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.network_error;
    }
}
