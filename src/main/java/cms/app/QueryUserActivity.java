package cms.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import cms.app.net.CMSClient;
import cms.app.net.QueryWaitingApprovalResult;
import cms.app.net.User;

public class QueryUserActivity extends AppCompatActivity {
    ListView listView;
    Button query;
    EditText editTextQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_query);
        listView = findViewById(R.id.list_admin_query);
        query = findViewById(R.id.btn_query_admin);
        editTextQuery = findViewById(R.id.edit_query_admin);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        final String retMsg = CMSClient.sendAndReceive("query_user");
        Gson gson = new Gson();
        final QueryWaitingApprovalResult result = gson.fromJson(retMsg,QueryWaitingApprovalResult.class);
        ArrayList<User> users = new ArrayList<>() ;
        for(User c : result.getUsers()){
            users.add(c);
        }
        QueryUserAdapter adp = new QueryUserAdapter(this,R.layout.listview_item_query_user,users);
        listView.setAdapter(adp);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d( "red","position"+position);
                ArrayList<User> users = result.getUsers();
                User c = users.get(position);

                String username =c.getUsername();
                int type =c.getType();
                String sendMsg = "select_user"+"\n"+username;
                String retMsg = CMSClient.sendAndReceive(sendMsg);
                String[] arr = retMsg.split("\n");
                String name  = arr[0];
                String phone = arr[1];
                String classroom  = arr[2];
                String password = arr[3];
                Intent intent = new Intent(QueryUserActivity.this,QueryUserDetailActivity.class);

                intent.putExtra("name",name);
                intent.putExtra("type",type);
                intent.putExtra("username",username);
                intent.putExtra("phone",phone);
                intent.putExtra("classroom",classroom);
                intent.putExtra("password",password);

                startActivity(intent);
            }
        });
        query.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String msg = editTextQuery.getText().toString();
                final String retMsg = CMSClient.sendAndReceive("select_user1\n%"+msg+"%");
                Gson gson = new Gson();
                final QueryWaitingApprovalResult result = gson.fromJson(retMsg,QueryWaitingApprovalResult.class);
                ArrayList<User> users = new ArrayList<>() ;
                for(User c : result.getUsers())
                {
                    users.add(c);
                }
                QueryUserAdapter adp = new QueryUserAdapter(QueryUserActivity.this,R.layout.listview_item_query_user,users);
                listView.setAdapter(adp);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Log.d("red", "position" + position);
                        ArrayList<User> users = result.getUsers();
                        User c = users.get(position);

                        String username = c.getUsername();
                        int type = c.getType();
                        String sendMsg = "select_user" + "\n" + username;
                        String retMsg = CMSClient.sendAndReceive(sendMsg);
                        String[] arr = retMsg.split("\n");
                        String name = arr[0];
                        String phone = arr[1];
                        String classroom = arr[2];
                        String password = arr[3];
                        Intent intent = new Intent(QueryUserActivity.this, QueryUserDetailActivity.class);

                        intent.putExtra("name", name);
                        intent.putExtra("type", type);
                        intent.putExtra("username", username);
                        intent.putExtra("phone", phone);
                        intent.putExtra("classroom", classroom);
                        intent.putExtra("password", password);

                        startActivity(intent);
                    }
                });
            }

        });
    }

}
