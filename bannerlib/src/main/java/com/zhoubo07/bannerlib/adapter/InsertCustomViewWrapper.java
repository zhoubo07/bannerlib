package com.zhoubo07.bannerlib.adapter;

/**
 * @title 向banner中插入自定义的布局的包装类
 * @desc:  使用时需要将banner实体类继承此类，然后设置需要插入的view对应的banner实体
 * @author: zhoubo
 * @CreateDate: 2020-10-28 18:00
 */
public class InsertCustomViewWrapper {
    private int bannerType;

    public int getBannerType() {
        return bannerType;
    }

    public void setBannerType(int bannerType) {
        this.bannerType = bannerType;
    }
}
