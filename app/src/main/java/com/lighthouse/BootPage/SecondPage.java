package com.lighthouse.BootPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.lighthouse.R;
import com.lighthouse.User.User;

import org.litepal.tablemanager.Connector;

public class SecondPage extends Activity {
    private ImageButton backButton;
    private ImageButton forwardButton;
    private ImageButton shujiButton;
    private ImageButton jingguanButton;
    private ImageButton wuxinButton;
    private ImageButton tumuButton;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage_activity);

        Connector.getDatabase();
        init();

        backButton = findViewById(R.id.second_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(SecondPage.this,FirstPage.class);
                startActivity(intent);
            }
        });

        forwardButton=findViewById(R.id.second_forward);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(SecondPage.this,ThirdPage.class);
                startActivity(intent);
            }
        });

        shujiButton=findViewById(R.id.xy1);
        shujiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("数学与计算机科学学院");
                intent.putExtra("userId",userId);
                intent.setClass(SecondPage.this,ThirdPage.class);
                startActivity(intent);
            }
        });

        jingguanButton=findViewById(R.id.xy2);
        jingguanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("经济与管理学院");
                intent.putExtra("userId",userId);
                intent.setClass(SecondPage.this,ThirdPage.class);
                startActivity(intent);
            }
        });

        wuxinButton=findViewById(R.id.xy3);
        wuxinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("物理与信息工程学院");
                intent.putExtra("userId",userId);
                intent.setClass(SecondPage.this,ThirdPage.class);
                startActivity(intent);
            }
        });

        tumuButton=findViewById(R.id.xy4);
        tumuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("土木学院");
                intent.putExtra("userId",userId);
                intent.setClass(SecondPage.this,ThirdPage.class);
                startActivity(intent);
            }
        });

    }

    private void init(){
        userId = getIntent().getStringExtra("userId");
    }
    private void saveInformation(String information){
        User updateUser = new User();
        updateUser.setUserCollege(information);
        updateUser.updateAll("userId = ?",userId);
    }
}
