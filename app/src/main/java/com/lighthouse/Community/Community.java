package com.lighthouse.Community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lighthouse.MainActivity;
import com.lighthouse.Plan.GeneralPlan;
import com.lighthouse.Plan.Plan;
import com.lighthouse.Plan.PlanAdapter;
import com.lighthouse.R;
import com.lighthouse.Search.Search;
import com.lighthouse.User.PersonActivity;
import com.lighthouse.User.User;
import com.lighthouse.User.UserGpRelation;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Community extends Activity implements View.OnClickListener {
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

        dianzanButton.setOnClickListener(this);
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

        int genPlanId = 0;
        List<UserGpRelation> gpRelations = DataSupport.findAll(UserGpRelation.class);
        for(UserGpRelation temp : gpRelations){
            if(temp.getUserId() == userId){
                genPlanId = temp.getCreationGeneralPlanId();
                break;
            }
        }
        if(genPlanId != 0){
            List<GeneralPlan> generalPlans = DataSupport.findAll(GeneralPlan.class);
            for(GeneralPlan temp : generalPlans){
                if(temp.getGeneralPlanId() == genPlanId){
                    generalPlan = temp;
                    break;
                }
            }
        }
        comPlanAuthor.setText(generalPlan.getAuthorName());
        comPlanName.setText(generalPlan.getPlanName());
        comPlanTime.setText(generalPlan.getStudyTime());
        dianzanNum.setText(generalPlan.getPlanPraise() + "次");
        collectionNum.setText(generalPlan.getPlanCollection() + "人");
        commentNum.setText(generalPlan.getPlanComment() + "条");
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
            case R.id.dian_zan:
                dianzanButton.setBackgroundResource(R.mipmap.dian_zan_light);
                //数据库+1更新
                break;
            case R.id.collection:
                collectionButton.setBackgroundResource(R.mipmap.collection_icon_light);
                //数据库+1更新
                break;
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
}
