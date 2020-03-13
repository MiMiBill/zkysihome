package com.zkys.yun.ihome;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.muju.note.launcher.R;
import com.zkys.yun.ihome.app.home.ui.HomeFragment;
import com.zkys.yun.ihome.app.membercenter.ui.MemberCenterFragment;
import com.zkys.yun.ihome.app.mynurse.ui.MyNurseFragment;
import com.zkys.yun.ihome.app.personal.ui.PersonalFragment;
import com.zkys.yun.ihome.app.setting.SettingFragmnet;
import com.zkys.yun.ihome.base.BaseActivity;
import com.zkys.yun.ihome.base.BaseFragment;
import com.zkys.yun.ihome.event.EventShowTab;
import com.zkys.yun.ihome.event.UpdateStatusBarEvent;
import com.zkys.yun.ihome.service.MainService;
import com.zkys.yun.ihome.util.ble.BleUtil;
import com.zkys.yun.ihome.util.log.LogUtil;
import com.zkys.yun.ihome.util.toast.FancyToast;
import com.zkys.yun.ihome.util.toast.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.btn_main)
    Button btnMain;
    @BindView(R.id.btn_member_Center)
    Button btnMemberCenter;
    @BindView(R.id.btn_my_nurse)
    Button btnMyNurse;
    @BindView(R.id.btn_personal)
    Button btnPersonal;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.ll_right_bar)
    LinearLayout llRightBar;
    @BindView(R.id.ll_left_bar)
    RelativeLayout rlLeftBar;
//    @BindView(R.id.rp_status_progress)
//    RoundnessProgressBar rpStatusProgress;

    @BindView(R.id.tv_time)
    TextView tvTime;



    private int prePosition = 0;

    private BaseFragment[]  fragments= new BaseFragment[5];
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {


        BleUtil.initSNHandler(this);

       startService( new Intent(getContext(),MainService.class));

        fragments[0] = HomeFragment.newInstance();
        fragments[1] = MemberCenterFragment.newInstance();
        fragments[2] = MyNurseFragment.newInstance();
        fragments[3] = PersonalFragment.newInstance();
        fragments[4] = SettingFragmnet.newInstance();

        loadMultipleRootFragment(R.id.fl_container, 0,
                fragments[0],
                fragments[1],
                fragments[2],
                fragments[3],
                fragments[4]);
        btnMain.setSelected(true);

        if (!EventBus.getDefault().isRegistered(this)) {//加上判断
            EventBus.getDefault().register(this);
        }
        OkGo.<String>get("").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtil.e(response.body());
            }
        });

    }

    @Override
    public void initPresenter() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateStatusBar(UpdateStatusBarEvent updateStatusBarEvent)
    {
        Log.i("UpdateStatusBarEvent","UpdateStatusBarEvent");
    }

    private void clearBtnState()
    {
         btnMain.setSelected(false);
         btnMemberCenter.setSelected(false);
         btnMyNurse.setSelected(false);
         btnPersonal.setSelected(false);
         btnSetting.setSelected(false);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        Log.i(TAG,"onKeyUp:" + keyCode);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG,"onKeyDown:" + keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.btn_personal,R.id.btn_my_nurse,R.id.btn_member_Center,R.id.btn_main,R.id.btn_setting})
    public void onClicView(View view)
    {
        clearBtnState();
        view.setSelected(true);
        switch (view.getId())
        {
            case R.id.btn_main:
            {
                showHideFragment(fragments[0], fragments[prePosition]);
                prePosition = 0;
                break;
            }
            case R.id.btn_member_Center:
            {
                showHideFragment(fragments[1], fragments[prePosition]);
                prePosition = 1;
                break;
            }
            case R.id.btn_my_nurse:
            {
                showHideFragment(fragments[2], fragments[prePosition]);
                prePosition = 2;
                break;
            }
            case R.id.btn_personal:
            {
                showHideFragment(fragments[3], fragments[prePosition]);
                prePosition = 3;
                break;
            }
            case R.id.btn_setting:
            {
                showHideFragment(fragments[4], fragments[prePosition]);
                prePosition = 4;
                break;
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showTab(EventShowTab eventShowTab)
    {
        int visib = eventShowTab.showTab ? View.VISIBLE : View.GONE;
        llRightBar.setVisibility(visib);
        rlLeftBar.setVisibility(visib);
        if (eventShowTab.showTab)
       {
           showHideFragment(fragments[prePosition]);
       }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
