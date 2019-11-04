package com.zkys.yun.ihome.app.heartrate.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.app.mynurse.ui.MyNurseFragment;
import com.zkys.yun.ihome.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class HeartRateFragment extends BaseFragment {



    private static HeartRateFragment sInstance;

    public static HeartRateFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new HeartRateFragment();
        }
        return sInstance;
    }



    @Override
    public int getLayout() {
        return R.layout.fragment_heart_rate;
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
