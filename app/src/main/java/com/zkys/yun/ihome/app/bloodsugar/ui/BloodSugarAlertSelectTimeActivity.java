package com.zkys.yun.ihome.app.bloodsugar.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.muju.note.launcher.R;
import com.zkys.yun.ihome.util.toast.FancyToast;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by long yun
 * on 2019/11/1
 */
public class BloodSugarAlertSelectTimeActivity extends Activity {


    @BindView(R.id.btn_early_morning)
    Button btnEarlyMorning;
    @BindView(R.id.btn_before_breakfast)
    Button btnBeforeBreakfast;
    @BindView(R.id.btn_before_dinner)
    Button btnBeforeDinner;
    @BindView(R.id.btn_before_lunch)
    Button btnBeforeLunch;
    @BindView(R.id.btn_before_sleep)
    Button btnBeforeSleep;
    @BindView(R.id.btn_after_dinner)
    Button btnAfterDinner;
    @BindView(R.id.btn_after_lunch)
    Button btnAfterLunch;
    @BindView(R.id.btn_after_breakfast)
    Button btnAfterBreakfast;

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_sure)
    Button btnSure;

    private int index = 0;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar_select_time);
        mUnBinder = ButterKnife.bind(this);
        clearBtnStates();
    }


    /**
     * 从1~8表示从凌晨到睡前
     */
    @OnClick({R.id.btn_early_morning
            ,R.id.btn_before_sleep
            ,R.id.btn_before_lunch
            ,R.id.btn_before_dinner
            ,R.id.btn_before_breakfast
            ,R.id.btn_after_lunch
            ,R.id.btn_after_dinner
            ,R.id.btn_after_breakfast
            ,R.id.btn_cancel
            ,R.id.btn_sure
    })
    public void onClick(Button btn)
    {

        if (!(btn.getId() == R.id.btn_cancel || btn.getId() == R.id.btn_sure))
        {
            clearBtnStates();
            btn.setBackgroundResource(R.mipmap.blood_sugar_alert_time_select);
            btn.setTextColor(Color.parseColor("#46BEEB"));
        }

        switch (btn.getId())
        {
            case R.id.btn_early_morning:
            {
                index = 1;
                break;
            }
            case R.id.btn_before_breakfast:
            {
                index = 2;
                break;
            }
            case R.id.btn_after_breakfast:
            {
                index = 3;
                break;
            }
            case R.id.btn_before_lunch:
            {
                index = 4;
                break;
            }
            case R.id.btn_after_lunch:
            {
                index = 5;
                break;
            }
            case R.id.btn_before_dinner:
            {
                index = 6;
                break;
            }
            case R.id.btn_after_dinner:
            {
                index = 7;
                break;
            }
            case R.id.btn_before_sleep:
            {
                index = 8;
                break;
            }
            case R.id.btn_cancel:
            {
                finish();
                break;
            }
            case R.id.btn_sure:
            {
                if (index == 0)
                {
                    FancyToast.makeText(this,"请选择您的测量时间!").show();
                }else {

                    if (iSetData != null)
                    {
                        iSetData.onSetData(index,true);
                    }

                    finish();
                }
                break;
            }
        }


    }


    private void clearBtnStates()
    {

        btnEarlyMorning.setTextColor(Color.parseColor("#333333"));
        btnBeforeBreakfast.setTextColor(Color.parseColor("#333333"));
        btnBeforeDinner.setTextColor(Color.parseColor("#333333"));
        btnBeforeLunch.setTextColor(Color.parseColor("#333333"));
        btnBeforeSleep.setTextColor(Color.parseColor("#333333"));
        btnAfterDinner.setTextColor(Color.parseColor("#333333"));
        btnAfterLunch.setTextColor(Color.parseColor("#333333"));
        btnAfterBreakfast.setTextColor(Color.parseColor("#333333"));

        btnEarlyMorning.setBackgroundResource(R.mipmap.blood_sugar_alert_time_unselect);
        btnBeforeBreakfast.setBackgroundResource(R.mipmap.blood_sugar_alert_time_unselect);
        btnBeforeDinner.setBackgroundResource(R.mipmap.blood_sugar_alert_time_unselect);
        btnBeforeLunch.setBackgroundResource(R.mipmap.blood_sugar_alert_time_unselect);
        btnBeforeSleep.setBackgroundResource(R.mipmap.blood_sugar_alert_time_unselect);
        btnAfterDinner.setBackgroundResource(R.mipmap.blood_sugar_alert_time_unselect);
        btnAfterLunch.setBackgroundResource(R.mipmap.blood_sugar_alert_time_unselect);
        btnAfterBreakfast.setBackgroundResource(R.mipmap.blood_sugar_alert_time_unselect);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
    public static ISetData iSetData;

    public static void  setiSetData(ISetData setData) {
        iSetData = setData;
    }

    public interface ISetData
    {
        public void onSetData(int index,boolean isSure);
    }

}
