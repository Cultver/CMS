package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import cms.app.net.CMSClient;
import cms.app.net.QueryWaitingApprovalResult;
import cms.app.net.User;

public class TeacherSelectActivity extends AppCompatActivity {
    ListView listView;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_select_class);
        getSupportActionBar().hide();
        listView=findViewById(R.id.list_class_list_select);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
    }
    @Override
    protected void onStart() {
        super.onStart();
        final String retmsg = CMSClient.sendAndReceive("quary_select_course_student\n" +id);
        Gson gson = new Gson();
        final QueryWaitingApprovalResult user = gson.fromJson(retmsg, QueryWaitingApprovalResult.class);
        ArrayList<User> student = new ArrayList<>();
        for (User c : user.getUsers()) {
            student.add(c);
        }

        TeacherSelectStudentAdapter adp = new TeacherSelectStudentAdapter(TeacherSelectActivity.this, R.layout.listview_item_student_select_course, student);

        listView.setAdapter(adp);

    }
}
