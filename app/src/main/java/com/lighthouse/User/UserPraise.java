package com.lighthouse.User;

import org.litepal.crud.DataSupport;

public class UserPraise extends DataSupport {
    private String userId;
    private int generalPlanId;
    private boolean isPraise;
    private boolean isCollection;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getGeneralPlanId() {
        return generalPlanId;
    }

    public void setGeneralPlanId(int generalPlanId) {
        this.generalPlanId = generalPlanId;
    }

    public boolean isPraise() {
        return isPraise;
    }

    public void setPraise(boolean praise) {
        isPraise = praise;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }
}
