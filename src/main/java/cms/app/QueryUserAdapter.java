package cms.app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import cms.app.net.User;

public class QueryUserAdapter extends ArrayAdapter<User> {
    int resId;
    public QueryUserAdapter(Context context, int resource, List<User> objects) {
            super(context,resource,objects);
            this.resId = resource;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
           User user = getItem(position);
            View v = LayoutInflater.from(getContext()).inflate(resId,parent,false);
            TextView textView= v.findViewById(R.id.listview_query_user);
            TextView textViewId = v.findViewById(R.id.listview_query_id);
            textViewId.setText(user.getUsername());
            textView.setText(user.getName());

            return v;
        }


}
