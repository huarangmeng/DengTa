package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.Community.Community;
import com.example.myapplication.Search.Search;
import com.example.myapplication.User.PersonActivity;

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
    private ImageButton comButton;
    private ImageButton serButton;
    private ImageButton perButton;
    private ImageButton planButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jumpinit();
        init();

        textPlanName = findViewById(R.id.general_plan_name);
        textPlanAuthor = findViewById(R.id.plan_author);

        textPlanName.setText(generalPlan.getPlanName());
        textPlanAuthor.setText("BY " + generalPlan.getAuthorName());

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
    public void jumpinit(){
        planButton = findViewById(R.id.main_plan);
        serButton = findViewById(R.id.main_search);
        perButton = findViewById(R.id.main_personal);
        comButton = findViewById(R.id.main_community);

        planButton.setBackgroundResource(R.mipmap.personal_icon_bright);
        comButton.setBackgroundResource(R.mipmap.community_icon_dark);
        perButton.setBackgroundResource(R.mipmap.personal_icon_dark);
        serButton.setBackgroundResource(R.mipmap.search_icon_dark);

        comButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Community.class);
                startActivity(intent);
            }
        });
        serButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Search.class);
                startActivity(intent);
            }
        });
        perButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });
    }
}
