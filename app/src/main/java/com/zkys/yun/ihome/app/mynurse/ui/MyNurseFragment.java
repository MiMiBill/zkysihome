package com.zkys.yun.ihome.app.mynurse.ui;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.base.BaseFragment;

public class MyNurseFragment extends BaseFragment {


    private static MyNurseFragment sInstance;

    public static MyNurseFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new MyNurseFragment();
        }
        return sInstance;
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_my_nurse;
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
