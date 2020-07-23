/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Component;
import java.sql.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JOptionPane;

/**
 *
 * @author Ahmad Hanafi
 */
public class CustomHelper {
    public void showMessage(Component comp, String text){
        JOptionPane.showMessageDialog(comp, text);
    }
    
    public int confirmDialog(Component comp, String message, String title) {
        int confirm = JOptionPane.showConfirmDialog(comp, message, title, JOptionPane.YES_NO_OPTION);
        return confirm;
    }
    
    public String getGender(String kode) {
        String genderResult = "";
        if(! kode.equals("") && ! kode.equals("-- Pilih Jenis Kelamin --"))  {
            if(kode.equals("Laki-laki")) {
                genderResult = "L";
            } else if(kode.equals("Perempuan")) {
                genderResult = "P";
            } else if(kode.equals("P")) {
                genderResult = "Perempuan";
            } else if(kode.equals("L")) {
                genderResult = "Laki-laki";
            }
        }
        
        return genderResult;
    }
    
    public String convertToRupiah(String rupiah) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        //formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(rupiah);
    }
}
