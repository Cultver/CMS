package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import cms.app.net.CMSClient;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedetail_electives);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        int type = intent.getIntExtra("type",0);
        float credit = intent.getFloatExtra("credit",0);
        String book = intent.getStringExtra("book");
        String teacherName = intent.getStringExtra("teacher-name");
        String classroom = intent.getStringExtra("classroom");
        String time = intent.getStringExtra("time");
        Log.d("red","detail:"+id+""+name+""+type+""+credit+""+book+""+teacherName+""+classroom+""+time);

        TextView textViewCourse_id = findViewById(R.id.textview_student_course_id);
        TextView textViewName = findViewById(R.id.textview_student_course_name);
        TextView textViewType = findViewById(R.id.textview_student_course_type);
        TextView textViewCredit = findViewById(R.id.textview_student_course_credits);
        TextView textViewBook = findViewById(R.id.textview_student_course_books);
        TextView textViewTeacher = findViewById(R.id.textview_student_course_teachername);
        TextView textViewAddress = findViewById(R.id.textview_student_course_address);
        TextView textViewTime = findViewById(R.id.textview_student_course_classtime);
        Button btn_cancel_select = findViewById(R.id.btn_student_cancel_select);
        Button btn_ensure_select = findViewById(R.id.btn_student_ensure_select);
        if(type==0){
            textViewType.setText("必修");
            btn_cancel_select.setVisibility(Button.GONE);
            btn_ensure_select.setVisibility(Button.GONE);
        }else if(type==1){
            textViewType.setText("选修");
            btn_ensure_select.setVisibility(Button.GONE);
        }else if(type==2){
            textViewType.setText("可选修课程");
            btn_cancel_select.setVisibility(Button.GONE);
        }
        textViewCourse_id.setText(id);
        textViewName.setText(name);
        textViewCredit.setText(""+ credit);//强制类型转换不行
        textViewBook.setText(book);
        textViewTeacher.setText(teacherName);
        textViewAddress.setText(classroom);
        textViewTime.setText(time);
        btn_cancel_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String retMsg = CMSClient.sendAndReceive("query_student_cancel_courses\n"+GlobalConfig.currentUser+"\n"+id);
                Log.d("red","query_student_cancel_courses\n"+GlobalConfig.currentUser+"\n"+id);
                finish();
            }
        });
        btn_ensure_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String retMsg = CMSClient.sendAndReceive("query_student_courses\n"+GlobalConfig.currentUser+"\n"+id);
                Log.d("red","query_student_courses\n"+GlobalConfig.currentUser+"\n"+id);
                finish();
            }
        });
    }
}
