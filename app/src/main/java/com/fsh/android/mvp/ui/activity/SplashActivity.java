package com.fsh.android.mvp.ui.activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.fsh.android.mvp.R;
import com.fsh.android.mvp.base.utils.Utils;
import com.fsh.android.mvp.bean.base.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 启动动画，欢迎页面
 */
public class SplashActivity extends AppCompatActivity {

    private LottieAnimationView mLottieAnimationView;

    private ViewGroup mSplashContainer;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        // 实现透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 实现透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //以上两个状态，我注释掉了，没有什么区别

        getWindow().setStatusBarColor(Utils.getColor(mContext));
        setContentView(R.layout.activity_splash);
        //overridePendingTransition:实现两个Activity切换时的动画效果
        //实现淡入浅出的效果
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        mSplashContainer = findViewById(R.id.splash_container);
        //注册监听EventBus
        EventBus.getDefault().register(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化进场动画
     */
    private void initView() {
        mSplashContainer.setBackgroundColor(Utils.getColor(mContext));
        //mLottieAnimationView 动画图标从上到下滑动
        mLottieAnimationView = findViewById(R.id.splash_animation);
        mLottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                //overridePendingTransition:实现两个Activity切换时的动画效果
                // 由左向右滑入的效果
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event) {
        if (event.target == Event.TARGET_SPLASH) {
            if (event.type == Event.TYPE_REFRESH_COLOR) {
                mSplashContainer.setBackgroundColor(Utils.getColor(mContext));
            }
        }
    }
}
