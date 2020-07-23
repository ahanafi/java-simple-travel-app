/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;

/**
 *
 * @author Ahmad Hanafi
 */
public class PengembalianModel {
    private static DBHelper dbHelper;
    private static final String tableName = "tbl_travel_kembali";
    
    public static String idKembali;
    public static String noSewa;
    public static String tglKembali;
    
    public PengembalianModel() {
        dbHelper = new DBHelper();
    }
    
    public void setValue(String no_sewa, String tanggal) {
        noSewa = no_sewa;
        tglKembali = tanggal;
    }
    
    public void setID(String id) {
        idKembali = id;
    }
    
    public boolean insertPengembalian() {
        String columns = "no_sewa, tgl_kembali";
        String values = "'" + noSewa +"', ";
                values += "'" + tglKembali +"'";
        
        return dbHelper.Insert(tableName, columns, values);
    }
    
    public boolean updatePengembalian() {    
        String data = "no_sewa = '" + noSewa + "', ";
        data += "tgl_kembali = '" + tglKembali + "'";
        
        String whereCondition = "no_Sewa = '" + noSewa + "'";
        return dbHelper.Update(tableName, data, whereCondition);
    }
    
    public ResultSet getAllPengembalian() {
        return dbHelper.GetData(tableName);
    }
    
    public ResultSet getPengembalianByNoSewa(String kode) {
        return dbHelper.GetDataBy(tableName, "no_sewa", kode);
    }
    
    public ResultSet getPengembalianByID(String id) {
        return dbHelper.GetDataBy(tableName, "id_kembali", id);
    }
    
    public void deletePengembalian(String noSewa) {
        dbHelper.Delete(tableName, "no_sewa = '" + noSewa + "'");
    }
    
    public ResultSet getPengembalianData() {
        String pengembalianTable = tableName + "_2017102020";
        String sewaTable = "tbl_travel_sewa_2017102020";
        String travelTable = "tbl_travel_2017102020";
        
        String SqlJoin = "SELECT * FROM "+pengembalianTable+" JOIN "+sewaTable+" USING (no_sewa)";
        SqlJoin += " JOIN "+travelTable+" USING (kode_travel)";
        return dbHelper.GetDataWithCutomQuery(SqlJoin);
    }
    
    public ResultSet getSearch(String val) {
        return dbHelper.GetSearchData(tableName, "no_sewa", val);
    }
    
    public ResultSet getFilterData(String start, String end) {
        return dbHelper.GetDataFilterByDate(tableName, "tgl_kembali", start, end);
    }
}
