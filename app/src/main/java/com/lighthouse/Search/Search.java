package com.lighthouse.Search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import com.lighthouse.Community.Community;
import com.lighthouse.MainActivity;
import com.lighthouse.R;
import com.lighthouse.User.PersonActivity;

public class Search extends Activity {
    private ImageButton planButton;
    private ImageButton comButton;
    private ImageButton perButton;
    private ImageButton serButton;
    //默认推荐内容
    private String[] mStrs = {"Java", "Python", "c", "c++", "java", "python"};
    private SearchView mSearchView;
    private ListView mListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        jumpinit();

        mSearchView = (SearchView) findViewById(R.id.searchView);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs));
        mListView.setTextFilterEnabled(true);

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mListView.setFilterText(newText);
                }else{
                    mListView.clearTextFilter();
                }
                return false;
            }
        });
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
