package com.lighthouse.BootPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.lighthouse.R;

public class FirstPage extends Activity {
    private ImageButton highButton;
    private ImageButton collegeButton;
    private ImageButton graduationButton;
    private ImageButton nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage_activity);

        highButton = findViewById(R.id.high_school_student);
        highButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(FirstPage.this,SecondPage.class);
                startActivity(intent);
            }
        });

        collegeButton=findViewById(R.id.college_student);
        collegeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(FirstPage.this,SecondPage.class);
                startActivity(intent);
            }
        });

        graduationButton=findViewById(R.id.graduation);
        graduationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(FirstPage.this,SecondPage.class);
                startActivity(intent);
            }
        });

        nextButton=findViewById(R.id.first_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(FirstPage.this,SecondPage.class);
                startActivity(intent);
            }
        });
    }
}
