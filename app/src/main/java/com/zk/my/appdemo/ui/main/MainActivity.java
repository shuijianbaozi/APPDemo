package com.zk.my.appdemo.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zk.my.appdemo.R;
import com.zk.my.appdemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    //TODO 标题常量
    public static final String[] TITLES = {"头条","百科","咨询","经营","数据","段子","直播","天气"};
    //广告
    @BindView(R.id.ad_vp)
    ViewPager adVp;
    @BindView(R.id.ad_tv)
    TextView adTv;
    @BindView(R.id.ad_dot_layout)
    LinearLayout adDotLayout;

    @BindView(R.id.main_tab)
    TabLayout mainTab;
    //主要内容
    @BindView(R.id.tea_vp)
    ViewPager teaVp;

    //广告View的集合
    private List<View> adViews;//控件
    private MyViewAdapter adAdapter;//适配器
    private Handler adHandler = new Handler();
    private ADRunnable adRunnable;

    //主画面
    private TeaAdapter teaAdapter;//适配器
    private List<Fragment> teaFragments;//控件
    @Override
    protected void onPause() {
        super.onPause();
        //结束广告轮播
        adHandler.removeCallbacks(adRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开启轮播
        adHandler.postDelayed(adRunnable, 2000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //实例化AD控件 设置适配器
        initAD();
        //实例化主布局
        initMain();

    }

    private void initMain() {
        initMainData();
        //设置适配器
        teaVp=(ViewPager) findViewById(R.id.tea_vp);

        teaAdapter = new TeaAdapter(getSupportFragmentManager(), teaFragments, TITLES);
        teaVp.setAdapter(teaAdapter);

        //关联TabLayout
        mainTab=(TabLayout) findViewById(R.id.main_tab);
        mainTab.setupWithViewPager(teaVp);
    }

    private void initMainData() {
        teaFragments = new ArrayList<>();
        for (int i =0; i<TITLES.length; i++) {
            TeaListFragment teaListFragment = new TeaListFragment();
            //TODO tablayout和fragment传值
            Bundle bundle = new Bundle();
            bundle.putString(MyConstants.KEY_FRAGMENT_TEY, TITLES[i]);
            teaListFragment.setArguments(bundle);
            teaFragments.add(teaListFragment);
        }
    }


    private void initAD() {
        //初始化AD的子View
        initADItemView();
        //初始化广告圆点
        initDot();
        //初始化AD的ViewPager
        initADViewPager();
        //轮播
        adRunnable = new ADRunnable();
    }
    //TODO 广告轮播的任务
    class ADRunnable implements Runnable {
        @Override
        public void run() {
            int currentPosition = adVp.getCurrentItem(); //获得当前的位置
            currentPosition++;
            if (currentPosition > 2) {
                currentPosition = 0;
            }
            adVp.setCurrentItem(currentPosition);//重新设置位置
            //            adRunnable = new ADRunnable();
            adHandler.postDelayed(adRunnable, 2000);
        }
    }
    //TODO 初始化AD的ViewPager
    private void initADViewPager() {
        adVp=(ViewPager) findViewById(R.id.ad_vp);
        adAdapter = new MyViewAdapter(adViews);
        adVp.setAdapter(adAdapter);

        //ViewPager滑动监听----->设置dot的状态
        adVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int itemCount = adDotLayout.getChildCount(); //获得Layout中子View的数量
                for (int i = 0; i < itemCount; i++) {
                    View view = adDotLayout.getChildAt(i);//提取子View
                    if (i == position) {
                        view.setSelected(true);
                    } else {
                        view.setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adVp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction(); //获得动作
                switch (action) {
                    case MotionEvent.ACTION_DOWN: //按下
                        adHandler.removeCallbacks(adRunnable);
                        break;
                    case MotionEvent.ACTION_UP: //提前
                        adHandler.postDelayed(adRunnable, 2000);
                        break;
                }
                return false;//拦截
            }
        });
    }
    //TODO 初始化dot
    private void initDot() {
        adDotLayout=(LinearLayout) findViewById(R.id.ad_dot_layout);
        for (int i = 0; i < 3; i++) {
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i < 3 - 1) {
                params.rightMargin = 30;
            }
            if (i == 0) {
                iv.setSelected(true);
            }
            iv.setLayoutParams(params); //设置布局
            iv.setImageResource(R.drawable.dot); //设置图片资源
            iv.setScaleType(ImageView.ScaleType.FIT_XY); //设置填充属性
            adDotLayout.addView(iv);
        }
    }
    //TODO 初始化AD的子View
    private void initADItemView() {
        adViews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )); //设置布局
            iv.setImageResource(R.mipmap.ic_launcher); //设置图片资源
            iv.setScaleType(ImageView.ScaleType.FIT_XY); //设置填充属性
            adViews.add(iv);
        }
    }


}
