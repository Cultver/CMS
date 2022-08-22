package cms.app;


import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cms.app.net.QuerySchedules;
import cms.app.net.Schedules;
import com.google.gson.Gson;

import java.util.ArrayList;



import cms.app.net.CMSClient;


public class StudentSchedulesActivity extends AppCompatActivity {
    String mon="周一";
    String tues="周二";
    String wed="周三";
    String thur="周四";
    String fri="周五";
    String sat="周六";
    String sun="周日";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedules);
        getSupportActionBar().hide();
        final Button ensure = findViewById(R.id.button_create_course_ensure);
        ListView listView = findViewById(R.id.list_select_course_table);
        Intent intent=getIntent();
        String type = intent.getStringExtra("type");
if(type.equals("3")) {
    final String retmsg = CMSClient.sendAndReceive("query_courses\n" + GlobalConfig.currentUser);
    Gson gson = new Gson();
    final QuerySchedules querySchedules = gson.fromJson(retmsg, QuerySchedules.class);
    ArrayList<Schedules> schedules = new ArrayList<>();

    for (Schedules a : querySchedules.getcourse()) {
        schedules.add(a);
    }
    StudentSchedulesAdapter adp = new StudentSchedulesAdapter(StudentSchedulesActivity.this, R.layout.list_item_student_schedules, schedules);
    listView.setAdapter(adp);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

            final TextView tx1 = (TextView) view.findViewById(R.id.text_course_table_mon);
            tx1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tx1.getText().toString().equals("已选择")) {
                        tx1.setBackgroundColor(Color.WHITE);
                        tx1.setText("  ");
                        String a = String.valueOf(position + 1);
                        String[] arr = mon.split(a);
                        mon = arr[0] + arr[1];
                        Log.d("asd", "" + a);
                        Log.d("asd", "" + mon);
                    } else if (tx1.getText().toString().equals("  ")) {
                        tx1.setBackgroundColor(Color.YELLOW);
                        tx1.setText("已选择");
                        mon += String.valueOf(position + 1) + ",";
                        Log.d("asd", mon);
                    }

                }

            });
            final TextView tx2 = (TextView) view.findViewById(R.id.text_course_table_tues);
            tx2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tx2.getText().toString().equals("已选择")) {
                        tx2.setBackgroundColor(Color.WHITE);
                        tx2.setText("  ");
                        String[] arr = tues.split(String.valueOf(position + 1));
                        tues = arr[0] + arr[1];
                    } else if (tx2.getText().toString().equals("  ")) {
                        tx2.setBackgroundColor(Color.YELLOW);
                        tx2.setText("已选择");
                        tues += (position + 1) + ",";
                    }

                }
            });
            final TextView tx3 = (TextView) view.findViewById(R.id.text_course_table_wed);
            tx3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (tx3.getText().toString().equals("已选择")) {
                        tx3.setBackgroundColor(Color.WHITE);
                        tx3.setText("  ");
                        String[] arr = wed.split(String.valueOf(position + 1));
                        wed = arr[0] + arr[1];
                    } else if (tx3.getText().toString().equals("  ")) {
                        tx3.setBackgroundColor(Color.YELLOW);
                        tx3.setText("已选择");
                        wed += (position + 1) + ",";
                    }
                }
            });
            final TextView tx4 = (TextView) view.findViewById(R.id.text_course_table_thur);
            tx4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (tx2.getText().toString().equals("已选择")) {
                        tx2.setBackgroundColor(Color.WHITE);
                        tx2.setText("  ");
                        String[] arr = thur.split(String.valueOf(position + 1));
                        thur = arr[0] + arr[1];
                    } else if (tx4.getText().toString().equals("  ")) {
                        tx4.setBackgroundColor(Color.YELLOW);
                        tx4.setText("已选择");
                        thur += (position + 1) + ",";
                    }
                }
            });
            final TextView tx5 = (TextView) view.findViewById(R.id.text_course_table_fri);
            tx5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (tx5.getText().toString().equals("已选择")) {
                        tx5.setBackgroundColor(Color.WHITE);
                        tx5.setText("  ");
                        String[] arr = fri.split(String.valueOf(position + 1));
                        fri = arr[0] + arr[1];
                    } else if (tx5.getText().toString().equals("  ")) {
                        tx5.setBackgroundColor(Color.YELLOW);
                        tx5.setText("已选择");
                        fri += (position + 1) + ",";
                    }
                }
            });
            final TextView tx6 = (TextView) view.findViewById(R.id.text_course_table_sat);
            tx6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (tx6.getText().toString().equals("已选择")) {
                        tx6.setBackgroundColor(Color.WHITE);
                        tx6.setText("  ");
                        String[] arr = sat.split(String.valueOf(position + 1));
                        sat = arr[0] + arr[1];
                    } else if (tx6.getText().toString().equals("  ")) {
                        tx6.setBackgroundColor(Color.YELLOW);
                        tx6.setText("已选择");
                        sat += (position + 1) + ",";
                    }
                }
            });
            final TextView tx7 = (TextView) view.findViewById(R.id.text_course_table_sun);
            tx7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tx7.getText().toString().equals("已选择")) {
                        tx7.setBackgroundColor(Color.WHITE);
                        tx7.setText("  ");
                        String[] arr = sun.split(String.valueOf(position + 1));
                        sun = arr[0] + arr[1];
                    } else if (tx7.getText().toString().equals("  ")) {
                        tx7.setBackgroundColor(Color.YELLOW);
                        tx7.setText("已选择");
                        sun += (position + 1) + ",";
                    }
                }
            });
            ensure.setVisibility(View.VISIBLE);
            ensure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent1=new Intent();
                    intent1.putExtra("a2",mon+tues+thur+wed+fri+sat+sun);
                    Log.d("asd",mon+tues+thur+wed+fri+sat+sun);
                    setResult(2,intent1);
                    finish();

                }
            });

        }


    });
}

    }
}




