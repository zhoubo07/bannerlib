
package com.zhoubo07.bannerlib.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhoubo07.bannerlib.banner.BannerHolder;
import com.zhoubo07.bannerlib.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by Sai on 15/7/29.
 */
public class CBPageAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> datas;
    private CBPageAdapterHelper helper;
    private BannerHolder bannerHolder;
    private boolean canLoop;
    private OnItemClickListener onItemClickListener;

    private SparseIntArray layouts;
    private @LayoutRes
    int layoutResId;

    public CBPageAdapter(BannerHolder bannerHolder, SparseIntArray layouts, List<T> datas, @LayoutRes int layoutResId, boolean canLoop) {
        this.bannerHolder = bannerHolder;
        this.layouts = layouts;
        this.datas = datas;
        this.canLoop = canLoop;
        helper = new CBPageAdapterHelper();
        this.layoutResId = layoutResId;
    }

    public CBPageAdapter(BannerHolder bannerHolder, SparseIntArray layouts, List<T> datas, boolean canLoop) {
        this(bannerHolder, layouts, datas, 0, canLoop);
    }

    @Override
    public int getItemViewType(int position) {
        T item = datas.get(position % datas.size());
        if (item instanceof InsertCustomViewWrapper) {
            return ((InsertCustomViewWrapper) item).getBannerType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int currLayoutResId;
        if (null != layouts && layouts.get(viewType, 0) != 0) {
            currLayoutResId = layouts.get(viewType);
        } else {
            currLayoutResId = layoutResId;
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(currLayoutResId, parent, false);
        helper.onCreateViewHolder(parent, itemView);
        return new RecyclerView.ViewHolder(itemView) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        helper.onBindViewHolder(viewHolder.itemView, position, getItemCount());
        int realPosition = position % datas.size();
        bannerHolder.updateUI(viewHolder, realPosition, datas.get(realPosition));
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new OnPageClickListener(realPosition));
        }
    }

    @Override
    public int getItemCount() {
        //根据模式决定长度
        if (datas.size() == 0) return 0;
//        return canLoop ? 3*datas.size() : datas.size();
        return canLoop ? Integer.MAX_VALUE : datas.size();
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    public int getRealItemCount() {
        return datas.size();
    }

    public boolean isCanLoop() {
        return canLoop;
    }

    public void setPagePadding(int pagePadding) {
        helper.setPagePadding(pagePadding);
    }

    public void setShowLeftCardWidth(int showLeftCardWidth) {
        helper.setShowLeftCardWidth(showLeftCardWidth);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class OnPageClickListener implements View.OnClickListener {
        private int position;

        public OnPageClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(position);
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
