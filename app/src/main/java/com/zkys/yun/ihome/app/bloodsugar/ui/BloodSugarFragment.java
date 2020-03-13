package com.zkys.yun.ihome.app.bloodsugar.ui;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.sinocare.Impl.SC_BlueToothCallBack;
import com.sinocare.Impl.SC_CurrentDataCallBack;
import com.sinocare.Impl.SC_ModifyCodeSetCmdCallBack;
import com.sinocare.Impl.SC_TimeSetCmdCallBack;
import com.sinocare.domain.BloodSugarData;
import com.sinocare.domain.BlueToothInfo;
import com.sinocare.handler.SN_MainHandler;
import com.sinocare.protocols.ProtocolVersion;
import com.sinocare.status.SC_DataStatusUpdate;
import com.sinocare.status.SC_ErrorStatus;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.app.bloodsugar.adapter.BloodSugarAdapter;
import com.zkys.yun.ihome.app.bloodsugar.bean.BloodSugarBean;
import com.zkys.yun.ihome.app.bloodsugar.bean.BloodSugarDeviceInfo;
import com.zkys.yun.ihome.app.bloodsugar.contract.BloodSugarContract;
import com.zkys.yun.ihome.app.bloodsugar.event.BloodSugarAlertSelectTimeEvent;
import com.zkys.yun.ihome.app.bloodsugar.presenter.BloodSugarPresenter;
import com.zkys.yun.ihome.app.home.adapter.HomeMenusAdapter;
import com.zkys.yun.ihome.app.mynurse.ui.MyNurseFragment;
import com.zkys.yun.ihome.base.BaseFragment;
import com.zkys.yun.ihome.base.LauncherApplication;
import com.zkys.yun.ihome.litepal.LitePalDb;
import com.zkys.yun.ihome.util.DateUtil;
import com.zkys.yun.ihome.util.ble.BleUtil;
import com.zkys.yun.ihome.util.log.LogUtil;
import com.zkys.yun.ihome.util.rx.RxUtil;
import com.zkys.yun.ihome.util.toast.FancyToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BloodSugarFragment extends BaseFragment<BloodSugarPresenter> implements BloodSugarContract.View, BloodSugarAlertSelectTimeActivity.ISetData {

    private static final int MSG_CONNECT_SUCCESS  = 1; //成功
    private static final int MSG_CONNECTING  = 2;//连接中
    private static final int MSG_CONNECT_FAIL  = 3;//连接失败
    private static final int MSG_CONNECT_SHUT_DOWN  = 4;//已经关机
    private static final int MSG_CONNECT_DISPLAY_DATA  = 5;//显示数据

    private int deviceStatus = 0;

    @BindView(R.id.btn_connect_blood_sugar)
    Button btnConnectBloodSugar;
    @BindView(R.id.iv_connect_bg)
    ImageView imgConnectBg;
    @BindView(R.id.btn_set_verification_code)
    Button btnSetVerificationCode;
    @BindView(R.id.btn_replace_the_binding)
    Button btnReplaceTheBinding;
    @BindView(R.id.tv_sn_num)
    TextView tvSnNum;
    @BindView(R.id.im_display_data_bg)
    ImageView imDisplayDataBg;
    @BindView(R.id.tv_blood_sugar_unit)
    TextView tvBloodSugarUnit;

    @BindView(R.id.rv_blood_sugar_data_form)
    RecyclerView rvBloodSugarDataForm;

    private BloodSugarAdapter bloodSugarAdapter;
    private boolean isFirst = true;

    private LinkedList<BloodSugarBean> bloodSugarBeans = new LinkedList<>();

    private Animation roateAnimation;
    private Disposable disposable;

    private static BloodSugarFragment sInstance;
    private SN_MainHandler snMainHandler = null;

    //最后一次测量出来的值
    private String curBloodSugar;

    private void setConnectSuccessUI()
    {
        deviceStatus = MSG_CONNECT_SUCCESS;
        if (curBloodSugar != null)
        {
            deviceStatus = MSG_CONNECT_DISPLAY_DATA;
            setBloodSugarDataUI(curBloodSugar);
            return;
        }
        if (btnConnectBloodSugar == null)return;
        btnConnectBloodSugar.setText("连接成功");
        btnConnectBloodSugar.setTextSize(30);
        btnConnectBloodSugar.setTextColor(Color.parseColor("#38DBB0"));
        btnConnectBloodSugar.setEnabled(false);

        tvBloodSugarUnit.setVisibility(View.GONE);
        imDisplayDataBg.setVisibility(View.GONE);

        snMainHandler.cancelSearch();
        RxUtil.closeDisposable(disposable);
        imgConnectBg.clearAnimation();
        imgConnectBg.setVisibility(View.VISIBLE);
    }


    private void setConnectingUI()
    {
        deviceStatus = MSG_CONNECTING;
        if (btnConnectBloodSugar == null)return;
        btnConnectBloodSugar.setText("正在连接...");
        btnConnectBloodSugar.setTextSize(30);
        btnConnectBloodSugar.setTextColor(Color.parseColor("#46BEEB"));
        btnConnectBloodSugar.setClickable(false);
        imgConnectBg.setVisibility(View.VISIBLE);
        imgConnectBg.startAnimation(roateAnimation);
        tvBloodSugarUnit.setVisibility(View.GONE);
        imDisplayDataBg.setVisibility(View.GONE);
    }

    private void setErrMsgToConnectBtnUI(String msg)
    {
        if (msg.equalsIgnoreCase("设备已关机"))
        {
            deviceStatus = MSG_CONNECT_SHUT_DOWN;
        }else {
            deviceStatus = MSG_CONNECT_FAIL;
        }
        if (btnConnectBloodSugar == null)return;
        if (deviceStatus == MSG_CONNECT_SHUT_DOWN)
        {
            btnConnectBloodSugar.setClickable(false);
        }else {
            btnConnectBloodSugar.setClickable(true);
        }
        btnConnectBloodSugar.setText(msg);
        btnConnectBloodSugar.setTextSize(28);
        btnConnectBloodSugar.setTextColor(Color.parseColor("#FF5157"));

        tvBloodSugarUnit.setVisibility(View.GONE);
        imDisplayDataBg.setVisibility(View.GONE);
        imgConnectBg.clearAnimation();
        imgConnectBg.setVisibility(View.VISIBLE);
        snMainHandler.cancelSearch();
        RxUtil.closeDisposable(disposable);
    }

    private void setBloodSugarDataUI(String data)
    {
        deviceStatus = MSG_CONNECT_DISPLAY_DATA;
        if (btnConnectBloodSugar == null)return;
        btnConnectBloodSugar.setText(data);
        btnConnectBloodSugar.setTextSize(30);
        btnConnectBloodSugar.setTextColor(Color.parseColor("#46BEEB"));
        btnConnectBloodSugar.setClickable(false);
        imgConnectBg.setVisibility(View.VISIBLE);
        imgConnectBg.clearAnimation();
        tvBloodSugarUnit.setVisibility(View.VISIBLE);
        imDisplayDataBg.setVisibility(View.VISIBLE);
    }

    public static BloodSugarFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new BloodSugarFragment();
        }
        return sInstance;
    }


    private void initForm()
    {
//        LitePalDb.setZkysDataDB();
//        LitePal.deleteAll(BloodSugarBean.class);
//        for (int i =0;i < 1 ; i ++)
//        {
//            BloodSugarBean bloodSugarBean =  new BloodSugarBean(DateUtil.getCurYearMonthDay(),"14.2","13.2","11.2","15.2","11.2","11.4","11.2","5.6");
//            bloodSugarBean.save();
//        }

        bloodSugarAdapter = new BloodSugarAdapter(R.layout.item_blood_sugar_data,bloodSugarBeans);
        rvBloodSugarDataForm.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBloodSugarDataForm.setAdapter(bloodSugarAdapter);
        rvBloodSugarDataForm.setHasFixedSize(true);
        rvBloodSugarDataForm.setNestedScrollingEnabled(false);
        mPresenter.getBloodSugarData();

    }



    @Override
    public int getLayout() {
        return R.layout.fragment_blood_sugar;
    }

    @Override
    public void initData() {
        LogUtil.i("initData");

        String [] macs = {"C0:15:83:CD:FD:20"};//,"00:15:83:CD:FD:20"
        BloodSugarDeviceInfo.getsInstance()
                .setFactory("三诺")
                .setModel("WL-1")
                .setSn("1GJ8ER00505")
                .setMacList(Arrays.asList(macs))
        ;
        initForm();
        snMainHandler = BleUtil.getSnMainHandler();
//        if (isFirst)
//        {
//            initSNHandle();
//        }

    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

        if (deviceStatus == MSG_CONNECT_SUCCESS)
        {
            setConnectSuccessUI();

        }else if (deviceStatus == MSG_CONNECT_DISPLAY_DATA){
            setBloodSugarDataUI(curBloodSugar);
        }else if (deviceStatus == MSG_CONNECT_SHUT_DOWN)
        {
            setErrMsgToConnectBtnUI("设备已关机");
        }else if (deviceStatus == MSG_CONNECTING)
        {
            setConnectingUI();
        }else {
            setErrMsgToConnectBtnUI("连接失败");
        }

        LogUtil.d("onSupportVisible");
        if (getActivity() != null)
        {
            getActivity().registerReceiver(mBtReceiver, makeIntentFilter());
        }
        if (isFirst){
            isFirst = false;
            startConnectBloodSugarDevice();
        }



    }


    //正在连接设备
    private void startConnectBloodSugarDevice()
    {
        if (snMainHandler == null)return;

       btnConnectBloodSugar.setClickable(false);
       //1.开始动画
       roateAnimation = AnimationUtils.loadAnimation(LauncherApplication.getContext(),R.anim.cycler_rotate);
       imgConnectBg.startAnimation(roateAnimation);
       //2.开始搜索设备
//        handler.obtainMessage(MSG_CONNECTING).sendToTarget();
        setConnectingUI();
       initReceiveBloodSugarDataListener(snMainHandler);
       mPresenter.searchBluetoothDevices(snMainHandler);
       disposable =  Observable.timer(10, TimeUnit.SECONDS)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<Long>() {
                   @Override
                   public void accept(Long aLong) throws Exception {
                       setErrMsgToConnectBtnUI("连接失败");
                       deviceStatus = MSG_CONNECT_FAIL;
                   }
               });





    }




    private void stopConnectBloodSugarDevice()
    {
        //1.停止动画
//        imgConnectBg.clearAnimation();
        //2.停止搜索设备

        if (snMainHandler != null)
        {
            snMainHandler.cancelSearch();
        }


    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        LogUtil.d("onSupportInvisible");
        if (getActivity() != null)
        {
            getActivity().unregisterReceiver(mBtReceiver);
        }

        stopConnectBloodSugarDevice();


    }


//    private void initSNHandle()
//    {
//        if (snMainHandler != null)return;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            AndPermission.with(getActivity())
//                    .permission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE)
//                    .onGranted(new Action() {
//                        @Override
//                        public void onAction(List<String> permissions) {
//                            snMainHandler = SN_MainHandler.getBlueToothInstance(LauncherApplication.getContext());
//                        }
//                    }).onDenied(new Action() {
//                @Override
//                public void onAction(List<String> permissions) {
//                    Toast.makeText(getActivity(), "请先允许用户权限", Toast.LENGTH_SHORT).show();
//                }
//            }).start();
//        }else {
//            snMainHandler = SN_MainHandler.getBlueToothInstance(LauncherApplication.getContext());
//        }
//
//    }











    @Override
    public void initPresenter() {
        mPresenter = new BloodSugarPresenter();
    }

    @Override
    public void showError(String msg) {

    }




    @OnClick({R.id.btn_connect_blood_sugar,R.id.btn_replace_the_binding,R.id.btn_set_verification_code})
    public void onClickView(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_connect_blood_sugar:
            {
                startConnectBloodSugarDevice();
                break;
            }
            case R.id.btn_replace_the_binding:
            {
                FancyToast.makeText(LauncherApplication.getContext(),"切换绑定").show();
                break;
            }
            case R.id.btn_set_verification_code:
            {
                FancyToast.makeText(LauncherApplication.getContext(),"输入校验码").show();
//                BloodSugarAlertSelectTimeActivity.setiSetData(BloodSugarFragment.this);
//                startActivity(new Intent(getActivity(),BloodSugarAlertSelectTimeActivity.class));
                break;
            }
        }

    }




    @Override
    public void getBluetoothDevices(BlueToothInfo blueToothInfo) {
       LogUtil.d("蓝牙名称：" +  blueToothInfo.getName()  + " mac:" + blueToothInfo.getDevice().getAddress());

       //如果搜索到了和设备配套的蓝牙设备，那么直接连接

        String mac = blueToothInfo.getDevice().getAddress();
        if (BloodSugarDeviceInfo.getsInstance().getMacList().contains(mac))
        {
            LogUtil.d("血糖仪开始连接！");
            snMainHandler.connectBlueTooth(blueToothInfo.getDevice(), new SC_BlueToothCallBack() {
                @Override
                public void onConnectFeedBack(int result) {
                    if(result == 16){
                        LogUtil.d("血糖仪连接成功！");
                        BloodSugarDeviceInfo.getsInstance().setBluetoothDevice(blueToothInfo.getDevice());
                        setConnectSuccessUI();


                    }else {
                        LogUtil.d("血糖仪连接失败！" + Thread.currentThread());
                        setErrMsgToConnectBtnUI("连接失败");

                    }
                }
            }, ProtocolVersion.WL_1);


        }

    }

    @Override
    public void getBloodSugarData(List<BloodSugarBean> bloodSugarBeans) {
            LogUtil.d(Thread.currentThread());

            if (bloodSugarBeans == null || bloodSugarBeans.size() == 0)
            {
                LogUtil.d("血糖数据为空");
            }else {
                this.bloodSugarBeans.clear();
                this.bloodSugarBeans.addAll(bloodSugarBeans);
                Collections.reverse(this.bloodSugarBeans);
                bloodSugarAdapter.notifyDataSetChanged();
            }

    }






    private void initReceiveBloodSugarDataListener(SN_MainHandler handler)
    {
        handler.registerReceiveBloodSugarData(new SC_CurrentDataCallBack<BloodSugarData>() {
            @Override
            public void onStatusChange(int status) {
                // TODO Auto-generated method stub
                if (status == SC_DataStatusUpdate.SC_BLOOD_FFLASH) {
                    LogUtil.d("请插入试条测试！");
                    deviceStatus = MSG_CONNECT_SUCCESS;
                    setConnectSuccessUI();
//                    list.add(new DeviceListItem("请插入试条测试！", false));
                } else if (status == SC_DataStatusUpdate.SC_MC_TESTING) {
                    LogUtil.d("正在测试，请稍后");

//                    list.add(new DeviceListItem("正在测试，请稍后！", false));
                } else if (status == SC_DataStatusUpdate.SC_MC_SHUTTINGDOWN) {
//                    list.add(new DeviceListItem("正在关机！", false));
                    LogUtil.d("正在关机");
                    FancyToast.makeText(getActivity(),"正在关机");
                } else if (status == SC_DataStatusUpdate.SC_MC_SHUTDOWN) {
                    LogUtil.d("已关机");

                    FancyToast.makeText(LauncherApplication.getContext(),"已关机");
                    setErrMsgToConnectBtnUI("设备已关机");
//                    handler.obtainMessage(MSG_DEVICE_HAS_SHUT_DOWN,"设备已关机").sendToTarget();
//                    handler.obtainMessage(MSG_CONNECT_FAIL,"设备已关机").sendToTarget();
                }

            }

            @Override
            public void onReceiveSyncData(BloodSugarData datas) {
                float v = datas.getBloodSugarValue();
                Date date = datas.getCreatTime();
                LogUtil.d("onReceiveSyncData 测得值：" + v + " 时间：" + date);
            }

            @Override
            public void onReceiveSucess(BloodSugarData datas) {
                // TODO Auto-generated method stub
                float v = datas.getBloodSugarValue();
                curBloodSugar = "" + v;
                Date date = datas.getCreatTime();
                LogUtil.d("onReceiveSucess 测得值：" + v + " 时间：" + date);
                setBloodSugarDataUI(curBloodSugar);
                BloodSugarAlertSelectTimeActivity.setiSetData(BloodSugarFragment.this);
                startActivity(new Intent(getActivity(),BloodSugarAlertSelectTimeActivity.class));

            }
        });
    }

    public void setModifyCode(String code) {
        //调整校验码
        snMainHandler.modifyCode(Byte.parseByte(code), new SC_ModifyCodeSetCmdCallBack() {
            @Override
            public void onModifyCodeCmdFeedback(byte cModifyCode) {
                LogUtil.d("设置成功，当前校验码为" + cModifyCode);
            }
        });
    }

    public void setDeviceTime(Date date) {
        //设置设备时间
        snMainHandler.setMCTime(date, new SC_TimeSetCmdCallBack() {
            @Override
            public void onTimeSetCmdFeedback(Date date) {
                LogUtil.d("设置时间成功：" + date);
            }
        });
    }

    //广播监听SDK ACTION
    private final BroadcastReceiver mBtReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (SN_MainHandler.ACTION_SN_CONNECTION_STATE_CHANGED.equals(action)) {
                if (snMainHandler.isUnSupport()) {
                    LogUtil.d("手机设备不支持低功耗蓝牙，无法连接血糖仪");
                } else if (snMainHandler.isConnected()) {
                    LogUtil.d("蓝牙已经连接");
//                    setConnectSuccessUI();
                } else if (snMainHandler.isIdleState() || snMainHandler.isDisconnecting()) {
//                    LogUtil.d("设备已断开");
//                    handler.obtainMessage(MSG_BLUETOOTH_HAS_DISCONNECT,"设备已断开").sendToTarget();
                }
            } else if (SN_MainHandler.ACTION_SN_ERROR_STATE.equals(action)) {
                Bundle bundle = intent.getExtras();
                int errorStatus = bundle.getInt(SN_MainHandler.EXTRA_ERROR_STATUS);
                if (errorStatus == SC_ErrorStatus.SC_OVER_RANGED_TEMPERATURE) {
                    LogUtil.d("错误码：E-2");
                } else if (errorStatus == SC_ErrorStatus.SC_AUTH_ERROR) {
                    LogUtil.d("错误：认证失败！");
                } else if (errorStatus == SC_ErrorStatus.SC_ERROR_OPERATE) {
                    LogUtil.d("错误码：E-3！");
                } else if (errorStatus == SC_ErrorStatus.SC_ERROR_FACTORY) {
                    LogUtil.d("错误码：E-6！");
                } else if (errorStatus == SC_ErrorStatus.SC_ABLOVE_MAX_VALUE) {
                    LogUtil.d("错误码：HI");
                    setBloodSugarDataUI("浓度太高");
                } else if (errorStatus == SC_ErrorStatus.SC_BELOW_LEAST_VALUE) {
                    LogUtil.d("错误码：LO");
                    setBloodSugarDataUI("浓度太低");
                } else if (errorStatus == SC_ErrorStatus.SC_LOW_POWER) {
                    LogUtil.d("错误码：E-1！");
                } else if (errorStatus == SC_ErrorStatus.SC_UNDEFINED_ERROR) {
                    LogUtil.d("未知错误！");
                } else if (errorStatus == 6) {
                    LogUtil.d("E-6");
                }
            } else if (SN_MainHandler.ACTION_SN_MC_STATE.equals(action)) {
                Bundle bundle = intent.getExtras();
                int MCStatus = bundle.getInt(SN_MainHandler.EXTRA_MC_STATUS);
            }
        }
    };

    private IntentFilter makeIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SN_MainHandler.ACTION_SN_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(SN_MainHandler.ACTION_SN_ERROR_STATE);
        intentFilter.addAction(SN_MainHandler.ACTION_SN_MC_STATE);
        return intentFilter;
    }

    @Override
    public void onSetData(int index, boolean isSure) {

        LogUtil.d("onSetData:" + index + " isSure:" + isSure);
        if (isSure)
        {
            BloodSugarBean bloodSugarBean = null;
            if (bloodSugarBeans.size() > 0)
            {
                bloodSugarBean = bloodSugarBeans.getFirst();
                if (bloodSugarBean.getDate() != DateUtil.getCurYearMonthDay())
                {
                    bloodSugarBean = new BloodSugarBean();
                    bloodSugarBean.setDate(DateUtil.getCurYearMonthDay());
                    bloodSugarBeans.addFirst(bloodSugarBean);
                }
            }else {
                bloodSugarBean = new BloodSugarBean();
                bloodSugarBean.setDate(DateUtil.getCurYearMonthDay());
                bloodSugarBeans.add(bloodSugarBean);
            }

            switch (index)
            {

                case 1:
                {
                    bloodSugarBean.setEarlyMorning(curBloodSugar);
                    break;
                }
                case 2:
                {
                    bloodSugarBean.setBeforeBreakfast(curBloodSugar);
                    break;
                }
                case 3:
                {
                    bloodSugarBean.setAfterBreakfast(curBloodSugar);
                    break;
                }
                case 4:
                {
                    bloodSugarBean.setBeforeLaunch(curBloodSugar);
                    break;
                }
                case 5:
                {
                    bloodSugarBean.setAfterLaunch(curBloodSugar);
                    break;
                }
                case 6:
                {
                    bloodSugarBean.setBeforeDinner(curBloodSugar);
                    break;
                }
                case 7:
                {
                    bloodSugarBean.setAfterDinner(curBloodSugar);
                    break;
                }
                case 8:
                {
                    bloodSugarBean.setBeforeSleep(curBloodSugar);
                    break;
                }

            }
//            bloodSugarBean.updateAll("date=" + bloodSugarBean.getDate());
            bloodSugarBean.save();
            bloodSugarAdapter.notifyDataSetChanged();

        }

    }
}
