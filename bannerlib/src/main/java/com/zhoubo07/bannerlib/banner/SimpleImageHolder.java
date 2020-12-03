package com.zhoubo07.bannerlib.banner;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.zhoubo07.bannerlib.R;


/**
 * @titile
 * @desc Created by seven on 2018/5/22.
 */

public class SimpleImageHolder implements BannerHolder<SimpleImageBannerBean> {

    @Override
    public void updateUI(RecyclerView.ViewHolder viewHolder, int position, SimpleImageBannerBean data) {
        ImageView iv = viewHolder.itemView.findViewById(R.id.iv_bannerview);
        if(TextUtils.isEmpty(data.getBannerImageUrl())){
            iv.setImageResource(data.getBannerImageDefult());
        }else {
            if(null!=BannerConfig.getBannerDisplayImageHolder()){
                BannerConfig.getBannerDisplayImageHolder().disPlayImage(iv.getContext(),iv,data);
            }
        }
    }
}
