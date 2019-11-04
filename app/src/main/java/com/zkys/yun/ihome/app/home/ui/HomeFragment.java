package com.zkys.yun.ihome.app.home.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.app.bodymonitoring.ui.BodyMonitoringFragment;
import com.zkys.yun.ihome.app.home.adapter.HomeMenusAdapter;
import com.zkys.yun.ihome.app.home.holder.LocalImageHolderView;
import com.zkys.yun.ihome.app.home.imageloader.GlideImageLoader;
import com.zkys.yun.ihome.app.intercourse.ui.InterCourseFragment;
import com.zkys.yun.ihome.app.messagecenter.ui.MessageCenterFragment;
import com.zkys.yun.ihome.base.BaseFragment;
import java.util.ArrayList;
import butterknife.BindView;


public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.banner)
    ConvenientBanner banner;



    private ArrayList<String> menus = new ArrayList<>();
    private HomeMenusAdapter homeMenusAdapter;
    private static HomeFragment sInstance;
    private ArrayList<String> imageUrls = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    public static HomeFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new HomeFragment();
        }
        return sInstance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        initMenus();
        initBanner();
    }

    private void initBanner() {

       imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569501511437&di=5779eb647d4ac237ac360f35a228d640&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201401%2F21%2F155537y0e0377jrjbowq7b.jpg");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569501511438&di=29ac76d2d354c6bf8e9f576ab40ace04&imgtype=0&src=http%3A%2F%2Fimg.aiimg.com%2Fuploads%2Fallimg%2F140315%2F263915-140315194H9.jpg11");
        imageUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569501511438&di=8d6d423a5e9eeb9de46d6ddc8463fa2a&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F4%2F54096b4f7211e.jpg11");
        titles.add("名字1");
        titles.add("名字2");
        titles.add("名字3");
        //设置banner样式
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//        //设置图片加载器
//        banner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        banner.setImages(imageUrls);
//        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage);
//        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
//        //设置自动轮播，默认为true
//        banner.isAutoPlay(true);
//        //设置轮播时间
//        banner.setDelayTime(2500);
//        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CENTER);
//        //banner设置方法全部调用完毕时最后调用
//        banner.start();


        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        banner.setPages(
                new CBViewHolderCreator() {

                    @Override
                    public Holder createHolder(View itemView) {
                        return new LocalImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.banner_content;
                    }
                }, imageUrls).startTurning();
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
//                .setOnItemClickListener(this);
        //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
        ;


    }

    private void initMenus() {


        menus.add("身体监测");
        menus.add("药品助手");
        menus.add("记录查询");
        menus.add("在线商城");
        menus.add("消息中心");
        menus.add("养生咨询");
        menus.add("健康知识");
        menus.add("保险服务");

        homeMenusAdapter = new HomeMenusAdapter(R.layout.rv_item_home_menu,menus);
        rvMenu.setLayoutManager( new GridLayoutManager(getContext(),4));
        rvMenu.setAdapter(homeMenusAdapter);
        rvMenu.setHasFixedSize(true);
        rvMenu.setNestedScrollingEnabled(false);
        homeMenusAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                menuClick(menus.get(position));
            }
        });
    }

    //跳转到制定的页面
    private void menuClick(String s) {


        switch (s) {
            case "消息中心": {
                start(new MessageCenterFragment());
                break;
            }
            case "身体监测": {
                start(new BodyMonitoringFragment());
                break;
            }
            default:{
                Bundle bundle = new Bundle();
                bundle.putString("name",s);
                start(InterCourseFragment.newInstance(bundle));
            }
        }


    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void showError(String msg) {

    }
}
