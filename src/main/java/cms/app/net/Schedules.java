/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.app.net;


import java.io.Serializable;

public class Schedules  {
    int number;
    String mon;
    String tues;
    String wed;
    String thur;
    String fri;
    String sat;
    String sun;

    public Schedules(int number, String mon, String tues, String wed, String thur, String fri, String sat, String sun) {
        this.number = number;
        this.mon = mon;
        this.tues = tues;
        this.wed = wed;
        this.thur = thur;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTues() {
        return tues;
    }

    public void setTues(String tues) {
        this.tues = tues;
    }

    public String getWed() {
        return wed;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public String getThur() {
        return thur;
    }

    public void setThur(String thur) {
        this.thur = thur;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }
}