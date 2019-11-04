package com.zkys.yun.ihome.app.bodytemperature.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.app.mynurse.ui.MyNurseFragment;
import com.zkys.yun.ihome.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class BodyTemperatureFragment extends BaseFragment {




    private static BodyTemperatureFragment sInstance;

    public static BodyTemperatureFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new BodyTemperatureFragment();
        }
        return sInstance;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_body_temperature;
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
