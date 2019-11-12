package com.lighthouse.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.lighthouse.R;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PersonalSetting extends Activity implements  AdapterView.OnItemSelectedListener{
    private Button saveButton;
    private Button backButton;
    private User user = new User();
    private String userId;
    private TextView textUseId;  //用户ID
    private EditText editUseName; //用户昵称
    private String userName;
    private EditText editName;    //用户本名
    private String Name;
    private Spinner sexSpinner;   //性别
    private String useSex;
    private EditText editUseBirthday;  //生日
    private String userBirthday;
    private EditText editUseAge;     //年龄
    private String userAge;
    private Spinner idenditySpinner;   //身份
    private String useIdendity;
    private Spinner collegeSpinner;    //学院
    private String useCollege;
    private Spinner majorSpinner;    //专业
    private String useMajor;
    private Spinner directionSpinner;  //方向
    private String useDirection;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalsetting_activity);

        init();

        sexSpinner.setOnItemSelectedListener(this);      //性别的选择监听
        idenditySpinner.setOnItemSelectedListener(this); //身份的选择监听
        collegeSpinner.setOnItemSelectedListener(this);  //学院的选择监听
        majorSpinner.setOnItemSelectedListener(this);    //专业的选择监听
        directionSpinner.setOnItemSelectedListener(this);//方向的选择监听

        /*点击保存修改按钮返回个人信息界面*/
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfromation();  //保存个人信息到数据库中

                Intent intent = new Intent();
                intent.setClass(PersonalSetting.this,PersonActivity.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PersonalSetting.this,PersonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveInfromation(){
        getInfromation();    //获得修改的信息
    }

    private void getInfromation(){
        userName = editUseName.getText().toString().trim();
        Name = editName.getText().toString().trim();
        userBirthday = editUseBirthday.getText().toString().trim();
        userAge = editUseAge.getText().toString().trim();
    }

    private void init(){
        saveButton = (Button) findViewById(R.id.save_changes);
        backButton = (Button) findViewById(R.id.per_back);
        textUseId = (TextView) findViewById(R.id.user_id);
        editUseName = (EditText) findViewById(R.id.user_name);
        editName = (EditText) findViewById(R.id.name);
        sexSpinner = (Spinner) findViewById(R.id.spin_sex);
        editUseBirthday = (EditText) findViewById(R.id.use_birthday);
        editUseAge = (EditText) findViewById(R.id.use_age);
        idenditySpinner = (Spinner) findViewById(R.id.spin_idendity);
        collegeSpinner = (Spinner) findViewById(R.id.spin_college);
        majorSpinner = (Spinner) findViewById(R.id.spin_major);
        directionSpinner = (Spinner) findViewById(R.id.spin_direction);

        findUserId(); //找到目标用户

        textUseId.setText(userId);
    }

    private void findUserId(){
        userId = getIntent().getStringExtra("userId");
        List<User> users = DataSupport.findAll(User.class);
        for(User temp : users){
            if(temp.getUserId() == userId){
                user = temp;
                break;
            }
        }
        editUseName.setText(user.getUserName());
        editName.setText(user.getName());
        editUseAge.setText(user.getAge());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String content = parent.getItemAtPosition(position).toString();
        switch (parent.getId()){
            case R.id.spin_sex:
                useSex = content;
                break;
            case R.id.spin_idendity:
                useIdendity = content;
                break;
            case R.id.spin_college:
                useCollege = content;
                break;
            case R.id.spin_major:
                useMajor = content;
                break;
            case R.id.spin_direction:
                useDirection = content;
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
