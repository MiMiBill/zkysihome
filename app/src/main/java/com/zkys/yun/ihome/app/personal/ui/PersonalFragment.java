package com.zkys.yun.ihome.app.personal.ui;

import com.muju.note.launcher.R;
import com.zkys.yun.ihome.app.mynurse.ui.MyNurseFragment;
import com.zkys.yun.ihome.base.BaseFragment;

public class PersonalFragment extends BaseFragment {

    private static PersonalFragment sInstance;

    public static PersonalFragment newInstance()
    {
        if (sInstance == null)
        {
            sInstance = new PersonalFragment();
        }
        return sInstance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_personal;
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
