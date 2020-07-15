package com.zhoubo07.bannerlib.banner;

import android.content.Context;
import android.widget.ImageView;

/**
 * @Title banner加载图片的方式
 * @Author: zhoubo
 * @CreateDate: 2019-07-05 10:39
 */
public interface BannerDisplayImageHolder {
    void disPlayImage(Context context,ImageView iv, SimpleImageBannerBean data);
}
