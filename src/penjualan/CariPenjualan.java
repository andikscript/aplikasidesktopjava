/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import menu.*;
import konfigurasi.*;
/**
 *
 * @author smaliska notebook
 */
public class CariPenjualan extends javax.swing.JFrame {
    public DefaultTableModel model = new DefaultTableModel ();
    String jumlah;
    String total;
    String cariData;
    int totalLaba;
    String file;
    String direktori;
   
    /**
     * Creates new form CariBarang
     */
    
     // variable database
    Connection conn;
    Statement stmt;
    ResultSet rs;
    
    public CariPenjualan() {
        initComponents();
        run();
    }
    
    public void run(){
        kosongkan();
        getJumlahPenjualan();
        getTotalPenjualan();
        getLaba();
        setTable();
        getData();
        jumlahPenjualan.setText(jumlah);
        totalPenjualan.setText("Rp. "+total);
        laba.setText("Rp. "+String.valueOf(totalLaba));
    }
   
    public void searchData(){
        int i = 1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "select motif, ukuran, nama_pelanggan, total, pelanggan.tanggal from penjualan INNER JOIN motif USING (id_motif) INNER JOIN ukuran USING (id_ukuran) INNER JOIN pelanggan USING (id_pelanggan) where motif.motif REGEXP '^"+cariData+"*'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object[] obj = new Object[6];
                obj[0] = i;
                obj[1] = rs.getString("motif");
                obj[2] = rs.getString("ukuran");
                obj[3] = rs.getString("nama_pelanggan");
                obj[4] = rs.getString("total");
                obj[5] = rs.getString("tanggal");
                model.addRow(obj);
                i++;
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void getData(){
        int i = 1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "select motif, ukuran, nama_pelanggan, total, penjualan.tanggal from penjualan INNER JOIN motif USING (id_motif) INNER JOIN ukuran USING (id_ukuran) INNER JOIN pelanggan USING (id_pelanggan) WHERE MONTH(penjualan.tanggal) = MONTH(NOW()) AND YEAR(penjualan.tanggal) = YEAR(NOW()) ORDER BY penjualan.tanggal ASC";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object[] obj = new Object[6];
                obj[0] = i;
                obj[1] = rs.getString("motif");
                obj[2] = rs.getString("ukuran");
                obj[3] = rs.getString("nama_pelanggan");
                obj[4] = rs.getString("total");
                obj[5] = rs.getString("tanggal");
                model.addRow(obj);
                i++;
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void refreshData(){
        int i = 1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "select motif, ukuran, nama_pelanggan, total, penjualan.tanggal from penjualan INNER JOIN motif USING (id_motif) INNER JOIN ukuran USING (id_ukuran) INNER JOIN pelanggan USING (id_pelanggan)";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object[] obj = new Object[6];
                obj[0] = i;
                obj[1] = rs.getString("motif");
                obj[2] = rs.getString("ukuran");
                obj[3] = rs.getString("nama_pelanggan");
                obj[4] = rs.getString("total");
                obj[5] = rs.getString("tanggal");
                model.addRow(obj);
                i++;
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void setTable(){
        tabel.setModel(model);
        model.addColumn("No.");
        model.addColumn("Motif");
        model.addColumn("Ukuran");
        model.addColumn("Pelanggan");
        model.addColumn("Total");
        model.addColumn("Tanggal");
        tabel.setRowHeight(35);
        tabel.setGridColor(Color.black);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabel.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabel.getColumnModel().getColumn(4).setPreferredWidth(160);
        tabel.getColumnModel().getColumn(5).setPreferredWidth(200);
    }
    
    public void getLaba(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "select sum(jumlah) from detail_penjualan";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    totalLaba = Integer.parseInt(rs.getString(1)) * 5000;
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void getTotalPenjualan(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "select sum(total) from penjualan";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    total = rs.getString(1);
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void getJumlahPenjualan(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sprei","root","");
            stmt = conn.createStatement();
            String sql = "select count(id_penjualan) from penjualan";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    jumlah = rs.getString(1);
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void toExcel(){
        try{
            direktori = JOptionPane.showInputDialog(null, "Masukkan Direktori Penyimpanan File :");
            file = JOptionPane.showInputDialog(null, "Masukkan Nama File :");
            FileWriter excel = new FileWriter(direktori+":\\\\"+file+".xls");

            for(int i = 0; i < model.getColumnCount(); i++){
                excel.write(model.getColumnName(i) + "\t");
            }

            excel.write("\n");

            for(int i=0; i< model.getRowCount(); i++) {
                for(int j=0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i,j).toString()+"\t");
                }
                excel.write("\n");
            }
             JOptionPane.showMessageDialog(null, "Berhasil.., Silahkan cek di Direktori "+direktori+":\\"+file);
            excel.close();

        }catch(IOException e){ System.out.println(e); }
    }

    
    public void kosongkan(){
        int baris = model.getRowCount();
        for (int a = 0; a < baris; a++) {
            model.removeRow(0);
        }
    } 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jumlahPenjualan = new javax.swing.JTextField();
        totalPenjualan = new javax.swing.JTextField();
        cari = new javax.swing.JTextField();
        search = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        laba = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        refresh = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        cetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Jumlah Penjualan");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Total Penjualan");

        jumlahPenjualan.setEditable(false);
        jumlahPenjualan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        totalPenjualan.setEditable(false);
        totalPenjualan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        cari.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-search-64.png"))); // NOI18N
        search.setText("CARI");
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Motif :");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Total Laba");

        laba.setEditable(false);
        laba.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(laba, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jumlahPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laba, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Informasi Transaksi Penjualan");

        tabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabel);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        refresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        cetak.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cetak.setText("Cetak");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refresh)
                    .addComponent(cancel)
                    .addComponent(cetak))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(287, 287, 287))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        kosongkan();
        refreshData();
    }//GEN-LAST:event_refreshActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        Penjualan p = new Penjualan();
        this.dispose();
        p.setVisible(true);
    }//GEN-LAST:event_cancelActionPerformed

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        cariData = cari.getText();
        kosongkan();
        searchData();
    }//GEN-LAST:event_searchMouseClicked

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        toExcel();
    }//GEN-LAST:event_cetakActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CariPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CariPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CariPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CariPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CariPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JTextField cari;
    private javax.swing.JButton cetak;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlahPenjualan;
    private javax.swing.JTextField laba;
    private javax.swing.JButton refresh;
    private javax.swing.JLabel search;
    public javax.swing.JTable tabel;
    private javax.swing.JTextField totalPenjualan;
    // End of variables declaration//GEN-END:variables
}
