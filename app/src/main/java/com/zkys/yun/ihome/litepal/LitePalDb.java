package com.zkys.yun.ihome.litepal;


import com.zkys.yun.ihome.app.bloodsugar.bean.BloodSugarBean;
import com.zkys.yun.ihome.app.startup.bean.ActiveInfo;

import org.litepal.LitePal;
import org.litepal.LitePalDB;

/**
 * Created by long yun
 * on 2019/10/25
 */
public class LitePalDb {

    public static final LitePalDB zkysDataDb = new LitePalDB("zkys-data",1);
    public static final LitePalDB zkysDb = new LitePalDB("zkys",1);

    public static final String DBNAME_ZKYS_DATA = "/sdcard/zkysihome/zkys-data.db";
    public static final String DBNAME_ZKYS = "/sdcard/zkysihome/zkys.db";

    public static  void addDB()
    {
        zkysDataDb.setStorage("zkysihome");
        zkysDataDb.addClassName(ActiveInfo.class.getName());
        zkysDataDb.addClassName(BloodSugarBean.class.getName());
        LitePal.use(zkysDataDb);
    }

    /**
     * 切换成统计的数据库
     */
    public static void setZkysDataDB()
    {
        LitePal.use(zkysDataDb);
    }
    public static void setZkysData()
    {
        LitePal.use(zkysDb);
    }
}
