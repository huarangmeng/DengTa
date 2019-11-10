package com.lighthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lighthouse.Community.Community;
import com.lighthouse.Plan.GeneralPlan;
import com.lighthouse.Plan.Plan;
import com.lighthouse.Plan.PlanAdapter;
import com.lighthouse.Plan.PlanEdit;
import com.lighthouse.Search.Search;
import com.lighthouse.User.PersonActivity;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {
    private GeneralPlan generalPlan = new GeneralPlan();
    private Plan[] array = new Plan[]{};
    private List<Plan> planList = new ArrayList<>(Arrays.asList(array));//计划列表
    private TextView textPlanName;    //计划名称
    private TextView textPlanAuthor;  //作者
    private ListView planListView;    //计划列表
    private PlanAdapter planAdapter;  //计划更新
    private TextView textMainTime;
    private String mainTime;          //时间
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
        textMainTime = findViewById(R.id.main_time);

        textPlanName.setText(generalPlan.getPlanName());
        textPlanAuthor.setText("BY " + generalPlan.getAuthorName());
        textMainTime.setText(mainTime);

        planListView = findViewById(R.id.list);
        planAdapter=new PlanAdapter(MainActivity.this, R.layout.plan_list, planList);
        planListView.setAdapter(planAdapter);
        planListView.setOnItemClickListener(this);
        planListView.setOnItemLongClickListener(this);
    }

    private void init(){
        for(int i = 0;i<3;i++){
            Plan plan = new Plan();
            if(i == 0){
                plan.setState(2);
                plan.setMainText(" ");
                plan.setPlanNum("第"+i+1+"阶段");
                plan.setPlanName("JAVA基础课程");
            }
            else if(i == 1){
                plan.setState(1);
                plan.setMainText("JavaWeb课程内容涉及: HTML5课程、CSS3. JavaScript MySQL使用、JDBC连接池、Servlet. JSP、 AJAX、 jQuery、 Bootstrap." +
                        "2.1第一部分: HTML51. html概述" +
                        "2. htm|基本标签3.图片标签4.超链接标签");
                plan.setPlanNum("第"+i+1+"阶段");
                plan.setPlanName("JAVA Web");
            }else{
                plan.setState(0);
                plan.setMainText(" ");
                plan.setPlanNum("第"+i+1+"阶段");
                plan.setPlanName("JAVA 框架课程");
            }
            plan.setStartTime("1.0");
            plan.setEndTime("2.0");
            plan.setPlanId(i);
            plan.setAlarm(false);
            planList.add(plan);
        }
        generalPlan.setAuthorName("Holo");
        generalPlan.setPlanName("JAVA课程学习");
        generalPlan.setPlanList(planList);

        mainTime = getNowTime();
    }
    private void jumpinit(){
        planButton = findViewById(R.id.main_plan);
        serButton = findViewById(R.id.main_search);
        perButton = findViewById(R.id.main_personal);
        comButton = findViewById(R.id.main_community);

        planButton.setBackgroundResource(R.mipmap.plan_icon_bright);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it=new Intent(MainActivity.this, PlanEdit.class);

        Plan record=getPlanWithNum(position);

        //add information into intent
        transportInformationToEdit(it, record);

        startActivityForResult(it,position);
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){

        return true;
    }
    private void transportInformationToEdit(Intent it,Plan record) {
        it.putExtra("planId",record.getPlanId());
        it.putExtra("alarm",record.getAlarm());
        it.putExtra("mainText",record.getMainText());
    }
    private Plan getPlanWithNum(int num) {
        String whereArgs = String.valueOf(num);
        Plan record= DataSupport.where("num = ?", whereArgs).findFirst(Plan.class);
        return record;
    }
    public String getNowTime(){
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");//2019/11/10

        SimpleDateFormat format2 = new SimpleDateFormat("EEEE");      //Sunday

        return (format1.format(date)+"  "+format2.format(date));
    }
}