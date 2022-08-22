package cms.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentSelectCourseAdapter extends ArrayAdapter<CourseInfo> {
    int resId;

    public StudentSelectCourseAdapter(Context context, int resource, List<CourseInfo> objects) {
        super(context, resource, objects);
        this.resId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CourseInfo courseInfo = getItem(position);
        View v = LayoutInflater.from(getContext()).inflate(resId, parent, false);
        TextView textViewId = v.findViewById(R.id.list_item_student_select_courses_id);
        TextView textViewName = v.findViewById(R.id.list_item_student_select_courses_name);
        textViewId.setText(courseInfo.id);
        textViewName.setText(courseInfo.name);

        return v;
    }
}
