package com.example.myapplication.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.Community.Community;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Search.Search;

public class PersonActivity extends Activity {
    private ImageButton planButton;
    private ImageButton serButton;
    private ImageButton comButton;
    private ImageButton perButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_activity);

        jumpinit();
    }

    public void jumpinit(){
        planButton = findViewById(R.id.main_plan);
        serButton = findViewById(R.id.main_search);
        perButton = findViewById(R.id.main_personal);
        comButton = findViewById(R.id.main_community);

        planButton.setBackgroundResource(R.mipmap.personal_icon_dark);
        comButton.setBackgroundResource(R.mipmap.community_icon_dark);
        perButton.setBackgroundResource(R.mipmap.personal_icon_bright);
        serButton.setBackgroundResource(R.mipmap.search_icon_dark);

        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        comButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonActivity.this, Community.class);
                startActivity(intent);
            }
        });
        serButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonActivity.this, Search.class);
                startActivity(intent);
            }
        });
    }
}
