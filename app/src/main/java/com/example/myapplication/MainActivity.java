package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GeneralPlan generalPlan = new GeneralPlan();
    Plan[] array = new Plan[]{};
    private List<Plan> planList = new ArrayList<>(Arrays.asList(array));//计划列表
    private TextView textPlanName;
    private TextView textPlanAuthor;
    private ListView planListView;
    private PlanAdapter planAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        textPlanName = findViewById(R.id.general_plan_name);
        textPlanAuthor = findViewById(R.id.plan_author);

        textPlanName.setText("BY " + generalPlan.getPlanName());
        textPlanAuthor.setText(generalPlan.getAuthorName());

        planListView = findViewById(R.id.list);
        planAdapter=new PlanAdapter(MainActivity.this, R.layout.plan_list, planList);
        planListView.setAdapter(planAdapter);
    }

    public void init(){
        for(int i = 0;i<4;i++){
            Plan plan = new Plan();
            plan.setState(0);
            plan.setPlanNum("第"+i+"阶段");
            plan.setPlanName("J");
            plan.setMainText("第"+i+"阶段内容");
            plan.setStartTime("1.0");
            plan.setEndTime("2.0");
            plan.setPlanId(i);
            plan.setAlarm(false);
            planList.add(plan);
        }
        generalPlan.setAuthorName("Holo");
        generalPlan.setPlanName("JAVA");
        generalPlan.setPlanList(planList);
    }
}
