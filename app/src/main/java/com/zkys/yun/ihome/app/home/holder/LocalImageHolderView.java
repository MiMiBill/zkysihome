package com.zkys.yun.ihome.app.home.holder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.base.LauncherApplication;

public class LocalImageHolderView  extends Holder<String> {

    private static final String TAG = LocalImageHolderView.class.getSimpleName();

    private ImageView imageView;

    public LocalImageHolderView(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.banner_image);
    }

    @Override
    protected void initView(View itemView) {
    }

    @Override
    public void updateUI(String data) {
        imageView.setImageResource(R.mipmap.banner);
//        Glide.with(imageView.getContext()).load(data).placeholder(R.mipmap.banner).into(imageView);
    }
}
