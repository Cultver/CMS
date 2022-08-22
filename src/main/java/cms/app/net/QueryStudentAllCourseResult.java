/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.app.net;


import java.util.ArrayList;

import cms.app.CourseInfo;

/**
 *
 * @author WMH
 */
public class QueryStudentAllCourseResult {

    boolean success;
    String msg;
    ArrayList<CourseInfo> courses;

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<CourseInfo> getCourses() {
        return courses;
    }
}
