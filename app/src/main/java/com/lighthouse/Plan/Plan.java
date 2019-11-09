package com.lighthouse.Plan;

import org.litepal.crud.DataSupport;

public class Plan extends DataSupport {
    private int planId;          //子表ID
    private String startTime;    //子阶段开始时间
    private String endTime;      //子阶段结束时间
    private Boolean alarm;        //闹钟提醒
    private int state;           //当前阶段状态： = 0 是表示未开始 ； = 1 表示正在进行；= 2 表示已完成
    private String mainText;     //阶段详细内容
    private String planName;     //阶段名称
    private String planNum;      //第几阶段

    public String getPlanNum() {
        return planNum;
    }
    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getPlanId() {
        return planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getAlarm() {
        return alarm;
    }
    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public String getMainText() {
        return mainText;
    }
    public void setMainText(String mainText) {
        this.mainText = mainText;
    }
}
