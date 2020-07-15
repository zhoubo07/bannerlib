package com.zhoubo07.bannerlib.banner;

/**
 * @titile
 * @desc Created by seven on 2018/5/22.
 */

public class SimpleImageBannerBean {
    private String bannerImageUrl;
    private int bannerImageDefult;

    public int getBannerImageDefult() {
        return bannerImageDefult;
    }

    public void setBannerImageDefult(int bannerImageDefult) {
        this.bannerImageDefult = bannerImageDefult;
    }

    public String getBannerImageUrl() {

        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }
}
