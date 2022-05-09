package com.fsh.android.mvp.model;

import com.fsh.android.mvp.base.model.BaseModel;
import com.fsh.android.mvp.base.utils.Constant;

import com.fsh.android.mvp.bean.collect.Collect;
import com.fsh.android.mvp.contract.collect.Contract;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/01/01
 * Time: 22:40
 */
public class CollectModel extends BaseModel implements Contract.ICollectModel {

    public CollectModel() {
        setCookies(false);
    }

    @Override
    public Observable<List<com.fsh.android.mvp.bean.db.Collect>> loadCollectData(int pageNum) {
        Observable<List<com.fsh.android.mvp.bean.db.Collect>> loadFromNet = loadCollectArticleFromNet(pageNum);
        return loadFromNet;

    }

    private Observable<List<com.fsh.android.mvp.bean.db.Collect>> loadCollectArticleFromNet(int pageNum) {
        return mApiServer.loadCollect(pageNum).filter(collectData -> collectData.getErrorCode() == Constant.SUCCESS)
                .map(collectData -> {
                    List<com.fsh.android.mvp.bean.db.Collect> list = new ArrayList<>();
                    collectData.getData().getDatas().stream().forEach(d -> {
                        com.fsh.android.mvp.bean.db.Collect collect = new com.fsh.android.mvp.bean.db.Collect();
                        collect.articleId = d.getId();
                        collect.originId = d.getOriginId();
                        collect.author = d.getAuthor();
                        collect.title = d.getTitle();
                        collect.chapterName = d.getChapterName();
                        collect.time = d.getPublishTime();
                        collect.link = d.getLink();
                        collect.niceDate = d.getNiceDate();
                        list.add(collect);
                    });
                    return list;
                });
    }

    @Override
    public Observable<List<com.fsh.android.mvp.bean.db.Collect>> refreshCollectData(int pageNum) {
        return loadCollectArticleFromNet(pageNum);
    }

    @Override
    public Observable<com.fsh.android.mvp.bean.db.Collect> addCollect(String title, String author, String link) {
        return mApiServer.addCollect(title, author, link)
                .filter(addCollect -> addCollect.getErrorCode() == Constant.SUCCESS)
                .map(addCollect -> {
                            com.fsh.android.mvp.bean.db.Collect collect = new com.fsh.android.mvp.bean.db.Collect();
                            collect.articleId = addCollect.getData().getId();
                            collect.author = addCollect.getData().getAuthor();
                            collect.link = addCollect.getData().getLink();
                            collect.time = addCollect.getData().getPublishTime();
                            collect.title = addCollect.getData().getTitle();
                            collect.chapterName = "站外";
                            return collect;
                        }
                );
    }

    @Override
    public Observable<Collect> unCollect(int articleId, int originId) {
        return mApiServer.unCollect(articleId, originId);
    }
}
