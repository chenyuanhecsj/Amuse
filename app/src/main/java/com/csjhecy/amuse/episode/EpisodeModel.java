package com.csjhecy.amuse.episode;

import com.csjhecy.amuse.common.BaseContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Admin on 2017/7/25.
 */

public class EpisodeModel implements BaseContract.Model {

    private EpisodePresenter mEpisodePresenter;

    public EpisodeModel(EpisodePresenter episodePresenter) {
        mEpisodePresenter = episodePresenter;
    }


    public void doAnalaze(final String url) {

        DisposableObserver<List<EpisodeBean>> disposableObserver = Observable.create(new ObservableOnSubscribe<List<EpisodeBean>>() {


            @Override
            public void subscribe(@NonNull ObservableEmitter<List<EpisodeBean>> e) throws Exception {

                Document document = Jsoup.connect(url).get();
                List<EpisodeBean> map = new ArrayList<EpisodeBean>();
                EpisodeBean episodeBean;

                Elements titleElements = document.select("h1.dp-b");
                Elements descriptionElements = document.select("div.content-img");

                for (int i = 0; i < titleElements.size(); i++) {
                    episodeBean = new EpisodeBean();
                    episodeBean.setTitle(titleElements.get(i).text());

                    episodeBean.setDesc(descriptionElements.get(i).text().replace("搜索 复制",""));
                    map.add(episodeBean);
                }

                e.onNext(map);

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<EpisodeBean>>() {


                    @Override
                    public void onNext(@NonNull List<EpisodeBean> o) {
                        mEpisodePresenter.getAnalyzeSucess(o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        mEpisodePresenter.addSubscribe(disposableObserver);
    }
}
