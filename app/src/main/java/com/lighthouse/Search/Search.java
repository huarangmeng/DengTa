package com.lighthouse.Search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.lighthouse.Community.Community;
import com.lighthouse.MainActivity;
import com.lighthouse.R;
import com.lighthouse.User.PersonActivity;

public class Search extends Activity {
    private ImageButton planButton;
    private ImageButton comButton;
    private ImageButton perButton;
    private ImageButton serButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        jumpinit();
    }

    public void jumpinit(){
        planButton = findViewById(R.id.main_plan);
        comButton = findViewById(R.id.main_community);
        perButton = findViewById(R.id.main_personal);
        serButton = findViewById(R.id.main_search);

        planButton.setBackgroundResource(R.mipmap.plan_icon_dark);
        comButton.setBackgroundResource(R.mipmap.community_icon_dark);
        perButton.setBackgroundResource(R.mipmap.personal_icon_dark);
        serButton.setBackgroundResource(R.mipmap.search_icon_bright);

        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, MainActivity.class);
                startActivity(intent);
            }
        });
        comButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Community.class);
                startActivity(intent);
            }
        });
        perButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, PersonActivity.class);
                startActivity(intent);
            }
        });
    }
}
