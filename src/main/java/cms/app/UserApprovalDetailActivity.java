package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cms.app.net.CMSClient;

public class UserApprovalDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_approval_detail);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",0);
        final String username = intent.getStringExtra("username");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String classname = intent.getStringExtra("classname");
        Log.d("red","detail:"+username+""+type+""+name+""+phone+""+classname);

        TextView textViewType = findViewById(R.id.textview_user_approval_type);
        TextView textViewUsername = findViewById(R.id.textview_user_approval_username);
        TextView textViewName = findViewById(R.id.textview_user_approval_name);
        TextView textViewPhone = findViewById(R.id.textview_user_approval_phone);
        TextView textViewClass = findViewById(R.id.textview_user_approval_class);
        View layoutClass = findViewById(R.id.layout_user_approval_class);
        Button btnPass = findViewById(R.id.btn_user_approval_pass);
        Button btnNotPass = findViewById(R.id.btn_user_approval_notpass);

        if(type == 0){
            textViewType.setText("学生");
        }else if(type==1){
            textViewType.setText("教师");
            layoutClass.setVisibility(View.GONE);
        }
        textViewUsername.setText(username);
        textViewName.setText(name);
        textViewPhone.setText(phone);
        textViewClass.setText(classname);
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String retMsg = CMSClient.sendAndReceive("user_approval\n"+username+"\n1");
                Log.d("red","user_approval response:"+retMsg);
                finish();
            }
        });
        btnNotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String retMsg = CMSClient.sendAndReceive("user_approval\n"+username+"\n0");
                Log.d("red","not_user_approval response:"+retMsg);
                finish();
            }
        });
    }
}
