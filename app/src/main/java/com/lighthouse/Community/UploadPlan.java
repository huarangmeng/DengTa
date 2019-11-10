package com.lighthouse.Community;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lighthouse.R;

import androidx.appcompat.app.AppCompatActivity;

public class UploadPlan extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    private Spinner majorSpinner = null;
    private Spinner directionSpinner = null;
    private String majorContent;       //专业名称
    private String directionContent;   //方向名称
    private EditText editUploadPlanName; //上传计划名称
    private EditText editUploadPlanTime; //需要的计划时长
    private Button addPlanButton;        //添加子计划
    private Button confirmUploadButton;  //确认上传
    private Button cancalUploadButton;   //取消上传

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadplan_activity);

        init();

        majorSpinner.setOnItemSelectedListener(this);
        directionSpinner.setOnItemSelectedListener(this);
    }

    private void init(){
        majorSpinner = findViewById(R.id.spin_one);
        directionSpinner = findViewById(R.id.spin_two);
        editUploadPlanName = findViewById(R.id.upload_plan_name);
        editUploadPlanTime = findViewById(R.id.upload_plan_time);
        addPlanButton = findViewById(R.id.add_plan);
        confirmUploadButton = findViewById(R.id.confrim_upload);
        cancalUploadButton = findViewById(R.id.cancal_upload);
    }
    public void addPlan(View v){  //添加子计划

    }
    public void confirmUpload(View v){ //确认上传

        Intent intent = new Intent();
        intent.setClass(UploadPlan.this,Community.class);
        startActivity(intent);
    }
    public void returnUpload(View v){ //返回
        Intent intent = new Intent();
        intent.setClass(UploadPlan.this,Community.class);
        startActivity(intent);
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
}
