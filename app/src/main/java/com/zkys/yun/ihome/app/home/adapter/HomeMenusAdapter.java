package com.zkys.yun.ihome.app.home.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkys.yun.ihome.R;

import java.util.List;

public class HomeMenusAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeMenusAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.rv_msg,item);
        switch (item)
        {
            case "消息中心":{
                helper.setImageResource(R.id.img_icon,R.mipmap.message_push);
                break;
            }
            case "身体监测":{
                helper.setImageResource(R.id.img_icon,R.mipmap.body_monitoring);
                break;
            }
            case "药品助手":{
                helper.setImageResource(R.id.img_icon,R.mipmap.drug_assistant);
                break;
            }
            case "记录查询":{
                helper.setImageResource(R.id.img_icon,R.mipmap.record_query);
                break;
            }
            case "在线商城":{
                helper.setImageResource(R.id.img_icon,R.mipmap.online_shopping_mall);
                break;
            }
            case "养生咨询":{
                helper.setImageResource(R.id.img_icon,R.mipmap.health_consultation);
                break;
            }
            case "保险服务":{
                helper.setImageResource(R.id.img_icon,R.mipmap.insurance_service);
                break;
            }
            case "健康知识":{
                helper.setImageResource(R.id.img_icon,R.mipmap.health_knowledge);
                break;
            }
        }
    }
}
