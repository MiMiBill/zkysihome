package com.zkys.yun.ihome.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.zkys.yun.ihome.base.LauncherApplication;
import com.zkys.yun.ihome.service.clock.OneMinuteDisposable;

public class MainService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        OneMinuteDisposable.getsInstance().start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }
}
