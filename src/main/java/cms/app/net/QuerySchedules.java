package cms.app.net;

import java.util.ArrayList;

public class QuerySchedules {
    boolean success;
    String msg;
    ArrayList<Schedules> courses;
    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<Schedules> getcourse() {
        return courses;
    }
}
