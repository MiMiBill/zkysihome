package com.zkys.yun.ihome.constant;

import android.os.Environment;

import java.io.File;

/**
 * Created by long yun
 * on 2019/10/25
 */
public class Constants {

    /**
     *  激活文件
     */
    public static final String FILE_ACTIVE_INFO = Environment.getExternalStorageDirectory().toString() + File.separator + "zkys" + File.separator + "active.info";
}
