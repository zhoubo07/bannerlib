package com.zhoubo07.bannerlib.banner;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import com.zhoubo07.bannerlib.ConvenientBanner;
import com.zhoubo07.bannerlib.R;
import com.zhoubo07.bannerlib.holder.CBViewHolderCreator;
import com.zhoubo07.bannerlib.holder.Holder;
import com.zhoubo07.bannerlib.layoutmanager.DefaultGalleryItemTransformer;
import com.zhoubo07.bannerlib.layoutmanager.ItemTransformer;
import com.zhoubo07.bannerlib.listener.OnItemClickListener;
import com.zhoubo07.bannerlib.layoutmanager.GalleryLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置banner工具类
 * Created by seven on 2017/4/19.
 */

public class BannerSetUtil {
    /**
     * @param bannerOptions       banner参数
     * @param onItemClickListener banner点击的监听
     */
    public static void setBanner(BannerOptions bannerOptions, OnItemClickListener onItemClickListener) {
        ConvenientBanner convenientBanner = bannerOptions.getConvenientBanner();
        //计算banner宽高
        int widthPx = bannerOptions.getWidthPx();
        int heightPx = bannerOptions.getHeightPx();
        double ratio = bannerOptions.getRatio();
        if (ratio != 0) {
            if (widthPx == 0 && heightPx != 0) widthPx = (int) (heightPx / ratio);
            else if (widthPx != 0 && heightPx == 0) heightPx = (int) (widthPx * ratio);
            else if (widthPx == 0 && heightPx == 0) {
                widthPx = getScreenWidth(convenientBanner.getContext());
                heightPx = (int) (widthPx * ratio);
            }
        }

        //设置banner的左侧放大、居左、边距； （一般不用设置）
        convenientBanner.setPagePadding(bannerOptions.getPageItemPaddingPx());
        convenientBanner.setShowLeftCardWidth(bannerOptions.getLeftMarginPx());
        convenientBanner.setSnapLeft(bannerOptions.isGalleryAndSnapLeft(), bannerOptions.getLeftOffsetPx());

        //判断此次banner加载是不是自定义布局，图片列表不为空则是图片banner
        int itemLayoutId = bannerOptions.getItemLayoutId();
        if (itemLayoutId == 0) {
            //没有设定自定义布局id，所以是图片banner
            List<String> bannerImgs = bannerOptions.getBannerImgs();
            List<SimpleImageBannerBean> bannerImgBeans= bannerOptions.getBannerImgBeans();

            if (null == bannerImgs && null == bannerImgBeans) throw new RuntimeException("缺少图片数据源");

            //判断是否画廊样式
            boolean isGallery = bannerOptions.isGallery();
            if (isGallery) {
                //设置画廊样式LayoutManager
                setGalleryManager(convenientBanner, bannerOptions);
            }



            setPage(bannerOptions, getImageBannerBeanList(bannerImgs,bannerImgBeans, bannerOptions.getDefultPic()), onItemClickListener);
        } else {
            //设置view样式
            List customDataList = bannerOptions.getCustomDataList();
            CustomBannerHolder customBannerHolder = bannerOptions.getCustomBannerHolder();
            if (null == customDataList) throw new RuntimeException("缺少数据源");
            if (null == customBannerHolder) throw new RuntimeException("请设置CustomBannerHolder");
            setBannerView(bannerOptions, itemLayoutId, customDataList, customBannerHolder, onItemClickListener);
        }

        //设置banner的宽高
        setBannerParams(convenientBanner, widthPx, heightPx);
    }

    /**
     * 设置banner的页面
     *
     * @param imageList           banner内容（url和defult实体）
     * @param onItemClickListener 条目监听
     * @param bannerOptions       banner参数，指示器、圆角
     */
    private static void setPage(BannerOptions bannerOptions,
                                List<SimpleImageBannerBean> imageList,
                                OnItemClickListener onItemClickListener) {
        final int pageResId;
        if (bannerOptions.isCorners()) pageResId = R.layout.bannerlib_bannerview_image_radius;
        else pageResId = R.layout.bannerlib_bannerview_image;
        bannerOptions.getConvenientBanner().setPages(
                imageList,
                bannerOptions.getInsertViewMap(),
                new CBViewHolderCreator() {
                    @Override
                    public Holder createHolder(View itemView) {
                        return new SimpleImageHolder(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return pageResId;
                    }
                });

        //设置指示器和条目监听
        setIndicatorAndItemListener(bannerOptions,imageList.size(),onItemClickListener);
    }

    private static <T> void setBannerView(BannerOptions bannerOptions,
                                         final int itemLayoutId,
                                         final List<T> dataList,
                                         final CustomBannerHolder holder,
                                         OnItemClickListener onItemClickListener) {
        bannerOptions.getConvenientBanner().setShowLeftCardWidth(0);
        bannerOptions.getConvenientBanner().setPages(
                dataList,
                bannerOptions.getInsertViewMap(),
                new CBViewHolderCreator() {
                    @Override
                    public Holder createHolder(View itemView) {
                        Holder holder0 = new Holder<T>(itemView) {
                            @Override
                            protected void initView(View itemView) {
                                holder.initView(itemView);
                            }

                            @Override
                            public void updateUI(T data) {
                                holder.updateUI(itemView, data);
                            }
                        };
                        return holder0;
                    }

                    @Override
                    public int getLayoutId() {
                        return itemLayoutId;
                    }
                });

        //设置指示器和条目监听
        setIndicatorAndItemListener(bannerOptions,dataList.size(),onItemClickListener);
    }


    /**
     * 设置指示器和条目监听
     * @param bannerOptions     banner参数
     * @param listCount         数据源数量
     * @param onItemClickListener 条目监听
     */
    private static void setIndicatorAndItemListener(BannerOptions bannerOptions,int listCount,OnItemClickListener onItemClickListener){
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        if (bannerOptions.isHideIndicator()) {
            //隐藏指示器（优先级最高，不关心其他情况，如果设置此属性则隐藏指示器）
        } else {
            if (bannerOptions.isHideIndicatorWithAlone() && listCount <= 1) {
                //当数据源只有一条的时候，隐藏指示器，默认为true (这里的数据源不会为空，前面已经判断过了)
            } else {
                bannerOptions.getConvenientBanner().setPageIndicator(new int[]{
                        bannerOptions.getIndicatorUnselectResId() == 0 ? BannerConfig.getResIndicatorUnselectId() : bannerOptions.getIndicatorUnselectResId(),
                        bannerOptions.getIndicatorSelectResId() == 0 ? BannerConfig.getResIndicatorSelectId() : bannerOptions.getIndicatorSelectResId()})
                        //设置指示器的方向
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            }
        }
        bannerOptions.getConvenientBanner().setOnItemClickListener(onItemClickListener);
    }

    /**
     * 获取banner内容的实体类的集合
     *
     * @param bannerImgs
     * @param defultPic
     * @return
     */
    private static List<SimpleImageBannerBean> getImageBannerBeanList(final List<String> bannerImgs,
                                                                           List<SimpleImageBannerBean> simpleImageBannerBeans,
                                                                           int defultPic) {
        if (null!=simpleImageBannerBeans) return simpleImageBannerBeans;
        ArrayList<SimpleImageBannerBean> imageList = new ArrayList<>();
        for (int i = 0; i < bannerImgs.size(); i++) {
            SimpleImageBannerBean simpleImageBannerBean = new SimpleImageBannerBean();
            simpleImageBannerBean.setBannerImageUrl(bannerImgs.get(i));
            int defult = defultPic == 0 ? R.drawable.ic_placeholder : defultPic;
            simpleImageBannerBean.setBannerImageDefult(defult);
            imageList.add(simpleImageBannerBean);
        }
        return imageList;
    }

    /**
     * 设置banner的宽高参数
     *
     * @param convenientBanner
     * @param widthPx
     * @param heightPx
     */
    private static void setBannerParams(ConvenientBanner convenientBanner,
                                        int widthPx,
                                        int heightPx) {
        ViewGroup.LayoutParams layoutParams = convenientBanner.getLayoutParams();
        if (widthPx != 0) {
            layoutParams.width = widthPx;
        }
        if (heightPx != 0) {
            layoutParams.height = heightPx;
        }
        convenientBanner.setLayoutParams(layoutParams);
    }

    /**
     * 设置画廊样式的LayoutManager，可以转换操作的LayoutManager
     *
     * @param convenientBanner
     * @param bannerOptions
     */
    private static void setGalleryManager(ConvenientBanner convenientBanner, BannerOptions bannerOptions) {
        GalleryLayoutManager manager = new GalleryLayoutManager(convenientBanner.getContext(), GalleryLayoutManager.HORIZONTAL, false);
        manager.setSnapLeft(bannerOptions.isGalleryAndSnapLeft(), bannerOptions.getLeftOffsetPx());
        convenientBanner.setLayoutManager(manager);
        ItemTransformer galleryItemTransformer = bannerOptions.getGalleryItemTransformer();
        if (null == galleryItemTransformer) {
            galleryItemTransformer = new DefaultGalleryItemTransformer();
        }
        manager.setItemTransformer(galleryItemTransformer);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        return p.x;
    }

}
