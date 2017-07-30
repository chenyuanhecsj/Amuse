package com.csjhecy.amuse.episode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.csjhecy.amuse.R;
import com.csjhecy.amuse.common.BaseContract;
import com.csjhecy.amuse.common.Global;
import com.mingle.widget.LoadingView;
import com.scwang.smartrefresh.header.CircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Admin on 2017/7/25.
 */

public class Text1Fragment extends Fragment implements BaseContract.View, OnRefreshListener, OnLoadmoreListener {
    private static Text1Fragment text1Fragment;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.loadView)
    LoadingView mLoadView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private EpisodePresenter mEpisodePresenter;
    private MyAdapter mMyAdapter;

    private static int nextPage = 1;


    public static final int STATUS_NORMAL = 1000;
    public static final int STATUS_REFRESHING = 1001;
    public static final int STATUS_LOADMORE = 1002;
    /**
     * 刷新状态
     */
    public static int STATE = STATUS_NORMAL;

    public static Text1Fragment newInstance(Bundle bundle) {
        if (text1Fragment == null) {
            text1Fragment = new Text1Fragment();
        }
        text1Fragment.setArguments(bundle);
        return text1Fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEpisodePresenter = new EpisodePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text1, null);
        unbinder = ButterKnife.bind(this, view);
        mEpisodePresenter.getAnalyze(Global.URl.ANALAZE_URL);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadmoreListener(this);
        mRefreshLayout.setRefreshHeader(new CircleHeader(getActivity()));
        return view;
    }

    @Override
    public void showDatas(List datas) {
        switch (STATE){
            case STATUS_NORMAL:
                mMyAdapter = new MyAdapter(datas);
                mRecyclerView.setAdapter(mMyAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                break;
            case STATUS_REFRESHING:
                mMyAdapter.getDatas().clear();
                mMyAdapter.setDatas(datas);
                mMyAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(0);
                mRefreshLayout.finishRefresh();
                break;
            case STATUS_LOADMORE:
                mMyAdapter.addMoreList(datas);
                mRefreshLayout.finishLoadmore();
                break;
        }
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            mLoadView.setVisibility(View.VISIBLE);
            mLoadView.setLoadingText("正在加载...");
            mLoadView.factor = 5;
        } else {
            mLoadView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mEpisodePresenter.unSubscribe();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
       STATE = STATUS_REFRESHING;
        mEpisodePresenter.getAnalyze(Global.URl.ANALAZE_URL);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        nextPage = nextPage + 1;
        if (nextPage <= 5) {
            STATE = STATUS_LOADMORE;
            mEpisodePresenter.getAnalyze(Global.URl.ANALAZE_URL1 + nextPage + ".html");
        } else {
            Toast.makeText(getActivity(), "已是最后一页啦！", Toast.LENGTH_SHORT).show();
        }
    }
}
