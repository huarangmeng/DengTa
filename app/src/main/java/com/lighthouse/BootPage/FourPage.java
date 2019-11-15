package com.lighthouse.BootPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.lighthouse.MainActivity;
import com.lighthouse.R;
import com.lighthouse.User.User;

import org.litepal.tablemanager.Connector;

public class FourPage extends Activity {
    private ImageButton backButton;
    private ImageButton androidButton;
    private ImageButton javaButton;
    private ImageButton webButton;
    private ImageButton shujuButton;
    private ImageButton dontButton;
    private String userId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourpage_activity);

        Connector.getDatabase();
        init();

        backButton = findViewById(R.id.four_back);
        androidButton = findViewById(R.id.fx1);
        javaButton = findViewById(R.id.fx2);
        webButton = findViewById(R.id.fx3);
        shujuButton = findViewById(R.id.fx4);
        dontButton = findViewById(R.id.fx5);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(FourPage.this,ThirdPage.class);
                startActivity(intent);
            }
        });
        androidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("Android");
                intent.putExtra("userId",userId);
                intent.setClass(FourPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        javaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("Java");
                intent.putExtra("userId",userId);
                intent.setClass(FourPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("Web");
                intent.putExtra("userId",userId);
                intent.setClass(FourPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        shujuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("数据库");
                intent.putExtra("userId",userId);
                intent.setClass(FourPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        dontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                saveInformation("尚未决定");
                intent.putExtra("userId",userId);
                intent.setClass(FourPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        userId = getIntent().getStringExtra("userId");
    }
    private void saveInformation(String information){
        User updateUser = new User();
        updateUser.setDirection(information);
        updateUser.updateAll("userId = ?",userId);
    }
}
