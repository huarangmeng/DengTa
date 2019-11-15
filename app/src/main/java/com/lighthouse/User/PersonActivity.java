package com.lighthouse.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lighthouse.Search.Search;
import com.lighthouse.Community.Community;
import com.lighthouse.MainActivity;
import com.lighthouse.R;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class PersonActivity extends Activity {
    //信息未设置
    private ImageButton planButton;
    private ImageButton serButton;
    private ImageButton comButton;
    private ImageButton perButton;
    private TextView textUserName;
    private TextView textUserMajor;
    private TextView textAdopt;
    private TextView textCollection;
    private TextView textPraise;
    private User user = new User();
    private String userId;
    private int adoptNum = 0;
    private int collectionNum = 0;
    private int praiseNum = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_activity);

        Connector.getDatabase();
        jumpinit();
        init();

        /*点击SETUP按钮进入个人信息修改界面*/
        Button setupButton = (Button) findViewById(R.id.setup);
        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(PersonActivity.this, PersonalSetting.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        userId = getIntent().getStringExtra("userId");
        List<User> users = DataSupport.findAll(User.class);
        for(User temp : users){
            if(temp.getUserId().equals(userId)){
                user = temp;
                break;
            }
        }
        textUserName = findViewById(R.id.user_name);
        textUserMajor = findViewById(R.id.user_major);
        textAdopt = findViewById(R.id.adoption_number);
        textCollection = findViewById(R.id.collection_number);
        textPraise = findViewById(R.id.like_number);

        textUserName.setText(user.getUserName());
        textUserMajor.setText(user.getUserCollege() +" | "+user.getUserMajor());
        textAdopt.setText(String.valueOf(adoptNum));
        textPraise.setText(String.valueOf(praiseNum));
        textCollection.setText(String.valueOf(collectionNum));
    }
    public void jumpinit(){
        planButton = findViewById(R.id.main_plan);
        serButton = findViewById(R.id.main_search);
        perButton = findViewById(R.id.main_personal);
        comButton = findViewById(R.id.main_community);

        planButton.setBackgroundResource(R.mipmap.plan_icon_dark);
        comButton.setBackgroundResource(R.mipmap.community_icon_dark);
        perButton.setBackgroundResource(R.mipmap.personal_icon_bright);
        serButton.setBackgroundResource(R.mipmap.search_icon_dark);

        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(PersonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        comButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(PersonActivity.this, Community.class);
                startActivity(intent);
            }
        });
        serButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(PersonActivity.this, Search.class);
                startActivity(intent);
            }
        });
    }
}
