package com.zkys.yun.ihome.util.rx;

import android.app.Dialog;

import io.reactivex.disposables.Disposable;

/**
 * Created by long yun
 * on 2019/10/31
 */
public class RxUtil {
    public static void closeDisposable(Disposable di) {
        if (di != null && !di.isDisposed()) {
            di.dispose();
        }
    }

    public static void closeDialog(Dialog dialog) {
        if (dialog != null &&dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
