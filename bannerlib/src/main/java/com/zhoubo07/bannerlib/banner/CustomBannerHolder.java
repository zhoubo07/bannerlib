package com.zhoubo07.bannerlib.banner;

import android.view.View;

/**
 * @titile
 * @desc Created by seven on 2018/5/22.
 */

public abstract class CustomBannerHolder<T>  {
    public CustomBannerHolder() {
    }
    public abstract void initView(View itemView);

    public abstract void updateUI(View itemView,T data);
}
