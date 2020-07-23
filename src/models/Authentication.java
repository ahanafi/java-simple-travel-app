/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import utils.CustomHelper;

/**
 *
 * @author Ahmad Hanafi
 */
public class Authentication {
    private static DBHelper dbHelper;
    private static CustomHelper helper;
    private static UserModel userModel;
    
    public Authentication() {
        dbHelper = new DBHelper();
        userModel = new UserModel();
        helper = new CustomHelper();
    }
    
    public boolean doLogin(String user, String pass) {
        boolean isAuthenticated = false;
        if(! user.equals("") && ! pass.equals("")) {
            try{
                String bindingWhere = "username = '"+user+"' AND password = MD5('"+pass+"')";

                if(! bindingWhere.equals("")) {
                    ResultSet getUser = dbHelper.GetDataWhere("tbl_user", bindingWhere);
                    if(getUser.next()) {
                        isAuthenticated = true;
                    }
                }            
            } catch(SQLException e) {
                helper.showMessage(null, e.getMessage());
            }
        }
        return isAuthenticated;
    }
}
