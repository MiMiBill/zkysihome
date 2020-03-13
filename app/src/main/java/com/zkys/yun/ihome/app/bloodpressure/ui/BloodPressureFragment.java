package com.zkys.yun.ihome.app.bloodpressure.ui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sinocare.Impl.SC_BlueToothSearchCallBack;
import com.sinocare.domain.BlueToothInfo;
import com.muju.note.launcher.R;
import com.zkys.yun.ihome.app.bloodpressure.bean.BloodPressureDeviceInfo;
import com.zkys.yun.ihome.app.bloodpressure.device.BPMManager;
import com.zkys.yun.ihome.app.bloodpressure.device.BPMManagerCallbacks;
import com.zkys.yun.ihome.app.mynurse.ui.MyNurseFragment;
import com.zkys.yun.ihome.base.BaseFragment;
import com.zkys.yun.ihome.base.LauncherApplication;
import com.zkys.yun.ihome.util.ble.BleUtil;
import com.zkys.yun.ihome.util.log.LogUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class BloodPressureFragment extends BaseFragment implements BPMManagerCallbacks {




    private static BloodPressureFragment sInstance;
    private BPMManager bpmManager;

    public static BloodPressureFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new BloodPressureFragment();
        }
        return sInstance;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_blood_pressure;
    }

    @Override
    public void initData() {



//        initBPMManager();
//        BloodPressureDeviceInfo.getInstance().setName("Yuwell BP-YE650A");
//        BloodPressureDeviceInfo.getInstance().setMac("C0:18:06:01:00:1E");
//        BleUtil.getSnMainHandler().searchBlueToothDevice(new SC_BlueToothSearchCallBack<BlueToothInfo>() {
//            @Override
//            public void onBlueToothSeaching(BlueToothInfo blueToothInfo) {
//                LogUtil.d("蓝牙名称：" + blueToothInfo.getDevice().getName() + "  mac:" + blueToothInfo.getDevice().getAddress());
//                if (blueToothInfo.getDevice().getAddress().equalsIgnoreCase(BloodPressureDeviceInfo.getInstance().getMac()))
//                {
//                    BleUtil.getSnMainHandler().cancelSearch();
//                    bpmManager.connect(blueToothInfo.getDevice())
//                            .useAutoConnect(shouldAutoConnect())
//                            .retry(3, 100)
//                            .enqueue();
//                }
//            }
//        });
    }

    protected boolean shouldAutoConnect() {
        return true;
    }

    private void initBPMManager()
    {
        bpmManager = BPMManager.getBPMManager(LauncherApplication.getContext());
        bpmManager.setGattCallbacks(this);
    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void showError(String msg) {
        LogUtil.d("showError");
    }

    @Override
    public void onDeviceConnecting(@NonNull BluetoothDevice device) {
        LogUtil.d("onDeviceConnecting");
    }

    @Override
    public void onDeviceConnected(@NonNull BluetoothDevice device) {
        LogUtil.d("onDeviceConnected");
    }

    @Override
    public void onDeviceDisconnecting(@NonNull BluetoothDevice device) {
        LogUtil.d("onDeviceDisconnecting");
    }

    @Override
    public void onDeviceDisconnected(@NonNull BluetoothDevice device) {
        LogUtil.d("onDeviceDisconnected");
    }

    @Override
    public void onLinkLossOccurred(@NonNull BluetoothDevice device) {
        LogUtil.d("onLinkLossOccurred");
    }

    @Override
    public void onServicesDiscovered(@NonNull BluetoothDevice device, boolean optionalServicesFound) {
        LogUtil.d("onServicesDiscovered");
    }

    @Override
    public void onDeviceReady(@NonNull BluetoothDevice device) {
        LogUtil.d("onDeviceReady");
    }

    @Override
    public void onBondingRequired(@NonNull BluetoothDevice device) {
        LogUtil.d("onBondingRequired");
    }

    @Override
    public void onBonded(@NonNull BluetoothDevice device) {
        LogUtil.d("onBonded");
    }

    @Override
    public void onBondingFailed(@NonNull BluetoothDevice device) {
        LogUtil.d("onBondingFailed");
    }

    @Override
    public void onError(@NonNull BluetoothDevice device, @NonNull String message, int errorCode) {
        LogUtil.d("onError");
    }

    @Override
    public void onDeviceNotSupported(@NonNull BluetoothDevice device) {
        LogUtil.d("onDeviceNotSupported");
    }

    @Override
    public void onBatteryLevelChanged(@NonNull BluetoothDevice device, int batteryLevel) {
        LogUtil.d("onBatteryLevelChanged");
    }

    @Override
    public void onBloodPressureMeasurementReceived(@NonNull BluetoothDevice device, float systolic, float diastolic, float meanArterialPressure, int unit, @Nullable Float pulseRate, @Nullable Integer userID, @Nullable BPMStatus status, @Nullable Calendar calendar) {
        LogUtil.d("onBloodPressureMeasurementReceived");
    }

    @Override
    public void onIntermediateCuffPressureReceived(@NonNull BluetoothDevice device, float cuffPressure, int unit, @Nullable Float pulseRate, @Nullable Integer userID, @Nullable BPMStatus status, @Nullable Calendar calendar) {
        LogUtil.d("onIntermediateCuffPressureReceived");
    }
}
