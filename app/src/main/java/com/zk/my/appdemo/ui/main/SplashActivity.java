package com.zk.my.appdemo.ui.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.zk.my.appdemo.R;
import com.zk.my.appdemo.base.BaseActivity;

public class SplashActivity extends BaseActivity {
    //闪屏停留时间---->这一段时间好好利用 可以加载网络数据
    public static final long SLEEP_TIME = 2000;

    //是否进入主画面key
    public static final String KEY_GOTO_MAIN = "KEY_GOTO_MAIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //TODO 闪屏同时开启异步任务
        new LoadTask().execute();
    }
    //闪屏的加载异步任务--->
    class LoadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            //获得起始时间
            long startTime = System.currentTimeMillis();

            //网络加载的操作----->

            //获得结束时间
            long endTime = System.currentTimeMillis();
            //获得加载销毁的数据
            long loadTime = endTime - startTime;
            if (loadTime < SLEEP_TIME) {
                try {
                    Thread.sleep(SLEEP_TIME - loadTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //是否进入主画面
            boolean isGotoMain = PreUtils.readBoolean(SplashActivity.this, KEY_GOTO_MAIN);
            Intent intent = new Intent();
            if (isGotoMain) {
                //进入主画面
                intent.setClass(SplashActivity.this, MainActivity.class);
            } else {
                //进入引导画面
                intent.setClass(SplashActivity.this, GuideActivity.class);
                //TODO 开关可以先关闭测试
                //PreUtils.writeBoolean(SplashActivity.this, KEY_GOTO_MAIN, true);
            }
            startActivity(intent);
            finish();
        }
    }
}
