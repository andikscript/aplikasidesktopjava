/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konfigurasi;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 *
 * @author genz
 */
public class ConfigPenjualan { 
    // variable stok
    int stokLama;
    int stokBaru;
    
    //variable Array List Penjualan
    public ArrayList<String> arrayIdPenjualan = new ArrayList<String>();
    public ArrayList<String> arrayIdMotif = new ArrayList<String>();
    public ArrayList<String> arrayIdUkuran = new ArrayList<String>();
    public ArrayList<Integer> arrayJumlah = new ArrayList<Integer>();
    public ArrayList<Integer> arrayHarga = new ArrayList<Integer>();
    public ArrayList<Integer> arrayTotal = new ArrayList<Integer>();
    
    //variable penjualan
    private String idPenjualan;
    public String idMotif;
    public String idUkuran;
    public int jumlah;
    public int harga;
    public int total;
    int nilaiPenjualan;
    
    //variable pelanggan
    private String idPelanggan;
    public String namaPelanggan;
    public String alamatPelanggan;
    public String kontakPelanggan;
    int nilaiPelanggan;
    
    // variable database
    Connection conn;
    Statement stmt;
    ResultSet rs;
    
    //insert penjualan dan update stok
    
    public void runInsert(){
        setHitungPenjualan();
        setIdPenjualan();
        for (int i = 0; i < arrayIdMotif.size(); i++) {
            insertPenjualan(arrayIdPenjualan.get(i), arrayIdMotif.get(i), arrayIdUkuran.get(i), idPelanggan, arrayTotal.get(i));
            insertDetailPenjualan(arrayIdPenjualan.get(i), arrayJumlah.get(i), arrayHarga.get(i), arrayTotal.get(i));
            getStok(arrayIdMotif.get(i), arrayIdUkuran.get(i));
            setStokBaru(arrayJumlah.get(i));
            updateStok(arrayIdMotif.get(i), arrayIdUkuran.get(i));
            setHitungPenjualan();
            setIdPenjualan();
        }
        
        JOptionPane.showMessageDialog(null, "Berhasil Input Transaksi");
    }
    
    // stok
    
    public void updateStok(String idMotif, String idUkuran){
        try {
            driver();
            String sql = "UPDATE stok_harga SET stok='"+stokBaru+"' WHERE id_motif='"+idMotif+"' AND id_ukuran='"+idUkuran+"'";
            int i = stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Update Stok "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void setStokBaru(int jumlah){
        stokBaru = stokLama - jumlah;
    }
    
    public void getStok(String idMotif, String idUkuran){
        try {
            driver();
            String sql = "select stok from stok_harga where id_motif='"+idMotif+"' AND id_ukuran='"+idUkuran+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                stokLama = Integer.parseInt(rs.getString(1));
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Hitung Penjualan"+e.getMessage());
            e.printStackTrace();
        }
    }
    
    //penjualan
    
    public void insertDetailPenjualan(String idPenjualan, int jumlah, int harga, int total){
        try {
            driver();
            String sql = "INSERT INTO detail_penjualan(id_penjualan,jumlah,harga,total) VALUES('"+idPenjualan+"',"+jumlah+","+harga+","+total+")";
            stmt.execute(sql);
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal DetailPenjualan" +e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void insertPenjualan(String idPenjualan, String idMotif, String idUkuran, String idPelanggan,int total){
        try {
            driver();
            String sql = "INSERT INTO penjualan(id_penjualan,id_motif,id_ukuran,id_pelanggan,total) VALUES('"+idPenjualan+"','"+idMotif+"','"+idUkuran+"','"+idPelanggan+"',"+total+")";
            stmt.execute(sql);
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Insert Penjualan"+e.getMessage());
            e.printStackTrace();
        }
    }
    
    //total 
    public void setTotal(){
        total = harga * jumlah;
        arrayTotal.add(total);
    }
    
    //cari harga
    public void getHarga(){
        try {
            driver();
            String sql = "select harga from stok_harga where id_motif='"+idMotif+"' AND id_ukuran='"+idUkuran+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                harga = Integer.parseInt(rs.getString(1));
                arrayHarga.add(harga);
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal getHarga"+e.getMessage());
            e.printStackTrace();
        }
    }
    
    //setIdpenjualan 
    
    public void setIdPenjualan(){
        if (nilaiPenjualan < 10) {
            idPenjualan = "J00"+String.valueOf(nilaiPenjualan);
            arrayIdPenjualan.add(idPenjualan);
        } else if (nilaiPenjualan < 100) {
            idPenjualan = "J0"+String.valueOf(nilaiPenjualan);
            arrayIdPenjualan.add(idPenjualan);
        } else if (nilaiPenjualan < 1000) {
            idPenjualan = "J"+String.valueOf(nilaiPenjualan);
            arrayIdPenjualan.add(idPenjualan);
        }
    }
    
    public void setHitungPenjualan(){
        try {
            driver();
            String sql = "select count(id_penjualan) from penjualan";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                nilaiPenjualan = Integer.parseInt(rs.getString(1)) + 1;
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Hitung Penjualan"+e.getMessage());
            e.printStackTrace();
        }
    }
    
    //pelanggan
    
    public void insertPelanggan(){
        try {
            driver();
            String sql = "INSERT INTO pelanggan(id_pelanggan,nama_pelanggan,alamat,kontak) VALUES('"+idPelanggan+"','"+namaPelanggan+"','"+alamatPelanggan+"','"+kontakPelanggan+"')";
            stmt.execute(sql);
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Pelanggan"+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void setIdPelanggan(){
        if (nilaiPelanggan < 10) {
            idPelanggan = "P0"+String.valueOf(nilaiPelanggan);
        } else if (nilaiPelanggan < 100) {
            idPelanggan = "P"+String.valueOf(nilaiPelanggan);
        }
    }
    
    public void setHitungPelanggan(){
        try {
            driver();
            String sql = "select count(id_pelanggan) from pelanggan";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                nilaiPelanggan = Integer.parseInt(rs.getString(1)) + 1;
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Hitung Pelanggan"+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void driver() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
        stmt = conn.createStatement();
    }
}
