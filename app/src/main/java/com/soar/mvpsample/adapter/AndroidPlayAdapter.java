package com.soar.mvpsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.soar.mvpsample.R;
import com.soar.mvpsample.activity.ArticleListActivity;
import com.soar.mvpsample.bean.ArticlesBean;
import com.soar.mvpsample.constant.RouteConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * NAME：YONG_
 * Created at: 2017/11/29 10
 * Describe:
 */
public class AndroidPlayAdapter extends RecyclerView.Adapter<AndroidPlayAdapter.ViewHolder> {

    private Context context;
    private List<ArticlesBean> datas;
    private ItemClickListener itemClickListener;

    public void setData(List<ArticlesBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public ArticlesBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context =parent.getContext()).inflate(R.layout.item_android_play, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticlesBean item = getItem(position);
        holder.ivNew.setVisibility(item.fresh?View.VISIBLE:View.GONE);
        holder.tvClassify.setText(item.chapterName);
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_place_welfare)
                .error(R.mipmap.ic_place_welfare);
        Glide.with(context)
                .load(item.envelopePic)
                .apply(options)
                .into(holder.ivImage);
        holder.tvTitle.setText(item.title);
        holder.textView2.setText(item.niceDate);
        holder.textView3.setText(" "+item.author);

        holder.tvClassify.setOnClickListener(v ->
                ARouter.getInstance()
                        .build(RouteConstants.Music.ARTICLE)
                        .withInt(ArticleListActivity.CID, item.getChapterId())
                        .withString(ArticleListActivity.CHAPTER_NAME, item.getChapterName())
                        .navigation());
        if (itemClickListener != null)
            holder.itemView.setOnClickListener(v -> itemClickListener.onItemClick(holder.getLayoutPosition()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_new)
        ImageView ivNew;
        @BindView(R.id.tv_classify)
        TextView tvClassify;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.textView2)
        TextView textView2;
        @BindView(R.id.textView3)
        TextView textView3;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
