package com.example.myapplication.Community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Search.Search;
import com.example.myapplication.User.PersonActivity;

public class Community extends Activity {
    private ImageButton planButton;
    private ImageButton serButton;
    private ImageButton perButton;
    private ImageButton comButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activity);

        jumpinit();
    }
    public void jumpinit(){
        planButton = findViewById(R.id.main_plan);
        serButton = findViewById(R.id.main_search);
        perButton = findViewById(R.id.main_personal);
        comButton = findViewById(R.id.main_community);

        planButton.setBackgroundResource(R.mipmap.personal_icon_dark);
        comButton.setBackgroundResource(R.mipmap.community_icon_bright);
        perButton.setBackgroundResource(R.mipmap.personal_icon_dark);
        serButton.setBackgroundResource(R.mipmap.search_icon_dark);

        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Community.this, MainActivity.class);
                startActivity(intent);
            }
        });
        serButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Community.this, Search.class);
                startActivity(intent);
            }
        });
        perButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Community.this, PersonActivity.class);
                startActivity(intent);
            }
        });
    }
}
