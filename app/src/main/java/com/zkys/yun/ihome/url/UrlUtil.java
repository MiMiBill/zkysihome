package com.zkys.yun.ihome.url;

/**
 * Created by long yun
 * on 2019/10/25
 */
public class UrlUtil {
    public static enum Enum_VERSION{
        Production //生产环境
        ,Development//开发环境
        ,Test       //测试环境
    }

    public static Enum_VERSION  currentVersion = Enum_VERSION.Test;

    public static String getHost() {

        if (currentVersion == Enum_VERSION.Production)//正式版本
        {
            return "http://pad.zgzkys.com";
        }else if (currentVersion == Enum_VERSION.Development){//开发版本
            return "http://192.168.0.103:8086";
        }else {//测试版本
            return "http://pad.test.zgzkys.com";
        }
    }

    /**
     * 获取平板的激活状态
     * @return
     */
    public static String getPadActiveStatus()
    {
        return getHost() + "/pad/activeStatus";
    }


}



