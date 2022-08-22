package cms.app;

import android.app.AlertDialog;
import android.content.Context;

public class Util {
    public  static  void showErrorMsgDig(Context context,String msg){
        AlertDialog.Builder dig = new AlertDialog.Builder(context);
        dig.setTitle("错误");
        dig.setMessage(msg);
        dig.show();
    }
}
