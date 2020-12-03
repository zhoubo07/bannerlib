package com.testapp3.zhoubo.mytestapp3;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhoubo07.bannerlib.ConvenientBanner;
import com.zhoubo07.bannerlib.banner.BannerConfig;
import com.zhoubo07.bannerlib.banner.BannerDisplayImageHolder;
import com.zhoubo07.bannerlib.banner.BannerOptions;
import com.zhoubo07.bannerlib.banner.BannerSetUtil;
import com.zhoubo07.bannerlib.banner.SimpleImageBannerBean;
import com.zhoubo07.bannerlib.layoutmanager.ItemTransformerPageScroll;
import com.zhoubo07.bannerlib.listener.OnPageChangeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BannerActivity extends Activity {

    private ConvenientBanner banner;
    private FrameLayout flBg;
    private TextView tvBanner;
    private ArrayList<SimpleImageBannerBean> imgs;

    public static void start(Context context, int bannerType) {
        Intent starter = new Intent(context, BannerActivity.class);
        starter.putExtra("banner_type", bannerType);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);


        BannerConfig.setBannerDisplayImageHolder(new BannerDisplayImageHolder() {
            @Override
            public void disPlayImage(Context context, ImageView iv, SimpleImageBannerBean data) {
                displayImage(context, iv, data.getBannerImageUrl(), data.getBannerImageDefult(), data.getBannerImageDefult());
            }
        });

        banner = findViewById(R.id.banner);
        tvBanner = findViewById(R.id.tv_banner);
        flBg = findViewById(R.id.fl_bg);
        initData();
    }

    protected void initData() {
        imgs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SimpleImageBannerBean bannerBean = new SimpleImageBannerBean();
            bannerBean.setBannerImageUrl("https://ss3.baidu.com/-rVXeDTa2gU2pMbgoY3K/it/u=1068465475,257788198&fm=202&mola=new&crop=v1");
            if (i == 2) {
                bannerBean.setBannerType(1);

            }
            imgs.add(bannerBean);

        }


        int bannerType = getIntent().getIntExtra("banner_type", 0);
        switch (bannerType) {
            case 0://普通banner
                initGalleryBanner();
                break;
            case 1://画廊banner
                initGalleryBanner();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    /*public void setBgDrawable(int rgbColor){
        GradientDrawable drawable = new GradientDrawable();
        float[] radii = {0F,0F,0F,0F, SizeUtils.dp2px(50),SizeUtils.dp2px(50),SizeUtils.dp2px(50),SizeUtils.dp2px(50),};
        drawable.setCornerRadii(radii);
        drawable.setColor(rgbColor);
    }

    private String [] colorArr = {"#ffffffff","#00000000"};
    public void setss(final ConvenientBanner convenientBanner){
        ItemTransformer itemTransformer = new ItemTransformer() {
            @Override
            public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
                ArgbEvaluator argbEvaluator = new ArgbEvaluator();
                int position = convenientBanner.getCurrentItem();
                String endColor ;
                if (fraction>0) endColor = position==colorArr.length-1?colorArr[0]:colorArr[position+1];
                else if (fraction<0)
                    argbEvaluator.evaluate(fraction,)；
            }
        };
    }*/

    public Drawable getBgDrawable(int rgbColor) {
        GradientDrawable drawable = new GradientDrawable();
        float[] radii = {0F, 0F, 0F, 0F, dp2px(50), dp2px(50), dp2px(50), dp2px(50),};
        drawable.setCornerRadii(radii);
        drawable.setColor(rgbColor);
        return drawable;
    }


    private String[] colorArr = {"#000000", "#4e5de0", "#030102", "#D81B60", "#008577"};
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    // 画廊效果--背景渐变过度
    private void initGalleryBanner() {
        /*View insertView = LayoutInflater.from(this).inflate(R.layout.view_banner_insert, null);
        HashMap<Integer, View> map = new HashMap<>();
        map.put(1, insertView);*/


        BannerOptions bannerOptions = BannerOptions.newBuilder(banner)
                .isGallery(true)
                .isCorners(true)
                .ratio(3 / 4f)
                .leftMarginPx(10)
                /*.galleryItemTransformer(new ItemTransformerPageScroll(5) {
                    @Override
                    public void onPageScrolled(int position, float fraction, boolean isLeftWithCenter) {
                        String endColor;
                        if (isLeftWithCenter) endColor = position == colorArr.length - 1 ? colorArr[0] : colorArr[position + 1];
                        else endColor = position == 0 ? colorArr[colorArr.length - 1] : colorArr[position - 1];
                        int evaluate = (int) argbEvaluator.evaluate(fraction, Color.parseColor(colorArr[position]), Color.parseColor(endColor));
//                        flBg.setBackgroundColor(evaluate);
                        flBg.setBackground(getBgDrawable(evaluate));
                    }
                })*/
                .pageItemPaddingPx(20)
                .bannerImgBeans(imgs)
                .build();

        BannerSetUtil.setBanner(bannerOptions, null);


        banner.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }

            @Override
            public void onPageSelected(int index) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        banner.startTurning(3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.stopTurning();
    }

    public int dp2px(final float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void displayImage(Context context, ImageView imageview, String url, int placeholderPic, int errorPic) {
        if (TextUtils.isEmpty(url)) {
            if (placeholderPic > 0) {
                imageview.setImageResource(placeholderPic);
            }
            return;
        }
        RequestOptions options = new RequestOptions();
        if (placeholderPic > 0) options.placeholder(placeholderPic);
        if (errorPic > 0) options.error(errorPic);
        Glide.with(context).load(url).apply(options).into(imageview);
    }

}
