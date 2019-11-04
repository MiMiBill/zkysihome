package com.zkys.yun.ihome.app.bloodsugar.bean;

import org.litepal.crud.LitePalSupport;

/**
 * Created by long yun
 * on 2019/11/2
 */
public class BloodSugarBean extends LitePalSupport
{
    private long date;
    private String earlyMorning;
    private String beforeBreakfast;
    private String afterBreakfast;
    private String beforeLaunch;
    private String afterLaunch;
    private String beforeDinner;
    private String afterDinner;
    private String beforeSleep;

    public BloodSugarBean(long date, String earlyMorning, String beforeBreakfast, String afterBreakfast, String beforeLaunch, String afterLaunch, String beforeDinner, String afterDinner, String beforeSleep) {
        this.date = date;
        this.earlyMorning = earlyMorning;
        this.beforeBreakfast = beforeBreakfast;
        this.afterBreakfast = afterBreakfast;
        this.beforeLaunch = beforeLaunch;
        this.afterLaunch = afterLaunch;
        this.beforeDinner = beforeDinner;
        this.afterDinner = afterDinner;
        this.beforeSleep = beforeSleep;
    }

    public BloodSugarBean() {

    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getEarlyMorning() {
        return earlyMorning;
    }

    public void setEarlyMorning(String earlyMorning) {
        this.earlyMorning = earlyMorning;
    }

    public String getBeforeBreakfast() {
        return beforeBreakfast;
    }

    public void setBeforeBreakfast(String beforeBreakfast) {
        this.beforeBreakfast = beforeBreakfast;
    }

    public String getAfterBreakfast() {
        return afterBreakfast;
    }

    public void setAfterBreakfast(String afterBreakfast) {
        this.afterBreakfast = afterBreakfast;
    }

    public String getBeforeLaunch() {
        return beforeLaunch;
    }

    public void setBeforeLaunch(String beforeLaunch) {
        this.beforeLaunch = beforeLaunch;
    }

    public String getAfterLaunch() {
        return afterLaunch;
    }

    public void setAfterLaunch(String afterLaunch) {
        this.afterLaunch = afterLaunch;
    }

    public String getBeforeDinner() {
        return beforeDinner;
    }

    public void setBeforeDinner(String beforeDinner) {
        this.beforeDinner = beforeDinner;
    }

    public String getAfterDinner() {
        return afterDinner;
    }

    public void setAfterDinner(String afterDinner) {
        this.afterDinner = afterDinner;
    }

    public String getBeforeSleep() {
        return beforeSleep;
    }

    public void setBeforeSleep(String beforeSleep) {
        this.beforeSleep = beforeSleep;
    }
}
