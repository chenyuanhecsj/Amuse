package com.csjhecy.amuse.episode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csjhecy.amuse.R;
import com.csjhecy.amuse.common.BaseContract;
import com.mingle.widget.LoadingView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Admin on 2017/7/25.
 */

public class Text1Fragment extends Fragment implements BaseContract.View {
    private static Text1Fragment text1Fragment;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.loadView)
    LoadingView mLoadView;
    private EpisodePresenter mEpisodePresenter;

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
        mEpisodePresenter.getAnalyze();
        return view;
    }

    @Override
    public void showDatas(List datas) {
        MyAdapter myAdapter = new MyAdapter(datas);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            mLoadView.setVisibility(View.VISIBLE);
            mLoadView.setLoadingText("正在加载。。。");
            mLoadView.factor = 5;
        } else {
            mLoadView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
