package com.zhoubo07.bannerlib.layoutmanager;

import android.view.View;

/**
 * @Title 画廊的转换的接口
 * @Author: zhoubo
 * @CreateDate: 2019-09-05 11:26
 */
public interface ItemTransformer {
    /**
     * Apply a property transformation to the given item.
     *
     * @param layoutManager Current LayoutManager
     * @param item          Apply the transformation to this item
     * @param fraction      of page relative to the current front-and-center position of the pager.
     *                      0 is front and center. 1 is one full
     *                      page position to the right, and -1 is one page position to the left.
     *
     *                      比例是针对可视的几个item的，（画廊一般是三个）
     *                      0代表当前item位于中心；
     *                      1代表当前item位于左侧起点了，有item（当前右侧的item）位于中心了
     *                      -1代表当前item位于右侧起点了，有item（当前左侧的item）位于中心了
     */
    void transformItem(GalleryLayoutManager layoutManager, View item, float fraction);
}
