package cms.app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cms.app.net.User;

import java.util.List;

class TeacherSelectStudentAdapter extends ArrayAdapter<User> {
    int resid;
    public TeacherSelectStudentAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.resid=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resid,parent,false);

        TextView textViewnumber=view.findViewById(R.id.list_item_student_select_courses_id);
        TextView textViewname=view.findViewById(R.id.list_item_student_select_courses_name);
        textViewnumber.setText(user.getUsername());
        textViewname.setText(user.getName());
        return view;
    }
}
