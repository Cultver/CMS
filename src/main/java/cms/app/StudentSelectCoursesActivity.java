package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import cms.app.net.CMSClient;
import cms.app.net.QueryStudentAllCourseResult;

public class StudentSelectCoursesActivity extends AppCompatActivity
{
    ListView listView;
    EditText editText;
    Button btnselect;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_select_courses);
        getSupportActionBar().hide();

    }

    @Override
    protected void onStart()
    {
        editText = findViewById(R.id.edit_student_query);
        listView = findViewById(R.id.listview_student_select_course);
        btnselect = findViewById(R.id.btn_student_query);
        super.onStart();
        String retMsg = CMSClient.sendAndReceive("query_student_select_courses\n"+GlobalConfig.currentUser);
        Log.d("red ","query_student_select_courses\n"+GlobalConfig.currentUser);
        Gson gson = new Gson();
        final QueryStudentAllCourseResult result = gson.fromJson(retMsg,QueryStudentAllCourseResult.class);
        ArrayList<CourseInfo> courses = new ArrayList<>() ;
        for(CourseInfo c : result.getCourses())
        {
            courses.add(c);
        }
        StudentSelectCourseAdapter adp = new StudentSelectCourseAdapter(this, R.layout.listview_item_student_select_course, courses);

        listView.setAdapter(adp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("red", "position" + position);
                ArrayList<CourseInfo> courses = result.getCourses();
                CourseInfo c = courses.get(position);
                String course_id = c.getId();
                String name = c.getName();
                int type = 2;
                float credit =c.getCredit();
                String book =c.getBook();
                String teacherName = c.getTeacherName();
                String classroom = c.getClassroom();
                String time = c.getTime();
                Intent intent = new Intent(StudentSelectCoursesActivity.this,CourseDetailActivity.class);
                intent.putExtra("id",course_id);
                intent.putExtra("name",name);
                intent.putExtra("type",2);
                intent.putExtra("credit",credit);
                intent.putExtra("book",book);
                intent.putExtra("teacher-name",teacherName);
                intent.putExtra("classroom",classroom);
                intent.putExtra("time",time);
                startActivity(intent);
                Log.d("red","detail:"+id+"\n"+name+"\n"+type+"\n"+credit+"\n"+book+"\n"+teacherName+"\n"+classroom+"\n"+time);
            }
        });
        btnselect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String msg = editText.getText().toString();
                Log.d("red",msg);
                final String  retMsg  =CMSClient.sendAndReceive("query_student_select\n%"+GlobalConfig.currentUser+"%\n%"+msg+"%\n");
                Log.d("red ","query_student_select\n"+GlobalConfig.currentUser+"%\n%"+msg+"%\n");
                Gson gson = new Gson();
                final QueryStudentAllCourseResult result = gson.fromJson(retMsg,QueryStudentAllCourseResult.class);
                ArrayList<CourseInfo> courses = new ArrayList<>() ;
                for(CourseInfo c : result.getCourses())
                {
                    courses.add(c);
                }
                StudentSelectCourseAdapter adp = new StudentSelectCourseAdapter(StudentSelectCoursesActivity.this, R.layout.listview_item_student_select_course, courses);

                listView.setAdapter(adp);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Log.d("red", "position" + position);
                        ArrayList<CourseInfo> courses = result.getCourses();
                        CourseInfo c = courses.get(position);
                        String course_id = c.getId();
                        String name = c.getName();
                        int type = 2;
                        float credit = c.getCredit();
                        String book = c.getBook();
                        String teacherName = c.getTeacherName();
                        String classroom = c.getClassroom();
                        String time = c.getTime();
                        Intent intent = new Intent(StudentSelectCoursesActivity.this, CourseDetailActivity.class);
                        intent.putExtra("id", course_id);
                        intent.putExtra("name", name);
                        intent.putExtra("credit", credit);
                        intent.putExtra("book", book);
                        intent.putExtra("teacher-name", teacherName);
                        intent.putExtra("classroom", classroom);
                        intent.putExtra("time", time);
                        startActivity(intent);
                        Log.d("red", "detail:" + id + "\n" + name + "\n" + type + "\n" + credit + "\n" + book + "\n" + teacherName + "\n" + classroom + "\n" + time);
                    }
                });
            }

        });
    }

}
