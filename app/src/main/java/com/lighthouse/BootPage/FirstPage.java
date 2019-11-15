package com.lighthouse.BootPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.lighthouse.R;
import com.lighthouse.User.User;

import org.litepal.tablemanager.Connector;

public class FirstPage extends Activity {
    private ImageButton highButton;
    private ImageButton collegeButton;
    private ImageButton graduationButton;
    private ImageButton nextButton;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage_activity);

        Connector.getDatabase();
        init();

        highButton = findViewById(R.id.high_school_student);
        highButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("高中生");
                intent.putExtra("userId",userId);
                intent.setClass(FirstPage.this,SecondPage.class);
                startActivity(intent);
            }
        });

        collegeButton=findViewById(R.id.college_student);
        collegeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("大学生");
                intent.putExtra("userId",userId);
                intent.setClass(FirstPage.this,SecondPage.class);
                startActivity(intent);
            }
        });

        graduationButton=findViewById(R.id.graduation);
        graduationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("毕业生");
                intent.putExtra("userId",userId);
                intent.setClass(FirstPage.this,SecondPage.class);
                startActivity(intent);
            }
        });

        nextButton=findViewById(R.id.first_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(FirstPage.this,SecondPage.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        userId = getIntent().getStringExtra("userId");
    }
    private void saveInformation(String information){
        User updateUser = new User();
        if(information.equals("高中生")){
            updateUser.setIdendity(0);
        }else{
            updateUser.setIdendity(1);
        }
        updateUser.updateAll("userId = ?",userId);
    }
}
