package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TeacherEditCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_edit_course);
        getSupportActionBar().hide();
    }
}
