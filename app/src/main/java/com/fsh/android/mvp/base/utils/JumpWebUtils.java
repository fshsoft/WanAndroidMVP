package com.fsh.android.mvp.base.utils;

import android.content.Context;
import android.content.Intent;

import com.fsh.android.mvp.ui.activity.WebViewActivity;

/**
 * Description: Web View 跳转器
 */
public class JumpWebUtils {

    public static void startWebView(Context context, String title, String url){
        Intent intent = new Intent();
        intent.setClass(context, WebViewActivity.class);
        intent.putExtra(Constant.KEY_TITLE, title);
        intent.putExtra(Constant.KEY_URL, url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startWebView(Context context, String title, String url, int articleId, boolean isCollect){
        Intent intent = new Intent();
        intent.setClass(context, WebViewActivity.class);
        intent.putExtra(Constant.KEY_TITLE, title);
        intent.putExtra(Constant.KEY_URL, url);
        intent.putExtra(Constant.KEY_ARTICLEID, articleId);
        intent.putExtra(Constant.KEY_COLLECT, isCollect);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
