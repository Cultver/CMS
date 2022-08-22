package cms.app;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cms.app.net.CMSClient;
    public class TeacherDetailActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_teacher_coursedetail);
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
            String classname = intent.getStringExtra("classname");
            Log.d("red","detail:"+id+""+name+""+type+""+credit+""+book+""+teacherName+""+classroom+""+time+"\n"+classname);

            TextView textViewCourse_id = findViewById(R.id.textview_teacher_course_id);
            TextView textViewName = findViewById(R.id.textview_teacher_course_name);
            TextView textViewType = findViewById(R.id.textview_teacher_course_type);
            TextView textViewCredit = findViewById(R.id.textview_teacher_course_credits);
            TextView textViewBook = findViewById(R.id.textview_teacher_course_books);
            TextView textViewTeacher = findViewById(R.id.textview_teacher_course_teachername);
            TextView textViewAddress = findViewById(R.id.textview_teacher_course_address);
            TextView textViewTime = findViewById(R.id.textview_teacher_course_classtime);
            TextView textViewClass = findViewById(R.id.textview_teacher_course_class1);
            Button btn_edit = findViewById(R.id.btn_teacaher_edit);
            Button btn_delete = findViewById(R.id.btn_teacher_delete);
            Button btn_select = findViewById(R.id.btn_teacher_select_student);
            LinearLayout line_class = findViewById(R.id.line_class);
            if(type==0){
                textViewType.setText("必修");
                btn_select.setVisibility(Button.GONE);
            }else if(type==1){
                textViewType.setText("选修");
                line_class.setVisibility(View.GONE);
            }
            textViewCourse_id.setText(id);
            textViewName.setText(name);
            textViewCredit.setText(""+ credit);//强制类型转换不行
            textViewBook.setText(book);
            textViewTeacher.setText(teacherName);
            textViewAddress.setText(classroom);
            textViewTime.setText(time);
            textViewClass.setText(classname);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String retMsg = CMSClient.sendAndReceive("delete_teacher_course\n"+id+"\n"+GlobalConfig.currentUser);
                    Log.d("red","delete_teacher_course\n"+id+"\n"+GlobalConfig.currentUser);
                    finish();

                }
            });
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TeacherDetailActivity.this,TeacherEditCourseActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            btn_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TeacherDetailActivity.this,TeacherSelectActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

