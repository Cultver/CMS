/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.app.net;


import java.util.ArrayList;

/**
 *
 * @author WMH
 */
public class QueryWaitingApprovalResult {
    boolean success;
    String msg;
    ArrayList<User> users;

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
