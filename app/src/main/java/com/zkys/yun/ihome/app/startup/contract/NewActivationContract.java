package com.zkys.yun.ihome.app.startup.contract;

import com.zkys.yun.ihome.base.IPresenter;
import com.zkys.yun.ihome.base.IView;

/**
 * Created by long yun
 * on 2019/10/25
 */
public interface NewActivationContract {

    interface  View extends IView
    {
        void bindSuccess(String body);
        void bindFail();
    }

    interface  Presenter extends IPresenter<View>
    {
        void bindingDevice(String iccid);
    }

}
