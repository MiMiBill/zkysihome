package com.zkys.yun.ihome.app.startup.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lzy.okgo.OkGo;
import com.zkys.yun.ihome.MainActivity;
import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.app.startup.contract.NewActivationContract;
import com.zkys.yun.ihome.app.startup.presenter.NewActivationPresenter;
import com.zkys.yun.ihome.base.BaseActivity;
import com.zkys.yun.ihome.util.log.LogUtil;
import com.zkys.yun.ihome.util.net.NetWorkUtil;
import com.zkys.yun.ihome.util.phone.PhoneInfoUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by long yun
 * on 2019/10/25
 */
public class NewActivationActivity extends BaseActivity <NewActivationPresenter> implements NewActivationContract.View {


    @BindView(R.id.tv_info)
    TextView tvInfo;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);




        }
    };

    @Override
    public int getLayout() {

        return R.layout.activity_new_activation;
    }

    @Override
    public void initData() {

        Intent intent = new Intent();
        intent.setClass(getContext(), MainActivity.class);
        startActivity(intent);
        mPresenter.bindingDevice(PhoneInfoUtil.getIMEI(getContext()));
//        mPresenter.bindingDevice("");


    }


    @Override
    public void initPresenter() {
        mPresenter = new NewActivationPresenter();
    }

    private void checkNetWork()
    {

        if (NetWorkUtil.isConnected(getContext()))
        {
            tvInfo.setText("网络已经连接");
        }else {
            tvInfo.setText("网络已经未连接");
            Observable.timer(20, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            checkNetWork();
                        }
                    });
        }



    }

    @Override
    public void bindSuccess(String body) {

    }

    @Override
    public void bindFail() {

    }
}
