package com.zkys.yun.ihome.app.bloodpressure.bean;

import android.bluetooth.BluetoothDevice;


/**
 * Created by long yun
 * on 2019/11/6
 */
public class BloodPressureDeviceInfo {

    private String name;
    private String mac;
    private BluetoothDevice bluetoothDevice;

    private static BloodPressureDeviceInfo bloodPressureDeviceInfo;
    public static BloodPressureDeviceInfo getInstance()
    {
        if (bloodPressureDeviceInfo == null)
        {
            bloodPressureDeviceInfo = new BloodPressureDeviceInfo();
        }
        return bloodPressureDeviceInfo;
    }

    private BloodPressureDeviceInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }
}
