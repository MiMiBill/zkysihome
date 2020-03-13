package com.zkys.yun.ihome.app.bodymonitoring.ui;

import android.graphics.Color;
import android.graphics.drawable.DrawableContainer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muju.note.launcher.R;
import com.zkys.yun.ihome.app.bloodpressure.ui.BloodPressureFragment;
import com.zkys.yun.ihome.app.bloodsugar.ui.BloodSugarFragment;
import com.zkys.yun.ihome.app.bodytemperature.ui.BodyTemperatureFragment;
import com.zkys.yun.ihome.app.heartrate.ui.HeartRateFragment;
import com.zkys.yun.ihome.app.home.ui.HomeFragment;
import com.zkys.yun.ihome.app.membercenter.ui.MemberCenterFragment;
import com.zkys.yun.ihome.app.mynurse.ui.MyNurseFragment;
import com.zkys.yun.ihome.app.personal.ui.PersonalFragment;
import com.zkys.yun.ihome.base.BaseFragment;

import java.io.PipedReader;

import butterknife.BindView;
import butterknife.OnClick;

public class BodyMonitoringFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.btn_body_temperature)
    Button btnBodyTemperature;
    @BindView(R.id.btn_blood_pressure)
    Button btnBloodPressure;
    @BindView(R.id.btn_blood_sugar)
    Button btnBloodSugar;
    @BindView(R.id.btn_heart_rate)
    Button btnHeartRate;
    private BaseFragment[]  fragments= new BaseFragment[4];

    private int prePosition = 0;

    private static BodyMonitoringFragment sInstance;


    public static BodyMonitoringFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new BodyMonitoringFragment();
        }
        return sInstance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_body_monitoring;
    }

    @Override
    public void initData() {
        tvTitle.setText("身体监测");
        fragments[0] = BloodPressureFragment.newInstance();
        fragments[1] = BloodSugarFragment.newInstance();
        fragments[2] = HeartRateFragment.newInstance();
        fragments[3] = BodyTemperatureFragment.newInstance();


        loadMultipleRootFragment(R.id.fl_container, 0,
                fragments[0],
                fragments[1],
                fragments[2],
                fragments[3]
                );

        clearStatus();
        defaultSelectBloodPressure();


    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void showError(String msg) {

    }


    /**
     * 默认选择血压选项
     */
    private void defaultSelectBloodPressure()
    {
        btnBloodPressure.setBackgroundResource(R.mipmap.blood_pressure);
        btnBloodPressure.setText("");
    }

    @OnClick({R.id.ll_back,R.id.btn_body_temperature,R.id.btn_blood_pressure,R.id.btn_blood_sugar,R.id.btn_heart_rate})
    public void onClick(View view)
    {
        clearStatus();
        switch (view.getId())
        {
            case R.id.ll_back :
            {
                backButtonClick();
                break;
            }
            case R.id.btn_blood_pressure :
            {
                showHideFragment(fragments[0], fragments[prePosition]);
                btnBloodPressure.setBackgroundResource(R.mipmap.blood_pressure);
                btnBloodPressure.setText("");
                prePosition = 0;
                break;
            }
            case R.id.btn_blood_sugar :
            {
                showHideFragment(fragments[1], fragments[prePosition]);
                btnBloodSugar.setBackgroundResource(R.mipmap.blood_sugar);
                btnBloodSugar.setText("");
                prePosition = 1;
                break;
            }
            case R.id.btn_heart_rate :
            {
                showHideFragment(fragments[2], fragments[prePosition]);
                btnHeartRate.setBackgroundResource(R.mipmap.heart_rate);
                btnHeartRate.setText("");
                prePosition = 2;
                break;
            }
            case R.id.btn_body_temperature :
            {
                showHideFragment(fragments[3], fragments[prePosition]);
                btnBodyTemperature.setBackgroundResource(R.mipmap.body_temparture);
                btnBodyTemperature.setText("");
                prePosition = 3;
                break;
            }
        }
    }


    private void clearStatus()
    {
        btnBloodPressure.setBackgroundColor(Color.TRANSPARENT);
        btnBloodSugar.setBackgroundColor(Color.TRANSPARENT);
        btnBodyTemperature.setBackgroundColor(Color.TRANSPARENT);
        btnHeartRate.setBackgroundColor(Color.TRANSPARENT);

        btnBloodPressure.setText("血压");
        btnBloodSugar.setText("血糖");
        btnBodyTemperature.setText("心率");
        btnHeartRate.setText("体温");
    }

}
