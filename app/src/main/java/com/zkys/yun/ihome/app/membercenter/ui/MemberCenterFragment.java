package com.zkys.yun.ihome.app.membercenter.ui;

import com.zkys.yun.ihome.R;

import com.zkys.yun.ihome.base.BaseFragment;

public class MemberCenterFragment extends BaseFragment {
    private static MemberCenterFragment sInstance;

    public static MemberCenterFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new MemberCenterFragment();
        }
        return sInstance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_member_center;
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
