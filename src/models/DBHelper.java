/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import utils.CustomHelper;

/**
 *
 * @author Ahmad Hanafi
 */
public class DBHelper {
    public static Connection connect;
    private static CustomHelper helper;
    private static String myPrefixTable, dbUser, dbPass, dbName;
    
    public DBHelper() {
        helper = new CustomHelper();
        myPrefixTable = "2017102020";
        dbUser = "root";
        dbPass = "";
        dbName = "travel_" + myPrefixTable;
    }
    
    public static Connection connection() throws SQLException{
        if(connect == null) {
            try{
                //Define some credentials to connecting database
                String connectionUrl = "jdbc:mysql://localhost:3306/" + dbName;
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connect = (Connection) DriverManager.getConnection(connectionUrl, dbUser, dbPass);
                
                System.out.println("Successfully connected to database!");
            }catch(SQLException e) {
                System.out.println("Can't connect to database! Error: " + e.getMessage());
            }
        }
        
        return connect;
    }
    
    public boolean Insert(String table, String columns, String values) {
        try{
            table += "_" + myPrefixTable;
            String query = "INSERT INTO " + table + "(" + columns + ") VALUES (" + values + ")";
            //System.out.print(query);
            PreparedStatement stmt = connection().prepareStatement(query);
            return stmt.execute();
        } catch(SQLException ex) {
            helper.showMessage(null, ex.getMessage());
            return false;
        }
    }
    
    public boolean Update(String table, String data, String condition) {
        try{
            table += "_" + myPrefixTable;
            String query = "UPDATE " + table + " SET " + data + " WHERE " + condition;
            //helper.showMessage(null, query);
            PreparedStatement stmt = connect.prepareStatement(query);
            return stmt.execute();
        } catch(SQLException ex) {
            helper.showMessage(null, ex.getMessage());
            return false;
        }
    }
    
    public void Delete(String table, String condition) {
        try{
            table += "_" + myPrefixTable;
            String query = "DELETE FROM " + table + " WHERE " + condition;
            
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.execute();
        } catch(SQLException ex) {
            helper.showMessage(null, ex.getMessage());
        }
    }
    
    public ResultSet GetData(String table) {
        try{
            table += "_" + myPrefixTable;
            String query = "SELECT * FROM " + table;
            
            Statement stmt = connection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch(SQLException ex) {
            helper.showMessage(null, ex.getMessage());
            return null;
        }
    }
    
    public ResultSet GetDataWhere(String table, String whereConditions) {
        try{
            table += "_" + myPrefixTable;
            String query = "SELECT * FROM " + table + " WHERE " + whereConditions;
            //helper.showMessage(null, query);
            Statement stmt = connection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch(SQLException ex) {
            helper.showMessage(null, ex.getMessage());
            return null;
        }
    }
    
    public ResultSet GetDataBy(String table, String columnName, String value) {
        try{
            table += "_" + myPrefixTable;
            String query = "SELECT * FROM " + table + " WHERE " + columnName + " = '"+ value +"'";
            Statement stmt = connection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch(SQLException ex) {
            helper.showMessage(null, ex.getMessage());
            return null;
        }
    }
    
    public ResultSet GetDataFilterByDate(String table, String columnName, String startDate, String endDate) {
        try{
            table += "_" + myPrefixTable;
            String query = "SELECT * FROM " + table + " WHERE " + columnName + " BETWEEN '"+startDate+"' AND '"+endDate+"'";
            helper.showMessage(null, query);
            Statement stmt = connection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch(SQLException ex) {
            helper.showMessage(null, ex.getMessage());
            return null;
        }
    }
    
    public ResultSet GetSearchData(String table, String columns, String value) {
        try{
            table += "_" + myPrefixTable;
            String query = "SELECT * FROM " + table;
            
            String column = " WHERE ";
            if(columns.contains(",")) {
                String[] cols = columns.split(",");
                for(int i=0; i< cols.length; i++) {
                    column += cols[i] + " LIKE '%"+value+"%'";
                    if(i < cols.length - 1) {
                        column += " OR ";
                    }
                }
            } else {
                column += columns + " LIKE '%"+value+"%'";
            }
            
            query += column;
            //helper.showMessage(null, query);
            
            Statement stmt = connection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch(SQLException ex) {
            helper.showMessage(null, ex.getMessage());
            return null;
        }
    }
    
    public ResultSet GetDataWithCutomQuery(String query) {
        try{
            Statement stmt = connection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch(SQLException ex) {
            helper.showMessage(null, ex.getMessage());
            return null;
        }
    }
    
    public String generateCode(String tableName, String columnName, String prefixCode) {
        String resultCode = "";
        try {
            tableName += "_" + myPrefixTable;
            
            String query = "SELECT MAX("+columnName+") AS code FROM "+tableName+" LIMIT 1";
            Statement stmt = (Statement) connection().createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            //if there are data
            if(result.next()) {
                
                String lastCode = result.getString("code");
                if(lastCode != null) {
                    String[] splitedCode = lastCode.split("-");
                
                    int lastIndexCode = Integer.parseInt(splitedCode[1]);
                    String newIndex = Integer.toString(lastIndexCode + 1);
                    String addZero = "";

                    switch (newIndex.length()) {
                        case 1:
                            addZero = "000";
                            break;
                        case 2:
                            addZero = "00";
                            break;
                        case 3:
                            addZero = "0";
                            break;
                        default:
                            break;
                    }

                    resultCode = prefixCode + "-" + addZero + newIndex;
                } else {
                    resultCode = prefixCode + "-0001";
                }                
                
            } else {
                resultCode = prefixCode + "-0001";
            }
            
        } catch(SQLException e) {
            helper.showMessage(null, e.getMessage());
        }
        
        return resultCode;
    }
}
