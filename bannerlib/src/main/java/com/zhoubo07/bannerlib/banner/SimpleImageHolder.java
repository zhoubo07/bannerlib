package com.zhoubo07.bannerlib.banner;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.zhoubo07.bannerlib.R;
import com.zhoubo07.bannerlib.holder.Holder;


/**
 * @titile
 * @desc Created by seven on 2018/5/22.
 */

public class SimpleImageHolder extends Holder<SimpleImageBannerBean> {
    public SimpleImageHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        //初始化的时候可以做一些什么操作
    }

    @Override
    public void updateUI(SimpleImageBannerBean data) {
        ImageView iv = itemView.findViewById(R.id.iv_bannerview);
        if(TextUtils.isEmpty(data.getBannerImageUrl())){
            iv.setImageResource(data.getBannerImageDefult());
        }else {
            if(null!=BannerConfig.getBannerDisplayImageHolder()){
                BannerConfig.getBannerDisplayImageHolder().disPlayImage(iv.getContext(),iv,data);
            }
        }
    }
}
