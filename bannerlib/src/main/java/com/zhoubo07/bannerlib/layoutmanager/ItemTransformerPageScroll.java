package com.zhoubo07.bannerlib.layoutmanager;

import android.view.View;

/**
 * @Title 带滑动监听的转换器
 * @Author: zhoubo
 * @CreateDate: 2019-09-05 11:30
 */
public abstract class ItemTransformerPageScroll extends DefaultGalleryItemTransformer{
    int beforPosition = -1;
    float beforFraction = -1;


    private int itemCount;
    public ItemTransformerPageScroll(int itemCount){
        this.itemCount = itemCount;
    }

    @Override
    public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
        super.transformItem(layoutManager, item, fraction);
    }


    /**
     * 这个在画廊的管理器里有调用，不用管这个方法，回调方法实现下面的抽象方法
     */
    public void onPageScrolled(int position, float fraction){
        int realPosition = position%itemCount;
        if (realPosition==beforPosition && fraction==beforFraction) return;
        beforFraction = realPosition;
        beforFraction = fraction;

        if (fraction>=0) onPageScrolled(realPosition,fraction,false);
        else onPageScrolled(realPosition,Math.abs(fraction),true);
    }

    public abstract void onPageScrolled(int position, float fraction,boolean isLeftWithCenter);
}
