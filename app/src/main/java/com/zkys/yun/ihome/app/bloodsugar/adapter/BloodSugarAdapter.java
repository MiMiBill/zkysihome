package com.zkys.yun.ihome.app.bloodsugar.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.app.bloodsugar.bean.BloodSugarBean;
import com.zkys.yun.ihome.util.DateUtil;

import java.util.List;

public class BloodSugarAdapter extends BaseQuickAdapter<BloodSugarBean, BaseViewHolder> {

    public BloodSugarAdapter(int layoutResId, @Nullable List<BloodSugarBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BloodSugarBean item) {

        String date = DateUtil.getMonthDay(item.getDate());
        helper.setText(R.id.tv_date,date);
        helper.setText(R.id.tv_before_breakfast,item.getBeforeBreakfast());
        helper.setText(R.id.tv_after_breakfast,item.getAfterBreakfast());
        helper.setText(R.id.tv_before_launch,item.getBeforeLaunch());
        helper.setText(R.id.tv_after_launch,item.getAfterLaunch());
        helper.setText(R.id.tv_before_dinner,item.getBeforeDinner());
        helper.setText(R.id.tv_after_dinner,item.getAfterDinner());
        helper.setText(R.id.tv_before_sleep,item.getBeforeSleep());
    }
}
