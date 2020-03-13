package com.zkys.yun.ihome.util.toast;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zkys.yun.ihome.R;
import com.zkys.yun.ihome.base.LauncherApplication;


public class FancyToast extends Toast {
    public static int SUCCESS = 1;
    public static int WARNING = 2;
    public static int ERROR = 3;
    public static int INFO = 4;
    public static int DEFAULT = 5;
    public static int CONFUSING = 6;
    private static Toast sToast;

    public FancyToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, String message) {
        return makeText(context, message, Toast.LENGTH_SHORT);
    }

    public static Toast makeText(Context context, String message, int duration) {
        return makeText(context, message, duration, FancyToast.DEFAULT, true);
    }

    public static Toast makeText(Context context, String message, int duration, int type) {
        return makeText(context, message, duration, type, true);
    }

    public static Toast makeText(Context context, String message, int duration, int type, boolean androidicon) {
        if (sToast == null)
        {
            if (context == null)
            {
                context = LauncherApplication.getContext();
            }
            sToast = new Toast(context);
        }
        sToast.setDuration(duration);
        View layout = LayoutInflater.from(context).inflate(R.layout.fancytoast_layout, (ViewGroup) null, false);
        TextView l1 = (TextView) layout.findViewById(R.id.toast_text);
        LinearLayout linearLayout = (LinearLayout) layout.findViewById(R.id.toast_type);
        ImageView img = (ImageView) layout.findViewById(R.id.toast_icon);
        ImageView img1 = (ImageView) layout.findViewById(R.id.imageView4);
        l1.setText(message);
        if (androidicon) {
            img1.setVisibility(View.VISIBLE);
        } else {
            img1.setVisibility(View.GONE);
        }
        sToast.setView(layout);
        return sToast;
    }

    public static Toast makeText(Context context, String message, int duration, int type, int ImageResource) {
        Toast toast = new Toast(context);
        View layout = LayoutInflater.from(context).inflate(R.layout.fancytoast_layout, (ViewGroup) null, false);
        TextView l1 = (TextView) layout.findViewById(R.id.toast_text);
        LinearLayout linearLayout = (LinearLayout) layout.findViewById(R.id.toast_type);
        ImageView img = (ImageView) layout.findViewById(R.id.toast_icon);
        l1.setText(message);
        linearLayout.setBackgroundResource(R.drawable.success_shape);
        toast.setView(layout);
        return toast;
    }

}
