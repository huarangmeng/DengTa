package com.example.myapplication;

import java.util.List;

public class GeneralPlan {
    private int generalPlanId; //总表ID
    private String authorName; //作者名字
    private int collegeId;     //学院ID
    private int majorId;       //专业ID
    private int directionId;   //方向ID
    private String planName;   //计划名称
    private String studyTime;  //学习时长
    private List<Plan> planList;//计划列表
    private int planViews;      //浏览数
    private int planPraise;     //点赞数

    public int getPlanViews() {
        return planViews;
    }
    public void setPlanViews(int planViews) {
        this.planViews = planViews;
    }

    public int getPlanPraise() {
        return planPraise;
    }
    public void setPlanPraise(int planPraise) {
        this.planPraise = planPraise;
    }

    public List<Plan> getPlanList() {
        return planList;
    }
    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    public int getMajorId() {
        return majorId;
    }
    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getDirectionId() {
        return directionId;
    }
    public void setDirectionId(int directionId) {
        this.directionId = directionId;
    }

    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getStudyTime() {
        return studyTime;
    }
    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public int getGeneralPlanId() {
        return generalPlanId;
    }
    public void setGeneralPlanId(int generalPlanId) {
        this.generalPlanId = generalPlanId;
    }


    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public int getCollegeId() {
        return collegeId;
    }
    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }


}
