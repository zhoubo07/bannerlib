
package com.zhoubo07.bannerlib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zhoubo07.bannerlib.listener.OnItemClickListener;
import com.zhoubo07.bannerlib.holder.CBViewHolderCreator;
import com.zhoubo07.bannerlib.holder.Holder;

import java.util.List;
import java.util.Map;

/**
 * Created by Sai on 15/7/29.
 */
public class CBPageAdapter<T> extends RecyclerView.Adapter<Holder> {
    protected List<T> datas;
    private CBViewHolderCreator creator;
    private CBPageAdapterHelper helper;
    private boolean canLoop;
    private OnItemClickListener onItemClickListener;
    private Map<Integer, View> insertViewMap;

    public CBPageAdapter(CBViewHolderCreator creator, List<T> datas, boolean canLoop, Map<Integer, View> insertViewMap) {
        this.creator = creator;
        this.datas = datas;
        this.canLoop = canLoop;
        this.insertViewMap = insertViewMap;
        helper = new CBPageAdapterHelper();
    }

    public CBPageAdapter(CBViewHolderCreator creator, List<T> datas, boolean canLoop) {
        this(creator, datas, canLoop, null);
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
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null != insertViewMap && null != insertViewMap.get(viewType)) {
            if (null == insertViewMap.get(viewType).getLayoutParams()) {
                FrameLayout.LayoutParams layoutParams
                        = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                insertViewMap.get(viewType).setLayoutParams(layoutParams);
            }
            return new Holder(insertViewMap.get(viewType)) {
                @Override
                protected void initView(View itemView) {

                }

                @Override
                public void updateUI(Object data) {

                }
            };
        }

        int layoutId = creator.getLayoutId();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        helper.onCreateViewHolder(parent, itemView);
        return creator.createHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        helper.onBindViewHolder(holder.itemView, position, getItemCount());
        /*if (null != insertViewMap && insertViewMap.containsKey(getItemViewType(position))) {
            return;
        }*/
        int realPosition = position % datas.size();
        holder.updateUI(datas.get(realPosition));
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new OnPageClickListener(realPosition));
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
