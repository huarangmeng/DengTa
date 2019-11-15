package com.lighthouse.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lighthouse.BootPage.FirstPage;
import com.lighthouse.R;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Register extends Activity {
    private Button btn_register;//注册按钮
    //用户名，密码，再次输入的密码的控件
    private EditText et_user_id,et_psw,et_psw_again;
    //用户名，密码，再次输入的密码的控件的获取值
    private String userId,psw,pswAgain;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        init();
    }

    private void init(){
        btn_register = (Button) findViewById(R.id.btn_register);
        et_user_id = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        et_psw_again = (EditText) findViewById(R.id.et_psw_again);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入在相应控件中的字符串
                getEditString();
                //判断输入框内容
                if(TextUtils.isEmpty(userId)){
                    Toast.makeText(Register.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(userId.length() != 7){
                    Toast.makeText(Register.this, "用户名长度需为7位", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(Register.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(psw.length() < 6 || psw.length() > 12){
                    Toast.makeText(Register.this, "密码长度需为6-12位", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(Register.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(Register.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                    /**
                     *从Litepal中读取输入的用户名，判断Litepal中是否有此用户名
                     */
                }else if(isExistUserName(userId)){
                    Toast.makeText(Register.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //把账号、密码和账号标识保存到sp里面

                    saveRegisterInfo(userId, psw);

                    Intent data = new Intent();
                    data.putExtra("userId", userId);
                    data.setClass(Register.this, FirstPage.class);
                    //跳转到主页面
                    startActivity(data);

                    Register.this.finish();
                }
            }
        });
    }

    private void getEditString(){
        userId = et_user_id.getText().toString().trim();
        psw = et_psw.getText().toString().trim();
        pswAgain = et_psw_again.getText().toString().trim();
    }

    private boolean isExistUserName(String userId){
        boolean has_userId = false;
        //查询数据库中是否存在相同账号
        List<User> users = DataSupport.findAll(User.class);
        for(User user:users){
            if(user.getUserId().equals(userId)){
                has_userId = true;
                break;
            }
        }
        return has_userId;
    }
    private void saveRegisterInfo(String userId,String psw){
        String md5Psw = MD5Utils.md5(psw);
        //将密码MD5 加密
        User user = new User();
        user.setUserId(userId);
        user.setPassWord(md5Psw);
        user.save();
    }
}
