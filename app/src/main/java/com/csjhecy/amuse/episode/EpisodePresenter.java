package com.csjhecy.amuse.episode;

import com.csjhecy.amuse.common.BaseContract;

import java.util.List;

/**
 * Created by Admin on 2017/7/25.
 */

public class EpisodePresenter extends BaseContract.presenter {

    private BaseContract.View mEpisodeView;
    private EpisodeModel mModel;

    public EpisodePresenter(BaseContract.View episodeView) {
        this.mEpisodeView = episodeView;
        this.mModel = new EpisodeModel(this);
    }

    @Override
    public void getData() {

    }

    public void getAnalyze(String url) {
        mEpisodeView.showLoading(true);
        mModel.doAnalaze(url);
    }

    public void getAnalyzeSucess(List<EpisodeBean> datas){
        mEpisodeView.showLoading(false);
        mEpisodeView.showDatas(datas);
    }
}
