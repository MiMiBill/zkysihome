package com.zkys.yun.ihome.app.bloodsugar.presenter;

import com.sinocare.Impl.SC_BlueToothSearchCallBack;
import com.sinocare.domain.BlueToothInfo;
import com.sinocare.handler.SN_MainHandler;
import com.zkys.yun.ihome.app.bloodsugar.bean.BloodSugarBean;
import com.zkys.yun.ihome.app.bloodsugar.contract.BloodSugarContract;
import com.zkys.yun.ihome.base.BasePresenter;
import com.zkys.yun.ihome.litepal.LitePalDb;
import com.zkys.yun.ihome.util.log.LogUtil;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.List;

/**
 * Created by long yun
 * on 2019/10/25
 */
public class BloodSugarPresenter extends BasePresenter<BloodSugarContract.View> implements BloodSugarContract.Presenter{


    @Override
    public void searchBluetoothDevices(SN_MainHandler snMainHandler) {
        snMainHandler.searchBlueToothDevice(new SC_BlueToothSearchCallBack<BlueToothInfo>() {
            @Override
            public void onBlueToothSeaching(BlueToothInfo blueToothInfo) {
                mView.getBluetoothDevices(blueToothInfo);
            }
        });


    }

    @Override
    public void getBloodSugarData() {

        LitePalDb.setZkysDataDB();
        LitePal.limit(30).findAsync(BloodSugarBean.class).listen(new FindMultiCallback<BloodSugarBean>() {
            @Override
            public void onFinish(List<BloodSugarBean> list) {
//                LogFactory.l().i("list"+list.size());
                if (mView == null) {
                    LogUtil.e("mView为空");
                    return;
                }
                mView.getBloodSugarData(list);
            }
        });


    }
}
