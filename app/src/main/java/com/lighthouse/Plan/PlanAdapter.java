package com.lighthouse.Plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lighthouse.R;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PlanAdapter extends ArrayAdapter<Plan>{
    private int resourceId;
    public PlanAdapter(Context context, int resource, List<Plan> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plan plan=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent, false);

        TextView textNum = (TextView)view.findViewById(R.id.plan_num);
        TextView textName=(TextView)view.findViewById(R.id.plan_name);
        TextView textTime=(TextView)view.findViewById(R.id.plan_time);
        ImageView alarm=(ImageView) view.findViewById(R.id.alarm);
        TextView stateNmae=(TextView) view.findViewById(R.id.plan_state_name);
        ImageView state =(ImageView) view.findViewById(R.id.plan_state);
        TextView mainText=(TextView)view.findViewById(R.id.mainText);
        LinearLayout linearLayout =(LinearLayout)view.findViewById(R.id.plan_list);

        textNum.setText(plan.getPlanNum());
        textName.setText(plan.getPlanName());
        textTime.setText(plan.getStartTime()+"-"+plan.getEndTime());

        if(plan.getAlarm()) {
            alarm.setVisibility(View.VISIBLE);
        }
        else {
            alarm.setVisibility(View.GONE);
        }

        if(plan.getState() == 0) {
            state.setBackgroundResource(R.mipmap.plan_incompleted);
            stateNmae.setText("未完成");
        }
        else if(plan.getState() == 1){
            ViewGroup.LayoutParams params = mainText.getLayoutParams();
            params.height = 60;
            mainText.setLayoutParams(params);
            ViewGroup.LayoutParams paraml = linearLayout.getLayoutParams();
            paraml.height = 600;
            linearLayout.setLayoutParams(paraml);
            state.setBackgroundResource(R.mipmap.plan_incompleted);
            stateNmae.setText("进行中");
        }else{
            state.setBackgroundResource(R.mipmap.plan_completed);
            stateNmae.setText("已完成");
        }

        mainText.setText(plan.getMainText());

        return view;
    }
}
