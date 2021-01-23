
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sefaozturk
 */
public class WatchForm extends javax.swing.JFrame implements Runnable {

    DefaultTableModel model;
    Thread tt;

    static int milliseconds = 0;
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;
    long startTime;
    long endTime;
    int oldseconds;

    String str = "", nstr = "", mstr = "", dstr = "";

    static boolean state = true;

    int hourr, secondd, minute;
    int hcnt = 0, mcnt = 0, scnt = 0, mscnt = 0;
    int cnt = 0, cnt2 = 0;
    int userid;
    int programid;
    Connection con = null;
    Statement sta = null;
    PreparedStatement psta = null;
    PreparedStatement psta2 = null;
    PreparedStatement psta3 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    /**
     * Creates new form WatchForm
     */
    public WatchForm(int aid,int pid) {
        userid = aid;
        programid = pid;
        
         String url= "jdbc:sqlite:data.sqlite";
        
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
            System.out.println("Veri tabanına bağlandı");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Driver Çalışmadı :/");
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection çalışamadı :/");
        }
        initComponents();

        Thread t = new Thread(this);
        tt = new Thread(this);
        t.start();
//        showTime();
        //Puan();

        this.setLocationRelativeTo(null);
        bolum();
        
        
            Border glob_label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        jPanel1.setBorder(glob_label_border);
        
        
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        close_label.setBorder(label_border);
        
    }

    private void bolum(){
        String sql="select * from program where id=? ";
        try {
            psta=con.prepareStatement(sql);
            psta.setInt(1,programid);
            rs2=psta.executeQuery();

            if(rs2.next()){
                for(int i=1;i<=rs2.getInt("bolum_sayisi");i++){
                    ComboItem asd = new ComboItem(i,  i+". Bölüm");
                    bolum_box.addItem(asd);
                }
                
                String sqlctrl="select * from kullaniciprogram where kullanici_id=? and program_id=? and bolum=1";
                try {
                    psta2=con.prepareStatement(sqlctrl);
                    psta2.setInt(1, userid);
                    psta2.setInt(2, programid);

                    rs= psta2.executeQuery();
                    if(rs.next()){
                        oldseconds = rs.getInt("sure");
                        if(oldseconds<60){
                            saniye.setText(String.valueOf(oldseconds));
                        }else if(oldseconds<3600){
                            saniye.setText(String.valueOf(oldseconds%60));
                            dakika.setText(String.valueOf(oldseconds/60));
                        }else {
                            saniye.setText(String.valueOf(oldseconds%60));
                            dakika.setText(String.valueOf((oldseconds%3600)/60));
                            saat.setText(String.valueOf(oldseconds/3600));
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(WatchForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                System.out.println("Program Bulunamadı");
            }
            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(WatchForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

//
//    private void showTime() {
//        DateFormat a = new SimpleDateFormat("HH:mm:ss");
//        Date d = new Date();
//        day1.setText(a.format(d));
//    }

    /*private void Puan() {
        ArrayList<Integer> puanla = new ArrayList();
        puanla.add(1);
        puanla.add(2);
        puanla.add(3);
        puanla.add(4);
        puanla.add(5);
        puanla.add(6);
        puanla.add(7);
        puanla.add(8);
        puanla.add(9);
        puanla.add(10);

        DefaultComboBoxModel aa = new DefaultComboBoxModel<>(puanla.toArray());
        puan_box.setModel(aa);
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        izle_field = new javax.swing.JTextField();
        izle_field1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        b_anasayfa = new javax.swing.JButton();
        puan_box = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bolum_box = new javax.swing.JComboBox<>();
        saat = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dakika = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        saniye = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        close_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        izle_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izle_fieldActionPerformed(evt);
            }
        });

        izle_field1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izle_field1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Film/Dizi Adı :");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Türü :");

        b_anasayfa.setBackground(new java.awt.Color(212, 67, 56));
        b_anasayfa.setForeground(new java.awt.Color(255, 255, 255));
        b_anasayfa.setText("Anasayfaya Dön");
        b_anasayfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_anasayfaActionPerformed(evt);
            }
        });

        puan_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        puan_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puan_boxActionPerformed(evt);
            }
        });

        jButton1.setText("Oynat");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Puan Ver");

        bolum_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bolum_boxActionPerformed(evt);
            }
        });

        saat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saat.setText("00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText(":");

        dakika.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dakika.setText("00");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText(":");

        saniye.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saniye.setText("00");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Bölüm:");

        close_label.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        close_label.setText(" X ");
        close_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                close_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                close_labelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(puan_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(175, 175, 175))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_anasayfa))
                        .addGap(172, 172, 172))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(izle_field)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bolum_box, 0, 298, Short.MAX_VALUE)
                                    .addComponent(izle_field1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(saat)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dakika)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(saniye))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(close_label))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(close_label)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(izle_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izle_field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bolum_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dakika, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saniye, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(puan_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(b_anasayfa)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void izle_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izle_fieldActionPerformed

    }//GEN-LAST:event_izle_fieldActionPerformed

    private void izle_field1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izle_field1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_izle_field1ActionPerformed

    private void b_anasayfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_anasayfaActionPerformed
        setVisible(false);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(WatchForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        FilmArchive geri = new FilmArchive(this, true, userid);
        geri.setLocationRelativeTo(null);
        geri.setVisible(true);

    }//GEN-LAST:event_b_anasayfaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(state==false){
            startTime = System.currentTimeMillis();
            state = true;
            jButton1.setText("Durdur");
            Thread t = new Thread() {
                public void run() {
                    for (;;) {
                        if (state == true) {
                            long tmpTime = System.currentTimeMillis();
                            long estimatedTime = tmpTime - startTime;
                            int seconds = (int)estimatedTime/1000;
                            seconds += oldseconds;
                            if(seconds<60){
                                saniye.setText(String.valueOf(seconds));
                            }else if(seconds<3600){
                                saniye.setText(String.valueOf(seconds%60));
                                dakika.setText(String.valueOf(seconds/60));
                            }else {
                                saniye.setText(String.valueOf(seconds%60));
                                dakika.setText(String.valueOf((seconds%3600)/60));
                                saat.setText(String.valueOf(seconds/3600));
                            }
                        } else {
                            break;
                        }
                    }
                }
            };
            t.start();
        }else{
            state = false;
            jButton1.setText("Oynat");
            endTime = System.currentTimeMillis();
            long estimatedTime = endTime - startTime; // Geçen süreyi milisaniye cinsinden elde ediyoruz
            int seconds = (int)estimatedTime/1000;

            DateFormat a = new SimpleDateFormat("dd.MM.yyyy");
            Date d = new Date();

            ComboItem bolum = (ComboItem) bolum_box.getSelectedItem();


            String sqlctrl="select * from kullaniciprogram where kullanici_id=? and program_id=? and bolum=?";


            try {
                psta2=con.prepareStatement(sqlctrl);
                psta2.setInt(1, userid);
                psta2.setInt(2, programid);
                psta2.setInt(3, bolum.getValue());

                rs= psta2.executeQuery();
                if(rs.next()){
                    String sql2="update kullaniciprogram set date=?,sure=sure+? where id=?";
                    psta3=con.prepareStatement(sql2);
                    psta3.setString(1, a.format(d));
                    psta3.setInt(2, seconds);
                    psta3.setInt(3, rs.getInt("id"));

                    psta3.execute();
                    psta3.close();
                }else{
                    String sql="Insert into kullaniciprogram (kullanici_id,program_id,date,sure,bolum) values(?,?,?,?,?)";
                    psta=con.prepareStatement(sql);
                    psta.setInt(1, userid);
                    psta.setInt(2, programid);
                    psta.setString(3, a.format(d));
                    psta.setInt(4, seconds);
                    psta.setInt(5, bolum.getValue());

                    psta.execute();
                    psta.close();
                }
                rs.close();
                oldseconds += seconds;
            } catch (SQLException ex) {
                Logger.getLogger(WatchForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
    
    }//GEN-LAST:event_jButton1MouseClicked

    private void bolum_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bolum_boxActionPerformed
        state = false;
        
        String sqlctrl="select * from kullaniciprogram where kullanici_id=? and program_id=? and bolum=?";

        ComboItem bolum = (ComboItem) bolum_box.getSelectedItem();
        
        try {
            psta2=con.prepareStatement(sqlctrl);
            psta2.setInt(1, userid);
            psta2.setInt(2, programid);
            psta2.setInt(3, bolum.getValue());
            
            rs= psta2.executeQuery();
            if(rs.next()){
                oldseconds = rs.getInt("sure");
                saniye.setText("a");
                if(oldseconds<60){
                    saniye.setText(String.valueOf(oldseconds));
                }else if(oldseconds<3600){
                    saniye.setText(String.valueOf(oldseconds%60));
                    dakika.setText(String.valueOf(oldseconds/60));
                }else {
                    saniye.setText(String.valueOf(oldseconds%60));
                    dakika.setText(String.valueOf((oldseconds%3600)/60));
                    saat.setText(String.valueOf(oldseconds/3600));
                }
            }else{
                oldseconds=0;
                saniye.setText("00");
                dakika.setText("00");
                saat.setText("00");
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(WatchForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bolum_boxActionPerformed

    private void puan_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puan_boxActionPerformed
        ComboItem bolum = (ComboItem) bolum_box.getSelectedItem();

        String sqlctrl="select * from kullaniciprogram where kullanici_id=? and program_id=? and bolum=?";

        DateFormat a = new SimpleDateFormat("dd.MM.yyyy");
        Date d = new Date();
        try {
            psta2=con.prepareStatement(sqlctrl);
            psta2.setInt(1, userid);
            psta2.setInt(2, programid);
            psta2.setInt(3, bolum.getValue());

            rs= psta2.executeQuery();
            if(rs.next()){
                String sql2="update kullaniciprogram set puan=?,date=? where id=?";
                psta3=con.prepareStatement(sql2);
                psta3.setInt(1, puan_box.getSelectedIndex()+1);
                psta3.setString(2, a.format(d));
                psta3.setInt(3, rs.getInt("id"));

                psta3.execute();
                psta3.close();
            }else{
                String sql="Insert into kullaniciprogram (kullanici_id,program_id,date,puan,bolum) values(?,?,?,?,?)";
                psta=con.prepareStatement(sql);
                psta.setInt(1, userid);
                psta.setInt(2, programid);
                psta.setString(3, a.format(d));
                psta.setInt(4, puan_box.getSelectedIndex()+1);
                psta.setInt(5, bolum.getValue());

                psta.execute();
                psta.close();
            }
            rs.close();
            
            JOptionPane.showMessageDialog(null, "Puanınız Kayıt Edildi.");
            
        } catch (SQLException ex) {
            Logger.getLogger(WatchForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_puan_boxActionPerformed

    private void close_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_labelMouseClicked
        System.exit(0);
    }//GEN-LAST:event_close_labelMouseClicked

    private void close_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_labelMouseEntered
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        close_label.setBorder(label_border);
        close_label.setForeground(Color.white);
    }//GEN-LAST:event_close_labelMouseEntered

    private void close_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_labelMouseExited
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        close_label.setBorder(label_border);
        close_label.setForeground(Color.black);
    }//GEN-LAST:event_close_labelMouseExited

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
            java.util.logging.Logger.getLogger(WatchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WatchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WatchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WatchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WatchForm(0,71).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_anasayfa;
    private javax.swing.JComboBox<ComboItem> bolum_box;
    private javax.swing.JLabel close_label;
    private javax.swing.JLabel dakika;
    public javax.swing.JTextField izle_field;
    public javax.swing.JTextField izle_field1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> puan_box;
    private javax.swing.JLabel saat;
    private javax.swing.JLabel saniye;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        /*while (true) {
            Calendar cal = Calendar.getInstance();
            hourr = cal.get(Calendar.HOUR_OF_DAY);
            minute = cal.get(Calendar.MINUTE);
            secondd = cal.get(Calendar.SECOND);

            DateFormat a = new SimpleDateFormat("HH:mm:ss");
            Date d = cal.getTime();
            String saat = a.format(d);

        }*/
    }
}
