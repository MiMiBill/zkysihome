package com.zkys.yun.ihome.app.bloodpressure.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.app.mynurse.ui.MyNurseFragment;
import com.zkys.yun.ihome.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class BloodPressureFragment extends BaseFragment {



    private static BloodPressureFragment sInstance;

    public static BloodPressureFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new BloodPressureFragment();
        }
        return sInstance;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_blood_pressure;
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
