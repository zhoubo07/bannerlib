package com.zhoubo07.bannerlib.banner;

import com.zhoubo07.bannerlib.ConvenientBanner;
import com.zhoubo07.bannerlib.layoutmanager.ItemTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title Banner工具的参数类
 * @Author: zhoubo
 * @CreateDate: 2019-08-12 15:21
 */
public class BannerOptions {
    private final ConvenientBanner convenientBanner;
    private final int indicatorSelectResId;//指示器选中的图标资源
    private final int indicatorUnselectResId;//指示器未选中的图标资源
    private final boolean hideIndicator;//隐藏指示器
    private final boolean hideIndicatorWithAlone;//当banner只有一条的时候，隐藏指示器，默认为true
    private final BannerDisplayImageHolder bannerDisplayImageHolder;//图片加载方式
    private final double ratio;// 高/宽 比例
    private final ArrayList<String> bannerImgs;//banner的显示图片集合
    private final int defultPic;  //占位图
    private final int widthPx;//  banner固定宽度
    private final int heightPx;// banner固定高度
    private final int pageItemPaddingPx;//每页的padding
    private final boolean isGallery;//设置为画廊样式（中间图片放大，左右图片展示一小半），默认false
    private final boolean isGalleryAndSnapLeft;//设置画廊样式并且左侧图片放大；默认false
    private final ItemTransformer galleryItemTransformer;//画廊的变化转换器
    private final int leftOffsetPx;//中间向左的偏移量
    private final int leftMarginPx;//初始的时候左侧的margin
    private final boolean isCorners;  //是否是圆角（如果是画廊样式的默认圆角）
    private final int itemLayoutId;   //自定义banner布局
    private final CustomBannerHolder customBannerHolder;//自定义banner布局的适配器
    private final List customDataList;//自定义banner布局的数据源

    private BannerOptions(Builder builder) {
        convenientBanner = builder.convenientBanner;
        indicatorSelectResId = builder.indicatorSelectResId;
        indicatorUnselectResId = builder.indicatorUnselectResId;
        hideIndicator = builder.hideIndicator;
        hideIndicatorWithAlone = builder.hideIndicatorWithAlone;
        bannerDisplayImageHolder = builder.bannerDisplayImageHolder;
        ratio = builder.ratio;
        bannerImgs = builder.bannerImgs;
        defultPic = builder.defultPic;
        widthPx = builder.widthPx;
        heightPx = builder.heightPx;
        pageItemPaddingPx = builder.pageItemPaddingPx;
        isGallery = builder.isGallery;
        isGalleryAndSnapLeft = builder.isGalleryAndSnapLeft;
        galleryItemTransformer = builder.galleryItemTransformer;
        leftOffsetPx = builder.leftOffsetPx;
        leftMarginPx = builder.leftMarginPx;
        isCorners = builder.isCorners;
        itemLayoutId = builder.itemLayoutId;
        customBannerHolder = builder.customBannerHolder;
        customDataList = builder.customDataList;
    }

    public static Builder newBuilder(ConvenientBanner convenientBanner) {
        return new Builder(convenientBanner);
    }

    public ConvenientBanner getConvenientBanner() {
        return convenientBanner;
    }

    public int getIndicatorSelectResId() {
        return indicatorSelectResId;
    }

    public int getIndicatorUnselectResId() {
        return indicatorUnselectResId;
    }

    public boolean isHideIndicator() {
        return hideIndicator;
    }

    public boolean isHideIndicatorWithAlone() {
        return hideIndicatorWithAlone;
    }

    public BannerDisplayImageHolder getBannerDisplayImageHolder() {
        return bannerDisplayImageHolder;
    }

    public double getRatio() {
        return ratio;
    }

    public ArrayList<String> getBannerImgs() {
        return bannerImgs;
    }

    public int getDefultPic() {
        return defultPic;
    }

    public int getWidthPx() {
        return widthPx;
    }

    public int getHeightPx() {
        return heightPx;
    }

    public boolean isGallery() {
        return isGallery;
    }

    public boolean isGalleryAndSnapLeft() {
        return isGalleryAndSnapLeft;
    }

    public boolean isCorners() {
        return isCorners;
    }

    public int getItemLayoutId() {
        return itemLayoutId;
    }

    public CustomBannerHolder getCustomBannerHolder() {
        return customBannerHolder;
    }

    public List getCustomDataList() {
        return customDataList;
    }

    public int getPageItemPaddingPx() {
        return pageItemPaddingPx;
    }

    public int getLeftOffsetPx() {
        return leftOffsetPx;
    }

    public int getLeftMarginPx() {
        return leftMarginPx;
    }

    public ItemTransformer getGalleryItemTransformer() {
        return galleryItemTransformer;
    }

    public static final class Builder {
        private ConvenientBanner convenientBanner;
        private int indicatorSelectResId;//指示器选中的图标资源
        private int indicatorUnselectResId;//指示器未选中的图标资源
        private boolean hideIndicator;//隐藏指示器（优先级最高，不关心其他情况，如果设置此属性则隐藏指示器）
        private boolean hideIndicatorWithAlone = true;//当banner只有一条的时候，隐藏指示器，默认为true
        private BannerDisplayImageHolder bannerDisplayImageHolder;//图片加载方式
        private double ratio;// 高/宽 比例
        private ArrayList<String> bannerImgs;//banner的显示图片集合
        private int defultPic;  //占位图
        private int widthPx;//  banner固定宽度
        private int heightPx;// banner固定高度
        private int pageItemPaddingPx;//每页的padding
        private boolean isGallery = false;//设置为画廊样式（中间图片放大，左右图片展示一小半），默认false
        private boolean isGalleryAndSnapLeft = false;//设置画廊样式并且左侧图片放大；默认false
        private ItemTransformer galleryItemTransformer;//画廊的变化转换器
        private int leftOffsetPx;//中间向左的偏移量
        private int leftMarginPx;//初始的时候左侧的margin
        private boolean isCorners;  //是否是圆角
        private int itemLayoutId;   //自定义banner布局
        private CustomBannerHolder customBannerHolder;//自定义banner布局的适配器
        private List customDataList;//自定义banner布局的数据源

        public Builder(ConvenientBanner convenientBanner){
            this.convenientBanner = convenientBanner;
        }

        public Builder indicatorSelectResId(int indicatorSelectResId) {
            this.indicatorSelectResId = indicatorSelectResId;
            return this;
        }

        public Builder indicatorUnselectResId(int indicatorUnselectResId) {
            this.indicatorUnselectResId = indicatorUnselectResId;
            return this;
        }

        public Builder hideIndicator(boolean hideIndicator) {
            this.hideIndicator = hideIndicator;
            return this;
        }

        public Builder hideIndicatorWithAlone(boolean hideIndicatorWithAlone) {
            this.hideIndicatorWithAlone = hideIndicatorWithAlone;
            return this;
        }

        public Builder displayImageHolder(BannerDisplayImageHolder bannerDisplayImageHolder) {
            this.bannerDisplayImageHolder = bannerDisplayImageHolder;
            return this;
        }

        public Builder ratio(double ratio) {
            this.ratio = ratio;
            return this;
        }

        public Builder bannerImgs(ArrayList<String> bannerImgs) {
            this.bannerImgs = bannerImgs;
            return this;
        }

        public Builder defultPic(int defultPic) {
            this.defultPic = defultPic;
            return this;
        }

        public Builder widthPx(int widthPx) {
            this.widthPx = widthPx;
            return this;
        }

        public Builder heightPx(int heightPx) {
            this.heightPx = heightPx;
            return this;
        }

        public Builder isGallery(boolean isGallery) {
            this.isGallery = isGallery;
            return this;
        }

        public Builder isGalleryAndSnapLeft(boolean isGalleryAndSnapLeft) {
            this.isGalleryAndSnapLeft = isGalleryAndSnapLeft;
            return this;
        }

        public Builder isCorners(boolean isCorners) {
            this.isCorners = isCorners;
            return this;
        }

        public Builder itemLayoutId(int itemLayoutId) {
            this.itemLayoutId = itemLayoutId;
            return this;
        }

        public Builder customBannerHolder(CustomBannerHolder customBannerHolder) {
            this.customBannerHolder = customBannerHolder;
            return this;
        }

        public Builder customDataList(List customDataList) {
            this.customDataList = customDataList;
            return this;
        }
        public Builder leftOffsetPx(int leftOffsetPx) {
            this.leftOffsetPx = leftOffsetPx;
            return this;
        }

        public Builder leftMarginPx(int leftMarginPx) {
            this.leftMarginPx = leftMarginPx;
            return this;
        }

        public Builder pageItemPaddingPx(int pageItemPaddingPx) {
            this.pageItemPaddingPx = pageItemPaddingPx;
            return this;
        }

        public Builder galleryItemTransformer(ItemTransformer galleryItemTransformer) {
            this.galleryItemTransformer = galleryItemTransformer;
            return this;
        }


        public BannerOptions build() {
            if (isGalleryAndSnapLeft) isGallery = true;
            return new BannerOptions(this);
        }
    }

}
