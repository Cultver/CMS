package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        getSupportActionBar().hide();

        Button btnMyCourse = findViewById(R.id.btn_teacher_mycourses);
        btnMyCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherActivity.this,TeacherCourseActivity.class);
                startActivity(intent);
            }
        });
        Button btnMyschedule = findViewById(R.id.btn_teacher_myschedules);
        btnMyschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherActivity.this,TeacherScheduleActivity.class);
                startActivity(intent);
            }
        });
    }
}
