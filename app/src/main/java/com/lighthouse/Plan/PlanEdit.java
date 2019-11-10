package com.lighthouse.Plan;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lighthouse.R;

import java.util.Calendar;

public  class PlanEdit extends Activity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, View.OnLongClickListener{
    private TextView date_text;   //日期
    private TextView time_text;   //时间
    private ImageButton alarm_button;  //闹钟
    private EditText edt;           //编辑文字框
    private TextView alarm_view;   //闹钟时间显示

    private int planId;
    private String textDate;
    private String textTime;
    private String endTime;
    private String mainText;

    //alarm clock
    private int num=0; //for requestcode
    private int BIG_NUM_FOR_ALARM=100;
    private String alarm="";
    private int alarm_hour=0;
    private int alarm_minute=0;
    private int alarm_year=0;
    private int alarm_month=0;
    private int alarm_day=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_activity);

        Intent it=getIntent();
        getInformationFromMain(it);

        date_text=(TextView) findViewById(R.id.dateText);
        time_text=(TextView) findViewById(R.id.timeText);
        alarm_button=(ImageButton) findViewById((R.id.alarmButton));
        //alarm_button.setBackgroundResource(R.mipmap.ic_launcher);
        edt=(EditText) findViewById(R.id.editText);
        //edt.setBackgroundColor(color[tag]);
        alarm_view=(TextView) findViewById(R.id.alarmView);

        date_text.setText(textDate);
        time_text.setText(textTime);
        edt.setText(mainText);

        alarm_view.setOnLongClickListener(this);
        if(alarm.length()>1) alarm_view.setText("Alert at "+alarm+"!");
        else alarm_view.setVisibility(View.GONE);

    }

    //*********************************set alarm clock***********************************
    public void setAlarm(View v) {
        if(alarm.length()<=1) {
            //if no alarm clock has been set up before
            //show the current time
            Calendar c=Calendar.getInstance();
            alarm_hour=c.get(Calendar.HOUR_OF_DAY);
            alarm_minute=c.get(Calendar.MINUTE);

            alarm_year=c.get(Calendar.YEAR);
            alarm_month=c.get(Calendar.MONTH)+1;
            alarm_day=c.get(Calendar.DAY_OF_MONTH);
        }
        else {
            //show the alarm clock time which has been set up before
            int i=0, k=0;
            while(i<alarm.length()&&alarm.charAt(i)!='/') i++;
            alarm_year=Integer.parseInt(alarm.substring(k,i));
            k=i+1;i++;
            while(i<alarm.length()&&alarm.charAt(i)!='/') i++;
            alarm_month=Integer.parseInt(alarm.substring(k,i));
            k=i+1;i++;
            while(i<alarm.length()&&alarm.charAt(i)!=' ') i++;
            alarm_day=Integer.parseInt(alarm.substring(k,i));
            k=i+1;i++;
            while(i<alarm.length()&&alarm.charAt(i)!=':') i++;
            alarm_hour=Integer.parseInt(alarm.substring(k,i));
            k=i+1;i++;
            alarm_minute=Integer.parseInt(alarm.substring(k));
        }

        new TimePickerDialog(this,this,alarm_hour,alarm_minute,true).show();
        new DatePickerDialog(this,this,alarm_year,alarm_month-1,alarm_day).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        alarm_year=year;
        alarm_month=monthOfYear+1;
        alarm_day=dayOfMonth;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        alarm_hour=hourOfDay;
        alarm_minute=minute;

        alarm=alarm_year+"/"+alarm_month+"/"+alarm_day+" "+alarm_hour+":"+alarm_minute;
        endTime = alarm_year+"/"+alarm_month+"/"+alarm_day;
        alarm_view.setText("Alert at "+alarm+"!");
        alarm_view.setVisibility(View.VISIBLE);
        Toast.makeText(this,"Alarm will be on at "+alarm+" !",Toast.LENGTH_LONG).show();
    }

    //******************************************************************************************



    //press the save button
    public void onSave(View v) {
        returnResult();
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //if the Back Button is pressed
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            returnResult();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // after pressing back or save, return the current state
    private void returnResult() {
        Intent it=new Intent();

        it.putExtra("planId",planId);
        //no need for date and time
        it.putExtra("alarm",alarm);
        it.putExtra("mainText",edt.getText().toString());

        setResult(RESULT_OK,it);
    }

    //read Intent information from main_activity
    private void getInformationFromMain(Intent it) {
        num=it.getIntExtra("num",0);

        planId=it.getIntExtra("planId",0);
        textDate=it.getStringExtra("textDate");
        textTime=it.getStringExtra("textTime");
        endTime=it.getStringExtra("endTime");
        alarm=it.getStringExtra("alarm");
        mainText=it.getStringExtra("mainText");
    }

    @Override
    public boolean onLongClick(View v) {
        if(v.getId()==R.id.alarmView||v.getId()==R.id.alarmButton) {
            //delete the alarm information
            alarm="";
            //hide textView
            alarm_view.setVisibility(View.GONE);
        }
        return true;
    }
}
