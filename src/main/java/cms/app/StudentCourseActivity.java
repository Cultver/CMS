package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import cms.app.net.CMSClient;
import cms.app.net.QueryStudentAllCourseResult;

public class StudentCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //to_do 获取当前用户
        String retMsg = CMSClient.sendAndReceive("query_student_all_courses\n"+GlobalConfig.currentUser);
        Log.d("red ","query_student_all_courses\n"+GlobalConfig.currentUser);
        Gson gson = new Gson();
        final QueryStudentAllCourseResult result = gson.fromJson(retMsg,QueryStudentAllCourseResult.class);
        ArrayList<CourseInfo> courses = new ArrayList<>() ;
        for(CourseInfo c : result.getCourses()){
            courses.add(c);
        }
        StudentCourseAdapter adp = new StudentCourseAdapter(this,R.layout.listview_item_student_course,courses);
        ListView listView = findViewById(R.id.listview_student_course);
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
                Intent intent = new Intent(StudentCourseActivity.this,CourseDetailActivity.class);
                intent.putExtra("id",course_id);
                intent.putExtra("name",name);
                intent.putExtra("type",type);
                intent.putExtra("credit",credit);
                intent.putExtra("book",book);
                intent.putExtra("teacher-name",teacherName);
                intent.putExtra("classroom",classroom);
                intent.putExtra("time",time);

                startActivity(intent);
            }
        });
    }
}
