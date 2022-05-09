package com.fsh.android.mvp.base.utils;

import com.fsh.android.mvp.bean.collect.AddCollect;
import com.fsh.android.mvp.bean.collect.CollectData;
import com.fsh.android.mvp.bean.home.ArticleBean;
import com.fsh.android.mvp.bean.home.Banner;
import com.fsh.android.mvp.bean.collect.Collect;
import com.fsh.android.mvp.bean.home.TopArticleBean;
import com.fsh.android.mvp.bean.me.IntegralData;
import com.fsh.android.mvp.bean.me.LoginData;
import com.fsh.android.mvp.bean.me.LogoutData;
import com.fsh.android.mvp.bean.me.RegisterData;
import com.fsh.android.mvp.bean.rank.RankData;
import com.fsh.android.mvp.bean.project.ProjectClassifyData;
import com.fsh.android.mvp.bean.project.ProjectListData;
import com.fsh.android.mvp.bean.searchresult.SearchResultData;
import com.fsh.android.mvp.bean.searchwords.SearchWordData;
import com.fsh.android.mvp.bean.share.DeleteShare;
import com.fsh.android.mvp.bean.share.ShareData;
import com.fsh.android.mvp.bean.square.NavigationData;
import com.fsh.android.mvp.bean.square.SquareData;
import com.fsh.android.mvp.bean.square.TreeData;
import com.fsh.android.mvp.bean.square.TreeListArticle;
import com.fsh.android.mvp.bean.todo.DeleteTodo;
import com.fsh.android.mvp.bean.todo.FinishTodo;
import com.fsh.android.mvp.bean.todo.TodoData;
import com.fsh.android.mvp.bean.todo.UpdateTodo;
import com.fsh.android.mvp.bean.wechat.WeChatClassifyData;
import com.fsh.android.mvp.bean.wechat.WeChatListData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Description: 请求APi
 */
public interface ApiServer {
    /**
     * 获取首页Banner
     *
     * @return
     */
    @GET(Constant.BANNER_URL)
    Observable<Banner> loadBanner();

    /**
     * 获取首页文章
     *
     * @return
     */
    @GET(Constant.ARTICLE_URL)
    Observable<ArticleBean> loadArticle(@Path("pageNum") int number);

    /**
     * 获取置顶文章
     *
     * @return
     */
    @GET(Constant.TOP_ARTICLE_URL)
    Observable<TopArticleBean> loadTopArticle();

    /**
     * 获取项目种类
     *
     * @return
     */
    @GET(Constant.PROJECT_CATEGORY_URL)
    Observable<ProjectClassifyData> loadProjectClassify();

    /**
     * 获取项目列表
     *
     * @return
     */
    @GET(Constant.PROJECT_LIST_URL)
    Observable<ProjectListData> loadProjectList(@Path("pageNum") int number, @Query("cid") int cid);


    /**
     * 获取公众号Tab
     *
     * @return
     */
    @GET(Constant.WE_CHAT_TAB_URL)
    Observable<WeChatClassifyData> loadWeChatClassify();

    /**
     * 获取某页公众号列表
     *
     * @param id
     * @param pageNum
     * @return
     */
    @GET(Constant.WE_CHAT_LIST_URL)
    Observable<WeChatListData> loadWeChatList(@Path("id") int id, @Path("pageNum") int pageNum);

    /**
     * 获取广场数据
     *
     * @param pageNum
     * @return
     */
    @GET(Constant.HOME_SQUARE_URL)
    Observable<SquareData> loadHomeSquareData(@Path("pageNum") int pageNum);

    /**
     * 添加分享文章
     *
     * @param title
     * @param link
     * @return
     */
    @POST(Constant.SQUARE_SHARE_ARTICLE)
    Observable<ArticleBean> addArticle(@Query("title") String title, @Query("link") String link);

    /**
     * 获取导航数据
     *
     * @return
     */
    @GET(Constant.NAVI_URL)
    Observable<NavigationData> loadNavigationData();

    /**
     * 获取体系数据
     *
     * @return
     */
    @GET(Constant.TREE_URL)
    Observable<TreeData> loadTreeData();

    /**
     * 获取体系文章列表
     *
     * @param pageNum
     * @param cid
     * @return
     */
    @GET(Constant.TREE_ARTICLE_URL)
    Observable<TreeListArticle> loadTreeArticle(@Path("pageNum") int pageNum, @Query("cid") int cid);

    /**
     * 获取搜索热词
     *
     * @return
     */
    @GET(Constant.HOT_SEARCH_URL)
    Observable<SearchWordData> loadSearchWordData();

    /**
     * 搜索结果数据
     *
     * @param pageNum
     * @param keyWords
     * @return
     */
    @POST(Constant.SEARCH_URL)
    Observable<SearchResultData> loadSearchResultData(@Path("pageNum") int pageNum, @Query("k") String keyWords);


    /**
     * 登陆数据
     *
     * @param userName
     * @param passWord
     * @return
     */
    @POST(Constant.LOGIN_URL)
    Observable<LoginData> login(@Query("username") String userName, @Query("password") String passWord);

    /**
     * 注册数据
     *
     * @param userName
     * @param password
     * @param repassword
     * @return
     */
    @POST(Constant.REGISTER_URL)
    Observable<RegisterData> register(@Query("username") String userName, @Query("password") String password, @Query("repassword") String repassword);

    /**
     * 登出
     *
     * @return
     */
    @GET(Constant.LOGINOUT_URL)
    Observable<LogoutData> logout();

    /**
     * 点击收藏文章
     *
     * @param id
     * @return
     */
    @POST(Constant.COLLECT_URL)
    Observable<Collect> onCollect(@Path("id") int id);

    /**
     * 点击取消收藏文章
     *
     * @param id
     * @return
     */
    @POST(Constant.UNCOLLECT_URL)
    Observable<Collect> unCollect(@Path("id") int id);

    /**
     * 我的收藏页面取消收藏文章包括自己录入的内容
     *
     * @param id
     * @param originId
     * @return
     */
    @POST(Constant.UNCOLLECT_INCLUDE_ADD_URL)
    Observable<Collect> unCollect(@Path("id") int id, @Query("originId") int originId);

    /**
     * 获取收藏文章列表
     *
     * @param pageNum
     * @return
     */
    @GET(Constant.COLLECT_LIST_URL)
    Observable<CollectData> loadCollect(@Path("pageNum") int pageNum);

    /**
     * 添加站外收藏
     *
     * @param title
     * @param author
     * @param link
     * @return
     */
    @POST(Constant.ADD_COLLECT_URL)
    Observable<AddCollect> addCollect(@Query("title") String title, @Query("author") String author, @Query("link") String link);

    /**
     * 获取个人积分排行
     *
     * @return
     */
    @GET(Constant.INTEGRAL_URL)
    Observable<IntegralData> loadIntegralData();

    /**
     * 获取积分排行列表
     *
     * @return
     */
    @GET(Constant.RANK_URL)
    Observable<RankData> loadRankData(@Path("pageNum") int pageNum);

    /**
     * 获取分享的文章的列表
     *
     * @param pageNum
     * @return
     */
    @GET(Constant.SHARE_ARTICLE_URL)
    Observable<ShareData> loadShareArticle(@Path("pageNum") int pageNum);

    /**
     * 删除分享的文章
     *
     * @param id
     * @return
     */
    @POST(Constant.DELETE_SHARE_ARTICLE_URL)
    Observable<DeleteShare> deleteShareArticle(@Path("id") int id);

    /**
     * 获取Todo列表
     *
     * @param pageNum
     * @return
     */
    @GET(Constant.TODO_LIST_URL)
    Observable<TodoData> loadTodoData(@Path("pageNum") int pageNum);

    /**
     * 添加一个Todo
     *
     * @param title
     * @param content
     * @param date
     * @param type
     * @param priority
     * @return
     */
    @POST(Constant.ADD_TODO_URL)
    Observable<UpdateTodo> addTodo(@Query("title") String title,
                                   @Query("content") String content,
                                   @Query("date") String date,
                                   @Query("type") int type,
                                   @Query("priority") int priority);

    /**
     * 删除一个Todo
     *
     * @param id
     * @return
     */
    @POST(Constant.DELETE_TODO_URL)
    Observable<DeleteTodo> deleteTodo(@Path("id") int id);

    /**
     * 更新一个Todo
     *
     * @param title
     * @param content
     * @param date
     * @param type
     * @param priority
     * @param id
     * @return
     */
    @POST(Constant.UPDATE_TODO_URL)
    Observable<UpdateTodo> updateTodo(
            @Path("id") int id,
            @Query("title") String title,
            @Query("content") String content,
            @Query("date") String date,
            @Query("type") int type,
            @Query("priority") int priority);

    /**
     * 完成Todo
     *
     * @param id
     * @return
     */
    @POST(Constant.FINISH_TODO_URL)
    Observable<FinishTodo> finishTodo(@Path("id") int id, @Query("status") int status);
}
