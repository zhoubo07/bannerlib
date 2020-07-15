package com.zhoubo07.bannerlib.helper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Title 靠左的Pager控制器
 * @Desc: 继承原生的控制页面居中的控制器，巨左显示
 * @Author: zhoubo
 * @CreateDate: 2018/11/28 11:30 AM
 */
public class PagerLeftSnapHelper extends PagerSnapHelper {

    @Nullable
    private OrientationHelper mVerticalHelper;
    @Nullable
    private OrientationHelper mHorizontalHelper;

    private RecyclerView mRecycleView;
    private int snapLeftMargin = 0;//设置画廊左侧图片放大

    public PagerLeftSnapHelper(int leftMargin){
        this.snapLeftMargin = leftMargin;
    }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
        this.mRecycleView = recyclerView;
    }

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToLeft(layoutManager, targetView,
                    getMyHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToLeft(layoutManager, targetView,
                    getMyVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    private int distanceToLeft(@NonNull RecyclerView.LayoutManager layoutManager,
                                 @NonNull View targetView, OrientationHelper helper) {
        final int childCenter = helper.getDecoratedStart(targetView)
                + (helper.getDecoratedMeasurement(targetView) / 2);

        final int containerCenter = helper.getStartAfterPadding() + (helper.getDecoratedMeasurement(targetView) / 2)
                +snapLeftMargin;
//        if (layoutManager.getClipToPadding()) {
//            containerCenter = helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
//        } else {
//            containerCenter = helper.getEnd() / 2;
//        }
        return childCenter - containerCenter;
    }


    @NonNull
    private OrientationHelper getMyVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (mVerticalHelper == null ) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return mVerticalHelper;
    }

    @NonNull
    private OrientationHelper getMyHorizontalHelper(
            @NonNull RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }

}
