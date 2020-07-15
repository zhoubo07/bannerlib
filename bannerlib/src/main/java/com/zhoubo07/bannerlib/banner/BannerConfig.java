package com.zhoubo07.bannerlib.banner;

import com.zhoubo07.bannerlib.R;

/**
 * @Title banner配置
 * @Author: zhoubo
 * @CreateDate: 2019-07-05 11:38
 */
public class BannerConfig {
    private static int res_indicator_select_id = R.drawable.bannerlib_indicator_select;
    private static int res_indicator_unselect_id = R.drawable.bannerlib_indicator_unselect;

    public static int getResIndicatorSelectId() {
        return res_indicator_select_id;
    }

    public static void setResIndicatorSelectId(int res_indicator_select_id) {
        BannerConfig.res_indicator_select_id = res_indicator_select_id;
    }

    public static int getResIndicatorUnselectId() {
        return res_indicator_unselect_id;
    }

    public static void setResIndicatorUnselectId(int res_indicator_unselect_id) {
        BannerConfig.res_indicator_unselect_id = res_indicator_unselect_id;
    }

    private static BannerDisplayImageHolder bannerDisplayImageHolder = null;

    public static void setBannerDisplayImageHolder(BannerDisplayImageHolder bannerDisplayImageHolder){
        BannerConfig.bannerDisplayImageHolder = bannerDisplayImageHolder;
    }

    public static BannerDisplayImageHolder getBannerDisplayImageHolder(){
        return bannerDisplayImageHolder;
    }
}
