package com.zhoubo07.bannerlib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * adapter中调用onCreateViewHolder, onBindViewHolder
 * Created by jameson on 9/1/16.
 * changed by 二精-霁雪清虹 on 2017/11/19
 * changed by Sai on 2018/04/25
 */
public class CBPageAdapterHelper {
    private int sPagePadding = 0;
    private int sShowLeftCardWidth = 0;

    public void onCreateViewHolder(ViewGroup parent, View itemView) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        lp.width = parent.getWidth() - 2 * (sPagePadding + sShowLeftCardWidth);
        itemView.setLayoutParams(lp);
    }

    public void onBindViewHolder(View itemView, final int position, int itemCount) {
        int padding =  sPagePadding;
        itemView.setPadding(padding, 0, padding, 0);
        int leftMarin = position == 0 ? padding + sShowLeftCardWidth : 0;
        int rightMarin = position == itemCount - 1 ? padding +  sShowLeftCardWidth : 0;
        setViewMargin(itemView, leftMarin, 0, rightMarin, 0);
    }

    private void setViewMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (null == lp){
            lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom);
            view.setLayoutParams(lp);
        }
    }

    public void setPagePadding(int pagePadding) {
        sPagePadding = pagePadding;
    }

    public void setShowLeftCardWidth(int showLeftCardWidth) {
        sShowLeftCardWidth = showLeftCardWidth;
    }
}
