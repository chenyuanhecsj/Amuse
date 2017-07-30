package com.csjhecy.amuse.episode;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.csjhecy.amuse.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2017/7/27.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private List<EpisodeBean> datas;

    public MyAdapter(List<EpisodeBean> datas) {
        this.datas = datas;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.fragment_text1_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_desc.setText(datas.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_desc)
        TextView tv_desc;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void addMoreList(List<EpisodeBean> datas){
        int itemCount = getItemCount();
        this.datas.addAll(datas);
        notifyItemRangeInserted(itemCount,datas.size());
    }

    public List<EpisodeBean> getDatas() {
        return datas;
    }

    public void setDatas(List<EpisodeBean> datas) {
        this.datas = datas;
    }
}
