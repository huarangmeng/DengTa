package com.lighthouse.Community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lighthouse.MainActivity;
import com.lighthouse.Plan.GeneralPlan;
import com.lighthouse.Plan.Plan;
import com.lighthouse.Plan.PlanAdapter;
import com.lighthouse.Plan.PlanConnection;
import com.lighthouse.R;
import com.lighthouse.Search.Search;
import com.lighthouse.User.PersonActivity;
import com.lighthouse.User.User;
import com.lighthouse.User.UserGpRelation;
import com.lighthouse.User.UserPraise;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Community extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //具体计划还未加入
    private TextView comDirection;
    private TextView comPlanName;
    private TextView comPlanAuthor;
    private TextView comPlanTime;
    private ImageButton dianzanButton;
    private TextView dianzanNum;
    private ImageButton collectionButton;
    private TextView collectionNum;
    private ImageButton commentButton;
    private TextView commentNum;
    private List<Plan> planList = new ArrayList<>();//计划列表
    private ListView planListView;    //计划列表
    private PlanAdapter planAdapter;  //计划更新
    private Button uploadPlanButton;
    private ImageButton planButton;
    private ImageButton serButton;
    private ImageButton perButton;
    private ImageButton comButton;
    private String userId;
    private GeneralPlan generalPlan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activity);

        init();
        jumpinit();
        Connector.getDatabase();

        planListView = findViewById(R.id.com_list);
        planAdapter = new PlanAdapter(Community.this, R.layout.plan_list, planList);
        planListView.setAdapter(planAdapter);
        planListView.setOnItemClickListener(this);   //计划表的单击监听

        dianzanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDianZan();
            }
        });
        collectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCollection();
            }
        });
        uploadPlanButton.setOnClickListener(this);
    }
    private void init(){
        userId = getIntent().getStringExtra("userId");
        comDirection = findViewById(R.id.com_direction);
        comPlanName = findViewById(R.id.com_plan_name);
        comPlanAuthor = findViewById(R.id.com_plan_author);
        comPlanTime = findViewById(R.id.com_plan_time);
        dianzanButton = findViewById(R.id.dian_zan);
        commentButton = findViewById(R.id.comment);
        collectionButton = findViewById(R.id.collection);
        dianzanNum = findViewById(R.id.dian_zan_num);
        collectionNum = findViewById(R.id.collection_num);
        commentNum = findViewById(R.id.comment_num);
        uploadPlanButton = findViewById(R.id.upload_plan);

        List<GeneralPlan> generalPlans = DataSupport.findAll(GeneralPlan.class);
        generalPlan = generalPlans.get(generalPlans.size()-1); //目前为最后一个

        List<PlanConnection> planConnections = DataSupport.findAll(PlanConnection.class);
        List<Plan> plans= DataSupport.findAll(Plan.class);
        for(PlanConnection planConnection : planConnections){
            if(planConnection.getGeneralPlanId() == generalPlan.getGeneralPlanId()){
                //找到目标ID
                for(Plan record : plans) {
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
        List<UserPraise> userPraises = DataSupport.findAll(UserPraise.class);
        for(UserPraise userPraise : userPraises){
            if(userPraise.getGeneralPlanId() == generalPlan.getGeneralPlanId() && userPraise.getUserId().equals(userId)){
                if(userPraise.isPraise()){
                    dianzanButton.setBackgroundResource(R.mipmap.dian_zan_light);
                }
                if(userPraise.isCollection()){
                    collectionButton.setBackgroundResource(R.mipmap.collection_icon_light);
                }
            }
        }

        comPlanAuthor.setText(generalPlan.getAuthorName());
        comPlanName.setText(generalPlan.getPlanName());
        comPlanTime.setText(generalPlan.getStudyTime());
        dianzanNum.setText(String.valueOf(generalPlan.getPlanPraise()) + "次");
        collectionNum.setText(String.valueOf(generalPlan.getPlanCollection()) + "人");
        commentNum.setText(String.valueOf(generalPlan.getPlanComment()) + "条");
    }
    public void jumpinit(){
        planButton = findViewById(R.id.main_plan);
        serButton = findViewById(R.id.main_search);
        perButton = findViewById(R.id.main_personal);
        comButton = findViewById(R.id.main_community);

        planButton.setBackgroundResource(R.mipmap.plan_icon_dark);
        comButton.setBackgroundResource(R.mipmap.community_icon_bright);
        perButton.setBackgroundResource(R.mipmap.personal_icon_dark);
        serButton.setBackgroundResource(R.mipmap.search_icon_dark);

        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(Community.this, MainActivity.class);
                startActivity(intent);
            }
        });
        serButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(Community.this, Search.class);
                startActivity(intent);
            }
        });
        perButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(Community.this, PersonActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.comment:
                break;
            case R.id.upload_plan:
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(Community.this, UploadPlan.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    private void isDianZan(){
        if(relationUP()){
            List<UserPraise> userPraises = DataSupport.findAll(UserPraise.class);
            for(UserPraise userPraise : userPraises){
                if(userPraise.getGeneralPlanId() == generalPlan.getGeneralPlanId() && userPraise.getUserId().equals(userId)){
                    if(userPraise.isPraise()){
                        dianzanButton.setBackgroundResource(R.mipmap.dian_zan_dark);
                        GeneralPlan record = new GeneralPlan();
                        record.setPlanPraise(generalPlan.getPlanPraise()-1);
                        record.updateAll("generalPlanId = ?",String.valueOf(generalPlan.getGeneralPlanId()));
                        UserPraise record2 = new UserPraise();
                        record2.setPraise(false);
                        record2.updateAll("userId = ? and generalPlanId = ?",userId,String.valueOf(generalPlan.getGeneralPlanId()));
                        dianzanNum.setText(String.valueOf(generalPlan.getPlanPraise()) + "次");
                    }else{
                        dianzanButton.setBackgroundResource(R.mipmap.dian_zan_light);
                        GeneralPlan record = new GeneralPlan();
                        record.setPlanPraise(generalPlan.getPlanPraise()+1);
                        record.updateAll("generalPlanId = ?",String.valueOf(generalPlan.getGeneralPlanId()));
                        UserPraise record2 = new UserPraise();
                        record2.setPraise(true);
                        record2.updateAll("userId = ? and generalPlanId = ?",userId,String.valueOf(generalPlan.getGeneralPlanId()));
                        dianzanNum.setText(String.valueOf(generalPlan.getPlanPraise()+1) + "次");
                    }
                }
            }
        }
    }
    private void isCollection(){
        if(relationUP()){
            List<UserPraise> userPraises = DataSupport.findAll(UserPraise.class);
            for(UserPraise userPraise : userPraises){
                if(userPraise.getGeneralPlanId() == generalPlan.getGeneralPlanId() && userPraise.getUserId().equals(userId)){
                    if(userPraise.isCollection()){
                        collectionButton.setBackgroundResource(R.mipmap.collection_icon_dark);
                        GeneralPlan record = new GeneralPlan();
                        record.setPlanCollection(generalPlan.getPlanPraise());
                        record.updateAll("generalPlanId = ?",String.valueOf(generalPlan.getGeneralPlanId()));
                        UserPraise record2 = new UserPraise();
                        record2.setCollection(false);
                        record2.updateAll("userId = ? and generalPlanId = ?",userId,String.valueOf(generalPlan.getGeneralPlanId()));
                        collectionNum.setText(String.valueOf(generalPlan.getPlanCollection()-1) + "人");
                    }else{
                        collectionButton.setBackgroundResource(R.mipmap.collection_icon_light);
                        GeneralPlan record = new GeneralPlan();
                        record.setPlanCollection(generalPlan.getPlanPraise()+1);
                        record.updateAll("generalPlanId = ?",String.valueOf(generalPlan.getGeneralPlanId()));
                        UserPraise record2 = new UserPraise();
                        record2.setCollection(true);
                        record2.updateAll("userId = ? and generalPlanId = ?",userId,String.valueOf(generalPlan.getGeneralPlanId()));
                        collectionNum.setText(String.valueOf(generalPlan.getPlanCollection()+1) + "人");
                    }
                }
            }
        }
    }
    private boolean relationUP(){
        List<UserPraise> userPraises = DataSupport.findAll(UserPraise.class);
        for(UserPraise userPraise : userPraises){
            if(userPraise.getGeneralPlanId() == generalPlan.getGeneralPlanId() && userPraise.getUserId().equals(userId)){
                return true;
            }
        }
        UserPraise record = new UserPraise();
        record.setGeneralPlanId(generalPlan.getGeneralPlanId());
        record.setUserId(userId);
        record.save();
        return true;
    }
}
