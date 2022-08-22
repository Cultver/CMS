/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.app;

/**
 *
 * @author WMH
 */
public class CourseInfo {
    String id;
    String name;
    int type;
    float credit;
    String book;
    String teacherId;
    String teacherName;//教师姓名变量
    String classroom;
    String time;
    String classname;

    public CourseInfo(String id, String name, int type, float credit, String book, String teacherId, String teacherName, String classroom, String time, String classname) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.credit = credit;
        this.book = book;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.classroom = classroom;
        this.time = time;
        this.classname = classname;
    }

    public CourseInfo(String id, String name, int type, float credit, String book, String teacherId, String teacherName, String classroom, String time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.credit = credit;
        this.book = book;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.classroom = classroom;
        this.time = time;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public float getCredit() {
        return credit;
    }

    public String getBook() {
        return book;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getTime() {
        return time;
    }
}
