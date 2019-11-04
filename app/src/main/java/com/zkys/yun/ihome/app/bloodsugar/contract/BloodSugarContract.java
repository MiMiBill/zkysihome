package com.zkys.yun.ihome.app.bloodsugar.contract;

import com.sinocare.domain.BlueToothInfo;
import com.sinocare.handler.SN_MainHandler;
import com.zkys.yun.ihome.app.bloodsugar.bean.BloodSugarBean;
import com.zkys.yun.ihome.base.IPresenter;
import com.zkys.yun.ihome.base.IView;

import java.util.List;

/**
 * Created by long yun
 * on 2019/10/25
 */
public interface BloodSugarContract {

    interface  View extends IView
    {
        void getBluetoothDevices(BlueToothInfo blueToothInfo);
        void getBloodSugarData(List<BloodSugarBean> bloodSugarBeans);
    }

    interface  Presenter extends IPresenter<View>
    {
        void searchBluetoothDevices(SN_MainHandler snMainHandler);
        void getBloodSugarData();
    }

}
