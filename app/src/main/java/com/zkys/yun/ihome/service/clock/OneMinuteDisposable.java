package com.zkys.yun.ihome.service.clock;

import android.util.Log;

import com.zkys.yun.ihome.event.UpdateStatusBarEvent;
import com.zkys.yun.ihome.util.log.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class OneMinuteDisposable {

    private static OneMinuteDisposable sInstances = new OneMinuteDisposable();
    public static OneMinuteDisposable  getsInstance()
    {
        return sInstances;
    }


    public void start()
    {
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.get(Calendar.SECOND);
        Observable.timer(60 - currentTime,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Observable.interval(1,TimeUnit.MINUTES)
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        EventBus.getDefault().post(new UpdateStatusBarEvent());
                                    }
                                });

                    }
                });


    }


}
