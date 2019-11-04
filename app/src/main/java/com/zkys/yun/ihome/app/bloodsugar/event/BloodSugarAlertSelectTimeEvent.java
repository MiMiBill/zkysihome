package com.zkys.yun.ihome.app.bloodsugar.event;

/**
 * Created by long yun
 * on 2019/11/2
 */
public class BloodSugarAlertSelectTimeEvent {
    /**
     * 从1~8表示从凌晨到睡前
     */
    public int index;
    public boolean isSure;

    public BloodSugarAlertSelectTimeEvent(int index, boolean isSure) {
        this.index = index;
        this.isSure = isSure;
    }
}
