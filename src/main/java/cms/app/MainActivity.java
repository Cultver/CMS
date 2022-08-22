package cms.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cms.app.net.CMSClient;

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        getSupportActionBar().hide();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        final EditText editTextUsername = findViewById(R.id.edit_logon_username);
        final EditText editTextPassword = findViewById(R.id.edit_logon_pwd);
        Button btnReg = findViewById(R.id.Button_logon_reg);
        Button btnLogin = findViewById(R.id.Button_logon_login);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                if (username.isEmpty()) {
                    dlg.setTitle("错误");
                    dlg.setMessage("请输入用户名！");
                    dlg.show();
                    return;
                }
                if (password.isEmpty()) {
                    dlg.setTitle("错误");
                    dlg.setMessage("请输入密码！");
                    dlg.show();
                    return;
                }
                String sendMsg = "logon\n" + username + "\n" + password;
                String retMsg = CMSClient.sendAndReceive(sendMsg);

                String[] arr = retMsg.split("\n");
                if (arr[0].equals("Y")) {
                    int userType = Integer.parseInt(arr[1]);
                    GlobalConfig.currentUser = username;
                    if (userType == 0) {
                        Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                        startActivity(intent);
                    } else if (userType == 1) {
                        Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
                        startActivity(intent);
                    } else if (userType == 2) {
                        Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                        startActivity(intent);
                    }
                } else if (arr[0].equals("N")) {
                    String errMsg = arr[1];
                    // AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("登录失败");
                    dlg.setMessage(errMsg);
                    dlg.show();
                }
            }
        });
    }
}
