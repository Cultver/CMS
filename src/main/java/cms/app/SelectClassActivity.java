package cms.app;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import cms.app.net.CMSClient;

public class SelectClassActivity extends AppCompatActivity {
    TextView lastSelectTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);
        getSupportActionBar().hide();

        ListView listView = findViewById(R.id.btn_class_list_select);
        String retMsg = CMSClient.sendAndReceive("querry_all_classes");
        String[] allClasses = retMsg.split("\n");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectClassActivity.this, android.R.layout.simple_list_item_1, allClasses);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                Log.d("cms_debug", "list view item clicked:" + textView.getText());

                if (lastSelectTextView != null) {
                    lastSelectTextView.setBackgroundColor(Color.WHITE);
                }
                textView.setBackgroundColor(Color.YELLOW);
                lastSelectTextView = textView;
            }
        });
        Button btnSelect = findViewById(R.id.btn_reg_select);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastSelectTextView != null) {
                    String data_return = lastSelectTextView.getText().toString();
                    Log.d("cms_debug", "class selected" + data_return);
                    Intent intent = new Intent();
                    intent.putExtra("data_return", data_return);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Log.d("cms_debug", "no class selected");
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SelectClassActivity.this);
                    dialogBuilder.setTitle("错误");
                    dialogBuilder.setMessage("没有选择班级!");
                    dialogBuilder.show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String data_return = lastSelectTextView.getText().toString();
        Log.d("cms_debug", "class selected" + data_return);
        Intent intent = new Intent();
        intent.putExtra("data_turn", data_return);
        setResult(RESULT_OK, intent);
        finish();

    }
}




