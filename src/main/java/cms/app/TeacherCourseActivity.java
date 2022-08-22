package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import cms.app.net.CMSClient;
import cms.app.net.QueryStudentAllCourseResult;

public class TeacherCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_course);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //to_do 获取当前用户
        String retMsg = CMSClient.sendAndReceive("query_teacher_all_courses\n"+GlobalConfig.currentUser);
        Log.d("red ","query_teacher_all_courses\n"+GlobalConfig.currentUser);
        Gson gson = new Gson();
        final QueryStudentAllCourseResult result = gson.fromJson(retMsg,QueryStudentAllCourseResult.class);
        ArrayList<CourseInfo> courses = new ArrayList<>() ;
        for(CourseInfo c : result.getCourses()){
            courses.add(c);
        }
        TeacherCourseAdapter adp = new TeacherCourseAdapter(this,R.layout.listview_item_teacher_course,courses);
        ListView listView = findViewById(R.id.listview_teacher_course);
        listView.setAdapter(adp);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d( "red","position"+position);
                ArrayList<CourseInfo> courses = result.getCourses();
                CourseInfo c = courses.get(position);
                String course_id =c.getId();
                String name =c.getName();
                int type =c.getType();
                float credit =c.getCredit();
                String book =c.getBook();
                String teacherName = c.getTeacherName();
                String classroom = c.getClassroom();
                String time = c.getTime();
                String classname = c.getClassname();
                Intent intent = new Intent(TeacherCourseActivity.this,TeacherDetailActivity.class);
                intent.putExtra("id",course_id);
                intent.putExtra("name",name);
                intent.putExtra("type",type);
                intent.putExtra("credit",credit);
                intent.putExtra("book",book);
                intent.putExtra("teacher-name",teacherName);
                intent.putExtra("classroom",classroom);
                intent.putExtra("time",time);
                intent.putExtra("classname",classname);
                Log.d("red","detail:"+id+""+name+""+type+""+credit+""+book+""+teacherName+""+classroom+""+time+"\n"+classname);

                startActivity(intent);
            }
        });
        Button create = findViewById(R.id.btn_teacher_create_course);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherCourseActivity.this,TeacherCreateCourseActivity.class);
                startActivity(intent);
            }
        });
    }
}
