package cms.app;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import cms.app.net.QuerySchedules;
import cms.app.net.Schedules;
import cms.app.net.User;


public class StudentSchedulesAdapter extends ArrayAdapter<Schedules>{

    int resId;
    public StudentSchedulesAdapter(Context context, int resource, List<Schedules> objects) {
        super(context,resource,objects);
        this.resId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Schedules schedules = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resId,parent,false);
        TextView textViewnumber=view.findViewById(R.id.text_course_table_number);
        TextView textViewmon=view.findViewById(R.id.text_course_table_mon);
        TextView textViewtues=view.findViewById(R.id.text_course_table_tues);
        TextView textViewwed=view.findViewById(R.id.text_course_table_wed);
        TextView textViewthur=view.findViewById(R.id.text_course_table_thur);
        TextView textViewfri=view.findViewById(R.id.text_course_table_fri);
        TextView textViewsat=view.findViewById(R.id.text_course_table_sat);
        TextView textViewsun=view.findViewById(R.id.text_course_table_sun);
        textViewnumber.setText(""+schedules.getNumber());//
        textViewmon.setText(schedules.getMon());
        textViewtues.setText(schedules.getTues());
        textViewwed.setText(schedules.getWed());
        textViewthur.setText(schedules.getThur());
        textViewfri.setText(schedules.getFri());
        textViewsat.setText(schedules.getSat());
        textViewsun.setText(schedules.getSun());
        return view;
    }
}