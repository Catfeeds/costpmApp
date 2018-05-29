package com.fudan.cosmosapp.fragment;

/**
 * Created by yinxiaofei on 2017/7/2.
 */

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fudan.cosmosapp.R;
import com.fudan.cosmosapp.adapter.HomePage_ListAdapter;
import com.fudan.cosmosapp.app.CosmosApplication;
import com.fudan.cosmosapp.base.BaseFragment;
import com.fudan.cosmosapp.bean.NewsBean;
import com.fudan.cosmosapp.ui.home.presenter.HomePresenter;
import com.fudan.cosmosapp.ui.home.presenter.Imple.HomePresenterImple;
import com.fudan.cosmosapp.ui.home.view.HomeView;
import com.fudan.cosmosapp.utils.ToastUtils;
import com.fudan.cosmosapp.widget.LoadListView;

/**
 * Created by lt on 2015/12/1.
 */
public class HomeFragment extends BaseFragment implements LoadListView.ILoadListener,
        LoadListView.IReflashListener,LoadListView.HiddenTop, HomeView, AdapterView.OnItemClickListener {

    private GridLayout  top_rl = null;
    private LoadListView ListView_homepage=null;

    private HomePage_ListAdapter homepage_adapter =null;

    private HomePresenter mPresenter;

    private int mCurrentPage = 0;

    private Animator mAnimator;//动画属性

    public static String[] titel={"如何生物转CS，并在斯坦福大学三年拿到PhD：独家专访李纪为博士",
            "资源 | 关于大数据，你应该知道的75个专业术语。",
            "演讲 | Yann LeCun 现身上海交大，对话中国人工智能科技新锐。",
    "业界 | OpenAI 新研究：通过自适应参数噪声提升强化学习性能。",
            "资源 | 关于大数据，你应该知道的75个专业术语。",
            "演讲 | Yann LeCun 现身上海交大，对话中国人工智能科技新锐。",
            "业界 | OpenAI 新研究：通过自适应参数噪声提升强化学习性能。",
            "资源 | 关于大数据，你应该知道的75个专业术语。",
            "演讲 | Yann LeCun 现身上海交大，对话中国人工智能科技新锐。",
            "业界 | OpenAI 新研究：通过自适应参数噪声提升强化学习性能。"};
    public static String[] content={"在人工智能逐渐成为热点的今天，各家科技公司与机构对于相关人才的需求也在不断增大。然而，机器学习目前仍是一个门槛很高的领域",
            "近日，Ramesh Dontha 在 DataConomy 上连发两篇文章，扼要而全面地介绍了关于大数据的 75 个核心术语，这不仅是大数据初学者很好的入门资料",
    "7 月初，Facebook 人工智能研究院院长、纽约大学终身教授 Yann LeCun 受邀现身上海交通大学闵行校区，围绕 Deep Learning and AI：Past, Present, and Future 发表主题学术报告。",
    "OpenAI 的研究人员发现，改善强化学习算法参数中的自适应噪声性能可以提升性能。这种探索方式易于实现，同时很少降低系统表现，因此值得一试。",
            "近日，Ramesh Dontha 在 DataConomy 上连发两篇文章，扼要而全面地介绍了关于大数据的 75 个核心术语，这不仅是大数据初学者很好的入门资料",
            "7 月初，Facebook 人工智能研究院院长、纽约大学终身教授 Yann LeCun 受邀现身上海交通大学闵行校区，围绕 Deep Learning and AI：Past, Present, and Future 发表主题学术报告。",
            "OpenAI 的研究人员发现，改善强化学习算法参数中的自适应噪声性能可以提升性能。这种探索方式易于实现，同时很少降低系统表现，因此值得一试。",
            "近日，Ramesh Dontha 在 DataConomy 上连发两篇文章，扼要而全面地介绍了关于大数据的 75 个核心术语，这不仅是大数据初学者很好的入门资料",
            "7 月初，Facebook 人工智能研究院院长、纽约大学终身教授 Yann LeCun 受邀现身上海交通大学闵行校区，围绕 Deep Learning and AI：Past, Present, and Future 发表主题学术报告。",
            "OpenAI 的研究人员发现，改善强化学习算法参数中的自适应噪声性能可以提升性能。这种探索方式易于实现，同时很少降低系统表现，因此值得一试。"};

    public static String[]    titel1={"如何生物转CS，并在斯坦福大学三年拿到PhD：独家专访李纪为博士",
            "资源 | 关于大数据，你应该知道的75个专业术语。",
            "演讲 | Yann LeCun 现身上海交大，对话中国人工智能科技新锐。",
            "业界 | OpenAI 新研究：通过自适应参数噪声提升强化学习性能。",
            "资源 | 关于大数据，你应该知道的75个专业术语。",
            "演讲 | Yann LeCun 现身上海交大，对话中国人工智能科技新锐。",
            "业界 | OpenAI 新研究：通过自适应参数噪声提升强化学习性能。",
            "资源 | 关于大数据，你应该知道的75个专业术语。",
            "演讲 | Yann LeCun 现身上海交大，对话中国人工智能科技新锐。",
            "业界 | OpenAI 新研究：通过自适应参数噪声提升强化学习性能。",
            "如何生物转CS，并在斯坦福大学三年拿到PhD：独家专访李纪为博士",
            "资源 | 关于大数据，你应该知道的75个专业术语。",
            "演讲 | Yann LeCun 现身上海交大，对话中国人工智能科技新锐。",
            "业界 | OpenAI 新研究：通过自适应参数噪声提升强化学习性能。",
            "资源 | 关于大数据，你应该知道的75个专业术语。",
            "演讲 | Yann LeCun 现身上海交大，对话中国人工智能科技新锐。",
            "业界 | OpenAI 新研究：通过自适应参数噪声提升强化学习性能。",
            "资源 | 关于大数据，你应该知道的75个专业术语。",
            "演讲 | Yann LeCun 现身上海交大，对话中国人工智能科技新锐。",
            "业界 | OpenAI 新研究：通过自适应参数噪声提升强化学习性能。"};
    public static String[]    content1={"在人工智能逐渐成为热点的今天，各家科技公司与机构对于相关人才的需求也在不断增大。然而，机器学习目前仍是一个门槛很高的领域",
            "近日，Ramesh Dontha 在 DataConomy 上连发两篇文章，扼要而全面地介绍了关于大数据的 75 个核心术语，这不仅是大数据初学者很好的入门资料",
            "7 月初，Facebook 人工智能研究院院长、纽约大学终身教授 Yann LeCun 受邀现身上海交通大学闵行校区，围绕 Deep Learning and AI：Past, Present, and Future 发表主题学术报告。",
            "OpenAI 的研究人员发现，改善强化学习算法参数中的自适应噪声性能可以提升性能。这种探索方式易于实现，同时很少降低系统表现，因此值得一试。",
            "近日，Ramesh Dontha 在 DataConomy 上连发两篇文章，扼要而全面地介绍了关于大数据的 75 个核心术语，这不仅是大数据初学者很好的入门资料",
            "7 月初，Facebook 人工智能研究院院长、纽约大学终身教授 Yann LeCun 受邀现身上海交通大学闵行校区，围绕 Deep Learning and AI：Past, Present, and Future 发表主题学术报告。",
            "OpenAI 的研究人员发现，改善强化学习算法参数中的自适应噪声性能可以提升性能。这种探索方式易于实现，同时很少降低系统表现，因此值得一试。",
            "近日，Ramesh Dontha 在 DataConomy 上连发两篇文章，扼要而全面地介绍了关于大数据的 75 个核心术语，这不仅是大数据初学者很好的入门资料",
            "7 月初，Facebook 人工智能研究院院长、纽约大学终身教授 Yann LeCun 受邀现身上海交通大学闵行校区，围绕 Deep Learning and AI：Past, Present, and Future 发表主题学术报告。",
            "OpenAI 的研究人员发现，改善强化学习算法参数中的自适应噪声性能可以提升性能。这种探索方式易于实现，同时很少降低系统表现，因此值得一试。",
            "在人工智能逐渐成为热点的今天，各家科技公司与机构对于相关人才的需求也在不断增大。然而，机器学习目前仍是一个门槛很高的领域",
            "近日，Ramesh Dontha 在 DataConomy 上连发两篇文章，扼要而全面地介绍了关于大数据的 75 个核心术语，这不仅是大数据初学者很好的入门资料",
            "7 月初，Facebook 人工智能研究院院长、纽约大学终身教授 Yann LeCun 受邀现身上海交通大学闵行校区，围绕 Deep Learning and AI：Past, Present, and Future 发表主题学术报告。",
            "OpenAI 的研究人员发现，改善强化学习算法参数中的自适应噪声性能可以提升性能。这种探索方式易于实现，同时很少降低系统表现，因此值得一试。",
            "近日，Ramesh Dontha 在 DataConomy 上连发两篇文章，扼要而全面地介绍了关于大数据的 75 个核心术语，这不仅是大数据初学者很好的入门资料",
            "7 月初，Facebook 人工智能研究院院长、纽约大学终身教授 Yann LeCun 受邀现身上海交通大学闵行校区，围绕 Deep Learning and AI：Past, Present, and Future 发表主题学术报告。",
            "OpenAI 的研究人员发现，改善强化学习算法参数中的自适应噪声性能可以提升性能。这种探索方式易于实现，同时很少降低系统表现，因此值得一试。",
            "近日，Ramesh Dontha 在 DataConomy 上连发两篇文章，扼要而全面地介绍了关于大数据的 75 个核心术语，这不仅是大数据初学者很好的入门资料",
            "7 月初，Facebook 人工智能研究院院长、纽约大学终身教授 Yann LeCun 受邀现身上海交通大学闵行校区，围绕 Deep Learning and AI：Past, Present, and Future 发表主题学术报告。",
            "OpenAI 的研究人员发现，改善强化学习算法参数中的自适应噪声性能可以提升性能。这种探索方式易于实现，同时很少降低系统表现，因此值得一试。"};

    private ProgressBar progressBar;
    private TextView tvDes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View homepageView = inflater.inflate(R.layout.main_home_fragment,container,false);

        top_rl=homepageView.findViewById(R.id.gl_homepage_voice_photo);
        ListView_homepage=homepageView.findViewById(R.id.listview_home_page);
        progressBar = homepageView.findViewById(R.id.progress_bar);
        tvDes = homepageView.findViewById(R.id.tv_des);

        return homepageView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        initView();

        //设置代理人
        new HomePresenterImple(getActivity(),this).loadList(0);

    }

//    private void initView() {
//
//        homepage_adapter = new HomePage_ListAdapter(getContext(),null);
//        ListView_homepage.setAdapter(homepage_adapter);
//        ListView_homepage.setOnItemClickListener(this);
//        ListView_homepage.setInterface(this,this,this);
//    }

    //加载更多
    @Override
    public void onLoad() {
        Handler handler = new Handler() ;
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                getLoadData();
                ListView_homepage.loadComplete();
            }
        }, 2000);
    }

    //下拉刷新
    @Override
    public void onReflash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                getReflashData();
                ListView_homepage.reflashComplete();
            }
        }, 2000);
    }


    @Override
    public void changeTop(int d) {
        if(mAnimator != null && mAnimator.isRunning()){//判断动画存在  如果启动了,则先关闭
            mAnimator.cancel();
        }
        if(d == 0){
            //Toast.makeText(getActivity(),"向下滑动了",Toast.LENGTH_SHORT).show();
            mAnimator = ObjectAnimator.ofFloat(top_rl, "translationY", top_rl.getTranslationY(),0);//从当前位置位移到0位置
        }else{
            //Toast.makeText(getActivity(),"向上滑动了",Toast.LENGTH_SHORT).show();
            mAnimator = ObjectAnimator.ofFloat(top_rl, "translationY", top_rl.getTranslationY(),-top_rl.getHeight());//从当前位置移动到布局负高度的wiz
        }
        mAnimator.start();//执行动画
        if(d==0){
            top_rl.setVisibility(View.VISIBLE);
        }else {
            top_rl.setVisibility(View.GONE);
        }
    }

    private void  getLoadData(){
        mPresenter.loadList(mCurrentPage);
    }

    private void getReflashData(){
        mPresenter.listRefresh();
    }

    @Override
    public void setPresenter(HomePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void emptyView(boolean visible) {
        progressBar.setVisibility(View.GONE);
        tvDes.setVisibility(visible?View.GONE:View.VISIBLE);
    }

    @Override
    public void handleError(Throwable throwable) {
        ToastUtils.showToast(CosmosApplication.getContext(),throwable.getMessage());
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void listLoaded(NewsBean newsBean) {
        if(newsBean != null){
            mCurrentPage++;

            if(homepage_adapter == null){
                homepage_adapter = new HomePage_ListAdapter(getContext(),newsBean);
                ListView_homepage.setAdapter(homepage_adapter);
                ListView_homepage.setOnItemClickListener(this);
                ListView_homepage.setInterface(this,this,this);
            } else {
                homepage_adapter.onDataAdd(newsBean);
            }
        }
    }

    @Override
    public void listRefreshed(NewsBean newsBean) {
        homepage_adapter.onDateChange(newsBean);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}