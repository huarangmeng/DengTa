package com.lighthouse.User;

public class UserGpRelation {//用户与所创建的学习表的关系
    private String userId;
    private int creationGeneralPlanId;

    public int getCreationGeneralPlanId() {
        return creationGeneralPlanId;
    }

    public void setCreationGeneralPlanId(int creationGeneralPlanId) {
        this.creationGeneralPlanId = creationGeneralPlanId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
