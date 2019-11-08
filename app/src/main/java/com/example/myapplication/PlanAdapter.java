package com.example.myapplication;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlanAdapter extends ArrayAdapter<Plan> {
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
        ImageView state =(ImageView) view.findViewById(R.id.plan_state);
        TextView mainText=(TextView)view.findViewById(R.id.mainText);


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
            state.setVisibility(View.VISIBLE);
        }
        else if(plan.getState() == 1){
            state.setVisibility(View.GONE);
        }else{
            state.setVisibility(view.GONE);
        }

        mainText.setText(plan.getMainText());

        return view;
    }
}
