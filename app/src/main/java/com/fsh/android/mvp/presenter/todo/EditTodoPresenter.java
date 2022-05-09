package com.fsh.android.mvp.presenter.todo;

import com.fsh.android.mvp.base.presenter.BasePresenter;
import com.fsh.android.mvp.bean.todo.Todo;
import com.fsh.android.mvp.contract.edittodo.Contract;
import com.fsh.android.mvp.model.EditTodoModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/02/23
 * Time: 12:43
 */
public class EditTodoPresenter extends BasePresenter<Contract.IEditTodoView> implements Contract.IEditTodoPresenter {

    Contract.IEditTodoModel iEditTodoModel;

    public EditTodoPresenter() {
        iEditTodoModel = new EditTodoModel();
    }
    @Override
    public void addTodo(String title, String content, String date, int type, int priority) {
        if (isViewAttached()) {
            getView().onLoading();
        }
        iEditTodoModel.addTodo(title, content, date, type, priority)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Todo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Todo todo) {
                        if (isViewAttached()) {
                            getView().onAddTodo(todo);
                            getView().onLoadSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().onLoadFailed();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateTodo(int id, String title, String content, String date, int type, int priority) {
        if (isViewAttached()) {
            getView().onLoading();
        }
        iEditTodoModel.updateTodo(id,title, content, date, type, priority)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Todo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Todo todo) {
                        if (isViewAttached()) {
                            getView().onUpdateTodo(todo);
                            getView().onLoadSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().onLoadFailed();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
