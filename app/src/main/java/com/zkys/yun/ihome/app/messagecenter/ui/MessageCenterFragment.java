package com.zkys.yun.ihome.app.messagecenter.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.app.mynurse.ui.MyNurseFragment;
import com.zkys.yun.ihome.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageCenterFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_back)
    LinearLayout llBack;

    private static MessageCenterFragment sInstance;

    public static MessageCenterFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new MessageCenterFragment();
        }
        return sInstance;
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view)
    {
        backButtonClick();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_message_center;
    }

    @Override
    public void initData() {
        tvTitle.setText("消息中心");
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void showError(String msg) {

    }
}
