package com.zhoubo07.bannerlib.layoutmanager;

import android.content.Context;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zhoubo07.bannerlib.layoutmanager.ItemTransformer;
import com.zhoubo07.bannerlib.layoutmanager.ItemTransformerPageScroll;
import com.zhoubo07.bannerlib.layoutmanager.ScrollLinearLayoutManager;

/**
 * @Title 可以转换操作的LayoutManager
 * @Desc: 可以用这个类实现画廊效果
 * @Author: zhoubo
 * @CreateDate: 2018/11/20 10:51 AM
 */
public class GalleryLayoutManager extends ScrollLinearLayoutManager {
    public GalleryLayoutManager(Context context) {
        super(context);
    }

    public GalleryLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public GalleryLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (mItemTransformer != null) {
            View child;
            for (int i = 0; i < getChildCount(); i++) {
                child = getChildAt(i);

                float fractionDistance = calculateToCenterFraction(child, 0);
                //当前item到中心了
                if (fractionDistance==0 ){
                    currPosition = getPosition(child);
                    //更新滑动监听
                    if (mItemTransformer instanceof ItemTransformerPageScroll){
                        ItemTransformerPageScroll scroll = (ItemTransformerPageScroll) mItemTransformer;
                        scroll.onPageScrolled(currPosition,fractionDistance);
                    }
                }
                mItemTransformer.transformItem(this, child, fractionDistance);
            }
        }
    }

    private int currPosition;

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int delta = -dx;
        int parentCenter = (getOrientationHelper().getEndAfterPadding() - getOrientationHelper().getStartAfterPadding()) / 2 + getOrientationHelper().getStartAfterPadding();
        if (dx > 0) {
            //If we've reached the last item, enforce limits
            View lastChild = getChildAt(getChildCount() - 1);
            if(null!=lastChild && getPosition(lastChild) == getItemCount() - 1){
                delta = -Math.max(0, Math.min(dx, (lastChild.getRight() - lastChild.getLeft()) / 2 + lastChild.getLeft() - parentCenter));
            }
        } else {
            //If we've reached the first item, enforce limits
//            if (mFirstVisiblePosition == 0) {
//                child = getChildAt(0);
//                delta = -Math.min(0, Math.max(dx, ((child.getRight() - child.getLeft()) / 2 + child.getLeft()) - parentCenter));
//            }
        }
        if (mItemTransformer != null) {
            View child2;
            for (int i = 0; i < getChildCount(); i++) {
                child2 = getChildAt(i);
                if (null!=child2){
                    float fractionDistance = calculateToCenterFraction(child2, -delta);
                    //当前item到中心了
                    if (fractionDistance==0 ) currPosition = getPosition(child2);
                    //更新滑动监听
                    if (mItemTransformer instanceof ItemTransformerPageScroll && getPosition(child2)==currPosition){
                        ItemTransformerPageScroll scroll = (ItemTransformerPageScroll) mItemTransformer;
                        scroll.onPageScrolled(currPosition,fractionDistance);
                    }

                    mItemTransformer.transformItem(this, child2, fractionDistance);
                }
            }
        }
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    private float calculateToCenterFraction(View child, float pendingOffset) {
        int distance = calculateDistanceCenter(child, pendingOffset);
        int childLength = child.getWidth();
        return Math.max(-1.f, Math.min(1.f, distance * 1.f / childLength));
    }

    /**
     * @param child
     * @param pendingOffset child view will scroll by
     * @return
     */
    private int calculateDistanceCenter(View child, float pendingOffset) {
        OrientationHelper orientationHelper = getOrientationHelper();
        int parentCenter ;
        if(isSnapLeft){
            parentCenter = orientationHelper.getStartAfterPadding()+child.getWidth()/2+snapLeftMargin;
        }else {
            parentCenter = (orientationHelper.getEndAfterPadding() - orientationHelper.getStartAfterPadding()) / 2 + orientationHelper.getStartAfterPadding();
        }
        return (int) (child.getWidth() / 2 - pendingOffset + child.getLeft() - parentCenter);
    }

    private OrientationHelper mHorizontalHelper;
    public OrientationHelper getOrientationHelper() {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(this);
        }
        return mHorizontalHelper;

    }

    private boolean isSnapLeft = false;//设置画廊左侧图片放大
    private int snapLeftMargin = 0;
    //设置画廊左侧图片放大
    public void setSnapLeft(boolean isSnapLeft,int leftMargin){
        this.isSnapLeft = isSnapLeft;
        this.snapLeftMargin = leftMargin;
    }

    private ItemTransformer mItemTransformer;
    public void setItemTransformer(ItemTransformer itemTransformer) {
        mItemTransformer = itemTransformer;
    }
}
