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
public class ConfigStok {
    public String idMotif;
    public String idUkuran;
    public int harga;
    public int stokTambah;
    public int stokLama;
    public int stokBaru;
    
    // variable database
    Connection conn;
    Statement stmt;
    ResultSet rs;
    
    public void run(){
        getStok();
    }
    
    public void updateStok(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "UPDATE stok_harga SET stok='"+stokBaru+"', harga='"+harga+"' WHERE id_motif='"+idMotif+"' AND id_ukuran='"+idUkuran+"'";
            int i = stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Berhasil Tambah Stok & Update Harga Barang");
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Tambah Stok & Update Harga Barang : "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void setStokBaru(){
        stokBaru = stokTambah + stokLama;
        updateStok();
    }

    public void getStok(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "select stok from stok_harga where id_motif='"+idMotif+"' AND id_ukuran='"+idUkuran+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    stokLama = Integer.parseInt(rs.getString(1));
                    setStokBaru();
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // cek 
    
    public void getHargaCek(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "select harga from stok_harga where id_motif='"+idMotif+"' AND id_ukuran='"+idUkuran+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    harga= Integer.parseInt(rs.getString(1));
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void getStokCek(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "select stok from stok_harga where id_motif='"+idMotif+"' AND id_ukuran='"+idUkuran+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    stokLama = Integer.parseInt(rs.getString(1));
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
