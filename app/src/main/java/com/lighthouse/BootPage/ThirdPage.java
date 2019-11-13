package com.lighthouse.BootPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.lighthouse.R;

public class ThirdPage extends Activity {
    private ImageButton backButton;
    private ImageButton forwardButton;
    private ImageButton jisuanjiButton;
    private ImageButton ruanjianButton;
    private ImageButton shuxueButton;
    private ImageButton xinanButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdpage_activity);

        backButton = findViewById(R.id.thrid_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ThirdPage.this,SecondPage.class);
                startActivity(intent);
            }
        });

        forwardButton=findViewById(R.id.thrid_forward);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ThirdPage.this,FourPage.class);
                startActivity(intent);
            }
        });

        jisuanjiButton=findViewById(R.id.zy1);
        jisuanjiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ThirdPage.this,FourPage.class);
                startActivity(intent);
            }
        });

        ruanjianButton=findViewById(R.id.zy2);
        ruanjianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ThirdPage.this,FourPage.class);
                startActivity(intent);
            }
        });

        shuxueButton=findViewById(R.id.zy3);
        shuxueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ThirdPage.this,FourPage.class);
                startActivity(intent);
            }
        });

        xinanButton=findViewById(R.id.zy4);
        xinanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ThirdPage.this,FourPage.class);
                startActivity(intent);
            }
        });

    }
}
