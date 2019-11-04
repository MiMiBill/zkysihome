package com.zkys.yun.ihome.base;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zkys.yun.ihome.litepal.LitePalDb;
import com.zkys.yun.ihome.okgo.OkGoUtil;
import com.zkys.yun.ihome.util.log.LogUtil;

import org.litepal.LitePal;

import java.io.PipedReader;

public class LauncherApplication extends Application {

    private static  LauncherApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //日志初始化
        LogUtil.init();
        //初始化okgo
        OkGoUtil.init(this);

        //LitePal初始化
        LitePal.initialize(this);
        LitePalDb.addDB();


    }

    public static Context getContext()
    {
        return sInstance;
    }

    public static LauncherApplication getInstance()
    {
        return sInstance;
    }
}
