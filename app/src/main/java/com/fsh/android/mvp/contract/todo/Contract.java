package com.fsh.android.mvp.contract.todo;

import com.fsh.android.mvp.base.interfaces.IBaseView;
import com.fsh.android.mvp.bean.todo.DeleteTodo;
import com.fsh.android.mvp.bean.todo.FinishTodo;
import com.fsh.android.mvp.bean.todo.Todo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created with Android Studio.
 * Description:
 *
 * @author: Wangjianxian
 * @date: 2020/02/22
 * Time: 21:25
 */
public class Contract {
    public interface ITodoModel {
        /**
         * 加载Todo列表
         *
         * @param pageNum
         * @return
         */
        Observable<List<Todo>> loadTodo(int pageNum);

        /**
         * 刷新Todo
         *
         * @param pageNum
         * @return
         */
        Observable<List<Todo>> refreshTodo(int pageNum);

        /**
         * 删除一个Todo
         *
         * @param id
         * @return
         */
        Observable<DeleteTodo> deleteTodo(int id);


        /**
         * 完成一个Todo
         *
         * @param id
         * @return
         */
        Observable<FinishTodo> finishTodo(int id, int status);

    }

    public interface ITodoView extends IBaseView {
        void onLoadTodo(List<Todo> todoList);

        void onRefreshTodo(List<Todo> todoList);

        void onDeleteTodo(DeleteTodo todo, int id);

        void onFinishTodo(FinishTodo todo, int id);
    }

    public interface ITodoPresenter {
        void loadTodo(int pageNum);

        void refreshTodo(int pageNum);

        void deleteTodo(int id);

        void finishTodo(int id, int status);
    }
}
