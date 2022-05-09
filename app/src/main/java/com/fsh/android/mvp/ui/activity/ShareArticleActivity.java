package com.fsh.android.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.fsh.android.mvp.R;
import com.fsh.android.mvp.base.activity.BaseActivity;
import com.fsh.android.mvp.base.utils.LoginUtils;
import com.fsh.android.mvp.base.utils.Utils;
import com.fsh.android.mvp.bean.base.Event;
import com.fsh.android.mvp.bean.db.Article;
import com.fsh.android.mvp.contract.squaresharearticle.Contract;
import com.fsh.android.mvp.presenter.share.SquareSharePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class ShareArticleActivity extends BaseActivity<Contract.IShareView, SquareSharePresenter> implements Contract.IShareView {

    @BindView(R.id.share_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.share_title)
    EditText mShareTitle;

    @BindView(R.id.share_url)
    EditText mShareUrl;

    @BindView(R.id.share_username)
    TextView mShareUserName;

    @BindView(R.id.share_submit)
    Button mShareSubmit;

    private Context mContext;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_share_article;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mContext = getApplicationContext();
        mShareUserName.setText(LoginUtils.getLoginUser());
        initColor();
        initToolbar();
    }

    private void initColor() {
        getWindow().setStatusBarColor(Utils.getColor(mContext));
        mToolbar.setBackgroundColor(Utils.getColor(mContext));
        mToolbar.setTitleTextColor(getColor(R.color.white));
        mShareSubmit.getBackground().setColorFilter(
                Utils.getColor(mContext), PorterDuff.Mode.SRC_ATOP);
    }

    @OnClick(R.id.share_submit)
    public void onSubmitClick() {
        if (mShareTitle.getText().toString().isEmpty()) {
            ToastUtils.showShort("请填写文章标题");
        } else if (mShareUrl.getText().toString().isEmpty()) {
            ToastUtils.showShort("请填写文章链接");
        } else {
            mPresenter.addArticle(mShareTitle.getText().toString()
                    , mShareUrl.getText().toString());
            finish();
        }

    }

    @Override
    protected SquareSharePresenter createPresenter() {
        return new SquareSharePresenter();
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.share_article);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAddArticle(Article addArticle) {
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadFailed() {
        Event e = new Event();
        e.target = Event.TARGET_MAIN;
        e.type = Event.TYPE_STOP_ANIMATION;
        EventBus.getDefault().post(e);
        ToastUtils.showShort("添加失败");
    }

    @Override
    public void onLoadSuccess() {
        ToastUtils.showShort("添加失败");
    }
}
