/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konfigurasi;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author genz
 */
public class ConfigLogin {
    //variable username dan password database 
    private String userValid;
    private String passValid;
    
    //variable dari user 
    public String username;
    public String password;
    
    //variable kondisi
    public int kondisi;
    
    // variable database
    Connection conn;
    Statement stmt;
    ResultSet rs;
    
    void cekPassword(){
        if (password.equals(passValid)) {
            kondisi = 1;
        } else {
            kondisi = 0;
        }
    }
    
    //cari password
    void cariPassword(){
        try {
            driver();
            String sql = "select password from user where password='"+password+"'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    passValid = rs.getString(1);
                    cekPassword();
                } else {
                    kondisi = 0;
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal cari password "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    void cekUsername(){
        if (username.equals(userValid)) {
            cariPassword();
        } else {
            kondisi = 0;
        }
    }
    
    //cari username
    public void cariUsername(){
        try {
            driver();
            String sql = "select username from user where username='"+username+"'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    userValid = rs.getString(1);
                    cekUsername();
                } else {
                    kondisi = 0;
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal cari username "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void driver() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
        stmt = conn.createStatement();
    }
}
