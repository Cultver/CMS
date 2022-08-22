package cms.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class TeacherCourseAdapter extends ArrayAdapter<CourseInfo> {
    int resId;
    public TeacherCourseAdapter(Context context, int resource, List<CourseInfo> objects) {
        super(context, resource, objects);
        this.resId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CourseInfo courseInfo = getItem(position);
        View v = LayoutInflater.from(getContext()).inflate(resId,parent,false);
        TextView textViewId = v.findViewById(R.id.listview_teacher_course_id);
        TextView textViewName = v.findViewById(R.id.listview_teacher_course_name);
        TextView textViewType = v.findViewById(R.id.listview_teacher_course_type);
        textViewId.setText(courseInfo.id);
        textViewName.setText(courseInfo.name);
        if(courseInfo.type==0){
            textViewType.setText("必修");
        }else if(courseInfo.type==1){
            textViewType.setText("选修");
        }
        return v;
    }
}
