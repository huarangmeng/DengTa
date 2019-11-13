package com.lighthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import com.lighthouse.Plan.PlanConnection;
import com.lighthouse.Plan.PlanEdit;
import com.lighthouse.Search.Search;
import com.lighthouse.User.PersonActivity;
import com.lighthouse.User.User;
import com.lighthouse.User.UserGpRelation;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {
    //计划不能点击，闹钟没有实现
    private GeneralPlan generalPlan = new GeneralPlan();
    private Plan[] array = new Plan[]{};
    private List<Plan> planList = new ArrayList<>(Arrays.asList(array));//计划列表
    private int nowPlanId;
    private TextView textPlanName;    //计划名称
    private TextView textPlanAuthor;  //作者
    private ListView planListView;    //计划列表
    private PlanAdapter planAdapter;  //计划更新
    private TextView textMainTime;
    private String userId;
    private String mainTime;          //时间
    private ImageButton comButton;
    private ImageButton serButton;
    private ImageButton perButton;
    private ImageButton planButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        jumpinit();

        planListView = findViewById(R.id.list);
        planAdapter=new PlanAdapter(MainActivity.this, R.layout.plan_list, planList);
        planListView.setAdapter(planAdapter);
        planListView.setOnItemClickListener(this);
        planListView.setOnItemLongClickListener(this);

        loadHistoryData();
    }

    private void init(){
        userId = getIntent().getStringExtra("userId");
        List<User> users = DataSupport.findAll(User.class);
        for(User user : users){
            if(user.getUserId() == userId){
                nowPlanId = user.getNowPlanId();
                break;
            }
        }
        nowPlanId = 2;//测试
        List<GeneralPlan> generalPlans = DataSupport.findAll(GeneralPlan.class);
        for(GeneralPlan temp :generalPlans){
            if(temp.getGeneralPlanId() == nowPlanId){
                generalPlan = temp;
                break;
            }
        }

        textPlanName = findViewById(R.id.general_plan_name);
        textPlanAuthor = findViewById(R.id.plan_author);
        textMainTime = findViewById(R.id.main_time);

        textPlanName.setText(generalPlan.getPlanName());
        textPlanAuthor.setText("BY " + generalPlan.getAuthorName());
        mainTime = getNowTime();
        textMainTime.setText(mainTime);

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
                intent.putExtra("userId",userId);
                intent.setClass(MainActivity.this, Community.class);
                startActivity(intent);
            }
        });
        serButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(MainActivity.this, Search.class);
                startActivity(intent);
            }
        });
        perButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(MainActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadHistoryData(){//加载历史计划
        List<PlanConnection> planConnections = DataSupport.findAll(PlanConnection.class);

        for(PlanConnection planConnection : planConnections){//遍历总表与子表的关系表
            if(planConnection.getGeneralPlanId() == nowPlanId){//当关系表的ID为当前计划ID时
                List<Plan> plans= DataSupport.findAll(Plan.class);

                for(Plan record:plans) {//遍历子表，把子表加入到ListView中
                    if(record.getPlanId() == planConnection.getPlanId()){
                        int planId = record.getPlanId();
                        String planNum = record.getPlanNum();
                        String planName = record.getPlanName();
                        boolean alarm = record.getAlarm();
                        String mainText = record.getMainText();
                        Plan temp = new Plan(planId, planNum, planName,alarm, mainText);
                        planList.add(temp);
                    }
                }
            }
        }
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