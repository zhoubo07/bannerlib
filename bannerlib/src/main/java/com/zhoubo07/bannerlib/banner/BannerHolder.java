package com.zhoubo07.bannerlib.banner;

import android.support.v7.widget.RecyclerView;

/**
 * @title banner的holder，自定义条目和多类型条目的需要设置
 * @author: zhoubo
 * @CreateDate: 2020/12/3 4:47 PM
 */
public interface BannerHolder<T> {
    public abstract void updateUI(RecyclerView.ViewHolder viewHolder, int position, T data);
}
