package com.zhoubo07.bannerlib.listener;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Sai on 15/7/29.
 * 翻页指示器适配器
 */
public class CBPageChangeListener implements OnPageChangeListener{
    private ArrayList<ImageView> pointViews;
    private int[] page_indicatorId;
    private OnPageChangeListener onPageChangeListener;
    public CBPageChangeListener(ArrayList<ImageView> pointViews,int page_indicatorId[]){
        this.pointViews=pointViews;
        this.page_indicatorId = page_indicatorId;
    }

    /*/newState :
                正在滚动 SCROLL_STATE_IDLE = 0;
                正在被外部拖拽,一般为用户正在用手指滚动 SCROLL_STATE_DRAGGING = 1;
                自动滚动开始 SCROLL_STATE_SETTLING = 2;*/
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if(onPageChangeListener != null)onPageChangeListener.onScrollStateChanged(recyclerView, newState);
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if(onPageChangeListener != null)onPageChangeListener.onScrolled(recyclerView,dx,dy);
    }

    public void onPageSelected(int index) {
        for (int i = 0; i < pointViews.size(); i++) {
            pointViews.get(index).setImageResource(page_indicatorId[1]);
            if (index != i) {
                pointViews.get(i).setImageResource(page_indicatorId[0]);
            }
        }
        if(onPageChangeListener != null)onPageChangeListener.onPageSelected(index);

    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }
}
