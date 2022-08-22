package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        getSupportActionBar().hide();

        Button btnMyCourses = findViewById(R.id.btn_student_mycourses);
        btnMyCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this,StudentCourseActivity.class);
                startActivity(intent);
            }
        });
        Button btnMyShedules = findViewById(R.id.btn_student_myschedules);
        btnMyShedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(StudentActivity.this,StudentSchedulesActivity.class);
                startActivity(intent);
            }
        });
        Button btnSelectCourses = findViewById(R.id.btn_student_select_courses);
        btnSelectCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(StudentActivity.this,StudentSelectCoursesActivity.class);
                startActivity(intent);
            }
        });
    }
}
