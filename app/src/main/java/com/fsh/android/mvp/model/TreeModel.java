package com.fsh.android.mvp.model;

import com.fsh.android.mvp.base.model.BaseModel;
import com.fsh.android.mvp.bean.square.TreeData;
import com.fsh.android.mvp.contract.square.Contract;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/01/08
 * Time: 11:11
 */
public class TreeModel extends BaseModel implements Contract.ITreeModel {
    public TreeModel() {
        setCookies(false);
    }
    @Override
    public Observable<TreeData> loadTreeData() {
        return mApiServer.loadTreeData();
    }
}
