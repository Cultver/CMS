package cms.app;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import cms.app.net.CMSClient;

import static cms.app.R.id.edit_teacher_create_course_time1;

public class TeacherCreateCourseActivity extends AppCompatActivity implements View.OnClickListener{
    int type=-1;
    EditText editTextid;
    EditText editTextname;
    RadioButton xuanxiu;
    RadioButton bixiu;
    EditText editTextcredit;
    EditText editTextplace;

    TextView textViewclass;
    EditText editTextbook;
    Button ensure;
    Button select;
    View  linearLayoutclass;
    Button buttonselecttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_teacher_create_course);
        editTextid=findViewById(R.id.edit_teacher_create_course_id);
        editTextcredit=findViewById(R.id.edit_teacher_create_course_credit);
        editTextname=findViewById(R.id.edit_teacher_create_course_name);
        editTextplace=findViewById(R.id.edit_teacher_create_course_place);
        //EditText editTextime = findViewById(R.id.edit_teacher_create_course_time1);
        editTextbook=findViewById(R.id.edit_teacher_create_course_book);
        xuanxiu=findViewById(R.id.radio_teacher_create_course_type_x);
        bixiu=findViewById(R.id.radio_teacher_create_course_type_b);

        textViewclass=findViewById(R.id.tview_teacher_create_class);
        linearLayoutclass=findViewById(R.id.layout_teacher_create_class);
        buttonselecttime=findViewById(R.id.btn_teacher_create_select_time);
        buttonselecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherCreateCourseActivity.this,StudentSchedulesActivity.class);
                intent.putExtra("type","3");
                startActivityForResult(intent, 1);
            }
        });
        xuanxiu.setOnClickListener(this);
        bixiu.setOnClickListener(this);

        linearLayoutclass.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.radio_teacher_create_course_type_b:

                linearLayoutclass.setVisibility(View.VISIBLE);
                type = 0;
                break;
            case R.id.radio_teacher_create_course_type_x:
                linearLayoutclass.setVisibility(View.GONE);
                type = 1;
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void createcourse() {
        AlertDialog.Builder dlgbuilder=new AlertDialog.Builder(TeacherCreateCourseActivity.this);
        String id=editTextid.getText().toString();
        String name=editTextname.getText().toString();
        String credit=editTextcredit.getText().toString();
        String place=editTextplace.getText().toString();
        //String time=editTexttime.getText().toString();
        String classroom=textViewclass.getText().toString();
        String book=editTextbook.getText().toString();
        if (type==-1||id.isEmpty()||name.isEmpty()||credit.isEmpty()||place.isEmpty()||book.isEmpty()){
            dlgbuilder.setTitle("错误");
            dlgbuilder.setMessage("请填写完整");
            dlgbuilder.show();
            return;
        }
        if(type==0&&classroom.isEmpty()){
            dlgbuilder.setTitle("错误");
            dlgbuilder.setMessage("请填写完整");
            dlgbuilder.show();
            return;
        }
        String msg="create_course\n"+id+"\n"+name+"\n"+type+"\n"+credit+"\n"+book+"\n"+GlobalConfig.currentUser+"\n"+classroom+"\n"+"\n"+place;
        String retmsg= CMSClient.sendAndReceive(msg);
        String[] arr=retmsg.split("\n");
        if(arr[0].equals("Y")){

            dlgbuilder.setMessage(arr[1]);
            dlgbuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            });

        }else if(arr[0].equals("N")){
            dlgbuilder.setTitle("错误");
            dlgbuilder.setMessage(arr[1]);
        }
        dlgbuilder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if(resultCode==1){
                    String b1=data.getStringExtra("a1");

                    textViewclass.setText(b1);

                }
                if(resultCode==2){
                    String b2=data.getStringExtra("a2");

                    //editTexttime.setText(b2);
                }
                break;
            default:
                break;
        }
    }
}
