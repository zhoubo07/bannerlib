package com.zhoubo07.bannerlib.layoutmanager;

import android.view.View;

/**
 * @Title 默认实现的画廊的item变化器
 * @Author: zhoubo
 * @CreateDate: 2019-09-05 11:19
 */
public class DefaultGalleryItemTransformer implements ItemTransformer{
    @Override
    public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
        //以圆心进行缩放
        item.setPivotX(item.getWidth() / 2.0f);
        item.setPivotY(item.getHeight() / 2.0f);
        float scale = 1 - 0.2f * Math.abs(fraction);
        item.setScaleX(scale);
        item.setScaleY(scale);
    }
}
