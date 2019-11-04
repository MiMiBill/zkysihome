package com.zkys.yun.ihome.app.setting;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.app.personal.ui.PersonalFragment;
import com.zkys.yun.ihome.base.BaseFragment;

public class SettingFragmnet extends BaseFragment {


    private static SettingFragmnet sInstance;

    public static SettingFragmnet newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new SettingFragmnet();
        }
        return sInstance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void showError(String msg) {

    }
}
