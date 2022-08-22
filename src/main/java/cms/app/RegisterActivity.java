package cms.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import cms.app.net.CMSClient;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    RadioButton radioButtonStudent;
    RadioButton radioButtonTeacher;
    Button btnSelectClass;
    Button btnRegister;
    View layoutSelectClass;
    TextView textViewClass;
    EditText editTextUsername;
    EditText editTextName;
    EditText editTextPhone;
    EditText editTextPwd;
    EditText editTextPwd1;
    int Usertype = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        radioButtonStudent = findViewById(R.id.radio_reg_stu);
        radioButtonTeacher = findViewById(R.id.radio_reg_tea);
        btnSelectClass = findViewById(R.id.btn_reg_select_class);
        btnRegister = findViewById(R.id.btn_reg_register);
        layoutSelectClass = findViewById(R.id.layout_reg_select_class);
        textViewClass = findViewById(R.id.test_view_class);
        editTextUsername = findViewById(R.id.edit_reg_username);
        editTextPhone = findViewById(R.id.edit_reg_phone);
        editTextName = findViewById(R.id.edit_reg_name);
        editTextPwd = findViewById(R.id.edit_reg_pwd);
        editTextPwd1 = findViewById(R.id.edit_reg_pwd2);
        textViewClass.setOnClickListener(this);
        radioButtonStudent.setOnClickListener(this);
        radioButtonTeacher.setOnClickListener(this);
        btnSelectClass.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
       /* btnSelectClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,SelectClassActivity.class);
                intent.putExtra("data_return","from data");
                startActivityForResult(intent,1);
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("test","requestCode"+requestCode+"resultCode"+resultCode);
      super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String returnedData = data.getStringExtra("data_return" );
                    //Log.d("RegisterActivity","kl"+returnedData);
                    textViewClass.setText(returnedData);
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_reg_stu:
                Log.d("cmsdbg","user click radio button student");
                layoutSelectClass.setVisibility(View.VISIBLE);
                Usertype=0;
                break;
            case R.id.radio_reg_tea:
                Log.d("cmsdbg","user click radio button teacher");
                layoutSelectClass.setVisibility(View.GONE);
                Usertype=1;
                break;
            case R.id.btn_reg_select_class:
                Log.d("cmsdbg","user click radio select class");
                Intent intent = new Intent(this,SelectClassActivity.class);
                //startActivity(intent);
                startActivityForResult(intent,1);
                break;
            case R.id.btn_reg_register:
                Log.d("cmsdbg","user click radio button register");
                registerUser();
                break;
            default:
                break;

        }
    }
    private void registerUser()
    {
       /* if(radioButtonTeacher.isSelected())
            type=1;
        if(radioButtonStudent.isSelected())
            type=0;
        String username = editTextUsername.getText().toString();*/
        if(Usertype == -1)
        {
            Util.showErrorMsgDig(this,"未选择用户类型！");
            return;
        }
        String username = editTextUsername.getText().toString();
        if(username.isEmpty())
        {
            Util.showErrorMsgDig(this,"未输入用户名！");
            return;
        }
        String name = editTextName.getText().toString();
        if(name.isEmpty()){
            Util.showErrorMsgDig(this,"未输入姓名！");
            return;
        }
        String phone = editTextPhone.getText().toString();
        if(phone.isEmpty()){
            Util.showErrorMsgDig(this,"未输入手机号！");
            return;
        }
        String classname = textViewClass.getText().toString();
        if(classname.isEmpty()&&Usertype==0){
            Util.showErrorMsgDig(this,"未选择班级！");
            return;
        }
        String password = editTextPwd.getText().toString();
        String pwd = editTextPwd1.getText().toString();
        if(password.isEmpty()||pwd.isEmpty()){
            Util.showErrorMsgDig(this,"未输入密码！");
            return;
        }
        if(!password.equals(pwd)){
        Util.showErrorMsgDig(this,"两次密码输入不一致！");
             return;
        }

        String sendMsg = "register_user\n"+Usertype+"\n"+username+"\n"+name+"\n"+phone+"\n"+classname+"\n"+password;
        String retMsg = CMSClient.sendAndReceive(sendMsg);
        Log.d("red","register_user:"+sendMsg);


        String[] arr = retMsg.split("\n") ;
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        if(arr[0].equals("Y")){
            dlg.setMessage(arr[1]);
            dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            });
        }else if(arr[0].equals("N")){
            dlg.setTitle("错误");
            dlg.setMessage(arr[1]);
        }
        dlg.show();
    }
}
