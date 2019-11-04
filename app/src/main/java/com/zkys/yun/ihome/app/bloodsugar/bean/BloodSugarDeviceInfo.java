package com.zkys.yun.ihome.app.bloodsugar.bean;

import android.bluetooth.BluetoothDevice;
import android.graphics.PixelFormat;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.interfaces.PBEKey;

/**
 * Created by long yun
 * on 2019/10/30
 */
public class BloodSugarDeviceInfo {

    private static BloodSugarDeviceInfo sInstance;

    public static BloodSugarDeviceInfo getsInstance()
    {
        if (sInstance == null)
        {
            sInstance = new BloodSugarDeviceInfo();
        }
        return sInstance;
    }


    private BloodSugarDeviceInfo()
    {

    }

    private String factory;
    private String model;
    private String sn;
    private List<String> macList;
    private BluetoothDevice bluetoothDevice;

    public List<String> getMacList() {
        return macList;
    }

    public BloodSugarDeviceInfo setMacList(List<String> macList) {
        this.macList = macList;
        return this;
    }

    public String getFactory() {
        return factory;
    }

    public BloodSugarDeviceInfo setFactory(String factory) {
        this.factory = factory;
        return this;
    }

    public String getModel() {
        return model;
    }

    public BloodSugarDeviceInfo setModel(String model) {
        this.model = model;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public BloodSugarDeviceInfo setSn(String sn) {
        this.sn = sn;
        return this;
    }



    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public BloodSugarDeviceInfo setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
        return this;
    }
}
