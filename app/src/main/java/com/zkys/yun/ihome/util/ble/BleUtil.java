package com.zkys.yun.ihome.util.ble;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.widget.Toast;

import com.sinocare.handler.SN_MainHandler;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.zkys.yun.ihome.base.LauncherApplication;

import java.util.List;

/**
 * Created by long yun
 * on 2019/11/6
 */
public class BleUtil {

    private static SN_MainHandler snMainHandler;

    public static void initSNHandler(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
            AndPermission.with(activity)
                    .permission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE)
                    .onGranted(new Action() {
                        @Override
                        public void onAction(List<String> permissions) {
                            snMainHandler = SN_MainHandler.getBlueToothInstance(LauncherApplication.getContext());
                        }
                    }).onDenied(new Action() {
                @Override
                public void onAction(List<String> permissions) {
                    Toast.makeText(LauncherApplication.getContext(), "请先允许用户权限", Toast.LENGTH_SHORT).show();
                }
            }).start();
        }else {
            snMainHandler = SN_MainHandler.getBlueToothInstance(LauncherApplication.getContext());
        }
    }

    public static SN_MainHandler getSnMainHandler()
    {
        return snMainHandler;
    }

}
