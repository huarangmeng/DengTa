package com.lighthouse.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lighthouse.MainActivity;
import com.lighthouse.R;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class Login extends Activity {
    private Button registerButton;
    private Button btn_login;//登录按钮
    private String userName,psw,spPsw;//获取的用户名，密码，加密密码
    private EditText et_user_name,et_psw;//编辑框

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Connector.getDatabase();

        init();

    }

    private void init(){
        registerButton = findViewById(R.id.tv_register);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Register.class);
                startActivity(intent);
            }
        });

        //登录按钮的点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始登录，获取用户名和密码 getText().toString().trim();
                userName=et_user_name.getText().toString().trim();
                psw=et_psw.getText().toString().trim();
                //对当前用户输入的密码进行MD5加密再进行比对判断, MD5Utils.md5( ); psw 进行加密判断是否一致
                String md5Psw= MD5Utils.md5(psw);
                // md5Psw ; spPsw 为 根据从Litepal中用户名读取密码
                // 定义方法 readPsw为了读取用户名，得到密码
                spPsw=readPsw(userName);
                // TextUtils.isEmpty
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(Login.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(Login.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                    // md5Psw.equals(); 判断，输入的密码加密后，是否与保存在Litepal中一致
                }else if(md5Psw.equals(spPsw)){
                    //一致登录成功
                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();

                    //登录成功后关闭此页面进入主页
                    Intent myIntent=new Intent();
                    myIntent.putExtra("data",userName);
                    //跳转到主界面，登录成功的状态传递到 MainPageActivity 中
                    myIntent.setClass(Login.this, MainActivity.class);
                    //myIntent.putExtra( ); name , value ;
                    startActivity(myIntent);
                    //销毁登录界面
                    Login.this.finish();
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    return;
                }else if((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw))){
                    Toast.makeText(Login.this, "密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(Login.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //从Litepal中根据用户名读取密码
    private String readPsw(String userName){
        String pswWord = null;
        List<User> users = DataSupport.findAll(User.class);
        for(User user : users){
            if(user.getUserId().equals(userName)){
                pswWord = user.getPassWord();
            }
        }
        return pswWord;
    }
}
