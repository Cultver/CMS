package cms.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ApprovalAdapter extends ArrayAdapter<UserApprovalSummary> {
    int resId;
    public ApprovalAdapter(Context context, int resource, List<UserApprovalSummary> objects) {
        super(context, resource, objects);
        this.resId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserApprovalSummary summary = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resId,parent,false);
        TextView textViewType = view.findViewById(R.id.list_item_approval_type);
        TextView textViewUername = view.findViewById(R.id.list_item_approval_username);
        TextView textViewName = view.findViewById(R.id.list_item_approval_name);
        if(summary.type==0){
            textViewType.setText("学生");
        }else  if (summary.type == 1){
            textViewType.setText("教师");
        }
        textViewUername.setText(summary.username);
        textViewName.setText(summary.name);
        return view;
    }
}
