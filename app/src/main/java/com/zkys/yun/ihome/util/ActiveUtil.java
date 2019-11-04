package com.zkys.yun.ihome.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zkys.yun.ihome.app.startup.bean.ActiveInfo;
import com.zkys.yun.ihome.constant.Constants;
import com.zkys.yun.ihome.util.file.FileIOUtils;

/**
 * Created by long yun
 * on 2019/10/25
 * 激活设备相关信息
 */
public class ActiveUtil {

    public static void setActiveInfo(ActiveInfo info) {
        Gson gson = new Gson();
        String data = gson.toJson(info);
        data = Base64.encodeToString(data.getBytes(), Base64.DEFAULT);
        try {
            FileIOUtils.writeFileFromString(Constants.FILE_ACTIVE_INFO, data);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static String getRequestHeader() {
        ActiveInfo entity = getPadActiveInfo();
        return entity == null ? "" : entity.getToken();
    }

    public static ActiveInfo getPadActiveInfo() {
        try {
            String data = FileIOUtils.readFile2String(Constants.FILE_ACTIVE_INFO);
            if (TextUtils.isEmpty(data)) {
                return null;
            }
            data = new String(Base64.decode(data, Base64.DEFAULT), "UTF-8");
            Gson gson = new Gson();
            return gson.fromJson(data, ActiveInfo.class);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public static String getPhoneNumber() {
        String phone = "13800138000";
        ActiveInfo entity = getPadActiveInfo();
        if (entity != null) {
            String simNumber = entity.getPhoneNum();
            if (!TextUtils.isEmpty(simNumber) && simNumber.length() == 11) {
                return simNumber;
            }
        }
        Logger.d("phone:%s", phone);
        return phone;
    }

//    public static boolean hadActived(Context context) {
//        ActivePadInfo.DataBean entity = getPadActiveInfo();
//        if (entity != null) {
//            if (!TextUtils.isEmpty(MobileInfoUtil.getICCID(context))
//                    && TextUtils.equals(entity.getIccid(), MobileInfoUtil.getICCID(context))//要保证有手机卡
//                    && TextUtils.equals(UrlUtil.getHost(), entity.getHost())) {//保证域名一致
//                LogFactory.l().i("iccid==="+entity.getIccid());
//                return true;
//            }
//
//        }
//        return false;
//    }

}
