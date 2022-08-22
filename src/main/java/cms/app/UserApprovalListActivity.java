package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import cms.app.net.CMSClient;
import cms.app.net.QueryWaitingApprovalResult;
import cms.app.net.User;

public class UserApprovalListActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_approval_list);
        getSupportActionBar().hide();

         listView = findViewById(R.id.listview_user_approval);

    }

    @Override
    protected void onStart() {
        super.onStart();
        final String retMsg = CMSClient.sendAndReceive("query_waiting_approval_users");
        Gson gson = new Gson();
        final QueryWaitingApprovalResult retObj = gson.fromJson(retMsg,QueryWaitingApprovalResult.class);


        ArrayList<UserApprovalSummary> listApproval = new ArrayList<>();
        if(retObj.isSuccess()){
            for(User u:retObj.getUsers()){
                listApproval.add(new UserApprovalSummary(u.getType(),u.getUsername(),u.getName()));
            }
        }

        ApprovalAdapter adp = new ApprovalAdapter(this,R.layout.listview_item_user_approval,listApproval);

        listView.setAdapter(adp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("red","position:"+position);
                ArrayList<User> users = retObj.getUsers();
                User u = users.get(position);
                int type = u.getType();
                String username = u.getUsername();
                String name = u.getName();
                String phone = u.getPhone();
                String classname = u.getClassname();
                Log.d("red",username+""+type+""+name+""+phone+""+classname);
                Intent intent = new Intent(UserApprovalListActivity.this,UserApprovalDetailActivity.class);
                intent.putExtra("type",type);
                intent.putExtra("username",username);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                intent.putExtra("classname",classname);
                startActivity(intent);
            }
        });
    }
}
