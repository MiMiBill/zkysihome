package com.zkys.yun.ihome.app.intercourse.ui;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class InterCourseFragment extends BaseFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_name)
    TextView tvName;

    private String name;
    @Override
    public int getLayout() {
        return R.layout.fragment_intercourse;
    }


    public static InterCourseFragment newInstance(Bundle bundle)
    {
        InterCourseFragment interCourseFragment = new InterCourseFragment();
        interCourseFragment.setArguments(bundle);
        return interCourseFragment;
    }

    @Override
    public void initData() {

        Bundle bundle = getArguments();
        if (bundle != null)
        {
             name = bundle.getString("name");
            tvName.setText(name);
        }

    }

    @OnClick(R.id.ll_back)
    public void onClick()
    {
       backButtonClick();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void showError(String msg) {

    }
}
