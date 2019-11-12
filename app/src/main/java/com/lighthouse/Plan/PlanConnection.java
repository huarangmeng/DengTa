package com.lighthouse.Plan;

import org.litepal.crud.DataSupport;

public class PlanConnection extends DataSupport {
    private int generalPlanId;
    private int planId;

    public int getGeneralPlanId() {
        return generalPlanId;
    }

    public void setGeneralPlanId(int generalPlanId) {
        this.generalPlanId = generalPlanId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
