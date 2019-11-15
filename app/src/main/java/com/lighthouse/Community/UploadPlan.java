package com.lighthouse.Community;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lighthouse.Plan.GeneralPlan;
import com.lighthouse.Plan.Plan;
import com.lighthouse.Plan.PlanAdapter;
import com.lighthouse.Plan.PlanConnection;
import com.lighthouse.Plan.PlanEdit;
import com.lighthouse.R;
import com.lighthouse.User.User;
import com.lighthouse.User.UserGpRelation;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class UploadPlan extends AppCompatActivity implements  AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener ,
            AdapterView.OnItemLongClickListener{
    private Spinner majorSpinner = null;
    private Spinner directionSpinner = null;
    private String userId;
    private String userName;
    private String majorContent;       //专业名称
    private String directionContent;   //方向名称
    private TextView planAuthorName;   //作者名字
    private EditText editUploadPlanName; //上传计划名称
    private EditText editUploadPlanTime; //需要的计划时长
    private String planName,planTime;
    private Button addPlanButton;        //添加子计划
    private Button confirmUploadButton;  //确认上传
    private Button cancalUploadButton;   //取消上传
    private List<Plan> planlist = new ArrayList<>();//计划列表
    private PlanAdapter planAdapter;
    private ListView planListView;
    private GeneralPlan generalPlan = new GeneralPlan();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadplan_activity);

        init();
        Connector.getDatabase();
        //loadHistoryData();

        majorSpinner.setOnItemSelectedListener(this);   //专业的选择监听
        directionSpinner.setOnItemSelectedListener(this); //方向的选择监听
        planAdapter = new PlanAdapter(UploadPlan.this, R.layout.plan_list, planlist);
        planListView.setAdapter(planAdapter);
        planListView.setOnItemClickListener(this);   //计划表的单击监听
        planListView.setOnItemLongClickListener(this); //计划表的长按监听
    }

    private void init(){ //初始化
        majorSpinner = findViewById(R.id.spin_one);
        directionSpinner = findViewById(R.id.spin_two);
        editUploadPlanName = findViewById(R.id.upload_plan_name);
        editUploadPlanTime = findViewById(R.id.upload_plan_time);
        addPlanButton = findViewById(R.id.add_plan);
        confirmUploadButton = findViewById(R.id.confrim_upload);
        cancalUploadButton = findViewById(R.id.cancal_upload);
        planListView = findViewById(R.id.up_list);
        planAuthorName = findViewById(R.id.upload_author_name);
        userId = getIntent().getStringExtra("userId");

        List<User> users = DataSupport.findAll(User.class);
        for(User user : users){
            if(user.getUserId().equals(userId)){
                userName = user.getUserName();
                break;
            }
        }

        planAuthorName.setText(userName);

        List<GeneralPlan> generalPlans= DataSupport.findAll(GeneralPlan.class);
        if(generalPlans.size() == 0){
            generalPlan.setGeneralPlanId(1);
        }else{
            generalPlan.setGeneralPlanId(generalPlans.get(generalPlans.size()-1).getGeneralPlanId()+1);
        }
    }
    public void addPlan(View v){  //添加子计划
        Intent it=new Intent(UploadPlan.this, PlanEdit.class);

        int position = planlist.size();

        it.putExtra("planId",generalPlan.getGeneralPlanId()*1000+position);
        it.putExtra("alarm","");
        it.putExtra("mainText","");

        startActivityForResult(it,position);
    }
    public void confirmUpload(View v){ //确认上传
        getInformation();
        if(TextUtils.isEmpty(planName)){
            Toast.makeText(UploadPlan.this, "请输入计划名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(planTime)){
            Toast.makeText(UploadPlan.this, "请输入计划需要时长", Toast.LENGTH_SHORT).show();
            return;
        }
        if(majorContent.equals("请选择专业")){
            Toast.makeText(UploadPlan.this, "请选择专业", Toast.LENGTH_SHORT).show();
            return;
        }
        if(directionContent.equals("请选择方向")){
            Toast.makeText(UploadPlan.this, "请选择方向", Toast.LENGTH_SHORT).show();
            return;
        }
        savePlan();

        Intent intent = new Intent();
        intent.putExtra("userId",userId);
        intent.setClass(UploadPlan.this,Community.class);
        startActivity(intent);
    }
    public void returnUpload(View v){ //返回
        deletePlan();
        Intent intent = new Intent();
        intent.putExtra("userId",userId);
        intent.setClass(UploadPlan.this,Community.class);
        startActivity(intent);
    }
    public void deletePlan(){
        for(int position = 0;position<planlist.size();position++){
            String whereArgs = String.valueOf(generalPlan.getGeneralPlanId()*1000+position); //why not position ?
            DataSupport.deleteAll(Plan.class, "planId = ?", whereArgs);
        }
    }
    public void savePlan(){
        //保存计划
        generalPlan.setAuthorName(userName);
        generalPlan.setPlanName(planName);
        generalPlan.setStudyTime(planTime);
        generalPlan.save();

        for(int i = 0 ;i < planlist.size();i++){
            PlanConnection planConnection = new PlanConnection();
            planConnection.setGeneralPlanId(generalPlan.getGeneralPlanId());
            planConnection.setPlanId(planlist.get(i).getPlanId());
            planConnection.save();
        }
        UserGpRelation record = new UserGpRelation();//保存用户与上传计划联系
        record.setUserId(userId);
        record.setCreationGeneralPlanId(generalPlan.getGeneralPlanId());
        record.save();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //单击子计划进入编辑
        Intent it=new Intent(UploadPlan.this,PlanEdit.class);

        Plan record = getPlanId(position);

        //add information into intent
        transportInformationToEdit(it, record);

        startActivityForResult(it,position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //长按子计划删除
        int n=planlist.size();
        if(position < planlist.size()-1){
            Toast.makeText(UploadPlan.this, "请删除最后一个计划或者修改当前计划", Toast.LENGTH_SHORT).show();
            return true;
        }
        planlist.remove(position);
        planAdapter.notifyDataSetChanged();

        String whereArgs = String.valueOf(generalPlan.getGeneralPlanId()*1000+position); //why not position ?
        DataSupport.deleteAll(Plan.class, "planId = ?", whereArgs);

        for(int i=position+1; i<n; i++) {
            ContentValues temp = new ContentValues();
            temp.put("planId", i-1);
            String where = String.valueOf(generalPlan.getGeneralPlanId()*1000+i);
            DataSupport.updateAll(Plan.class, temp, "planId = ?", where);
        }

        return true;
    }

    private void loadHistoryData(){//加载历史计划
        List<Plan> plans= DataSupport.findAll(Plan.class);

        for(Plan record:plans) {
            int planId = record.getPlanId();
            String planNum = record.getPlanNum();
            String planName = record.getPlanName();
            boolean alarm = record.getAlarm();
            String mainText = record.getMainText();
            Plan temp = new Plan(planId, planNum, planName,alarm, mainText);
            planlist.add(temp);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent it) {
        super.onActivityResult(requestCode, resultCode, it);
        if (resultCode == RESULT_OK) {
            updateLitePalAndList(requestCode, it);
        }
    }

    //update the database and memolist acccording to the "num" memo that Edit.class return
    private void updateLitePalAndList(int requestCode, Intent it) {
        int num=requestCode;
        int planId = it.getIntExtra("planId",0);

        String planNum = it.getStringExtra("planNum");
        String planName = it.getStringExtra("planName");
        String alarm=it.getStringExtra("alarm");
        String mainText=it.getStringExtra("mainText");

        boolean gotAlarm = alarm.length() > 1 ? true : false;
        Plan new_plan = new Plan(planId, planNum, planName,gotAlarm, mainText);

        if((requestCode+1)>planlist.size()) {
            // add a new memo record into database
            addRecordToLitePal(planId, planNum, planName,gotAlarm, mainText);

            // add a new OneMemo object into memolist and show
            planlist.add(new_plan);
        }
        else {
            //if the previous has got an alarm clock
            //cancel it first
            //update the previous "num" memo
            ContentValues temp = new ContentValues();
            temp.put("planId", planId);
            temp.put("planNum", planNum);
            temp.put("planName", planName);
            temp.put("alarm", alarm);
            temp.put("mainText", mainText);
            String where = String.valueOf(num);
            DataSupport.updateAll(Plan.class, temp, "planId = ?", where);

            planlist.set(num, new_plan);
        }
    }

    private void addRecordToLitePal(int planId,String planNum,String planName,Boolean alarm,String mainText) {
        Plan record=new Plan();
        record.setPlanId(planId);
        record.setPlanNum(planNum);
        record.setPlanName(planName);
        record.setAlarm(alarm);

        record.setMainText(mainText);
        record.save();
    }

    private Plan getPlanId(int num) {
        String whereArgs = String.valueOf(num);
        Plan record= DataSupport.where("planId = ?", whereArgs).findFirst(Plan.class);
        return record;
    }

    private void transportInformationToEdit(Intent it,Plan record) {
        it.putExtra("planId",record.getPlanId());
        it.putExtra("alarm","");
        it.putExtra("mainText",record.getMainText());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String content = parent.getItemAtPosition(position).toString();
        switch (parent.getId()){
            case R.id.spin_one:
                majorContent = content;
                break;
            case R.id.spin_two:
                directionContent = content;
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void getInformation(){
        planName = editUploadPlanName.getText().toString().trim();
        planTime = editUploadPlanTime.getText().toString().trim();
    }
}
