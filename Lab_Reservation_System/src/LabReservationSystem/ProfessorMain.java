/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabReservationSystem;

/**
 *
 * @author asdf0
 */
public class ProfessorMain extends javax.swing.JFrame {

    /**
     * Creates new form AssistantMain
     */
    public ProfessorMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        labCheckButt = new javax.swing.JButton();
        seminarReserButt = new javax.swing.JButton();
        timeTableCheckButt = new javax.swing.JButton();
        TitlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textField1 = new java.awt.TextField();
        label1 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1110, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        labCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        labCheckButt.setForeground(new java.awt.Color(255, 255, 255));
        labCheckButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/조교_실습실 조회 버튼.PNG"))); // NOI18N
        labCheckButt.setBorderPainted(false);
        labCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labCheckButtActionPerformed(evt);
            }
        });

        seminarReserButt.setBackground(new java.awt.Color(255, 255, 255));
        seminarReserButt.setForeground(new java.awt.Color(255, 255, 255));
        seminarReserButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/교수_특강 및 세미나 버튼.PNG"))); // NOI18N
        seminarReserButt.setBorderPainted(false);
        seminarReserButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seminarReserButtActionPerformed(evt);
            }
        });

        timeTableCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        timeTableCheckButt.setForeground(new java.awt.Color(255, 255, 255));
        timeTableCheckButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/교수_시간표 조회 버튼.PNG"))); // NOI18N
        timeTableCheckButt.setBorderPainted(false);
        timeTableCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeTableCheckButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(seminarReserButt, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(labCheckButt)
                .addGap(98, 98, 98)
                .addComponent(timeTableCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeTableCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seminarReserButt, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(194, 194, 194))
        );

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1110, 600));

        TitlePanel.setBackground(new java.awt.Color(204, 204, 204));
        TitlePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TitlePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TitlePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TitlePanelMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("굴림", 0, 30)); // NOI18N
        jLabel1.setText("컴퓨터소프트웨어공학과 실습실 예약 시스템");

        textField1.setEditable(false);
        textField1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textField1.setText("홍길동");

        label1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label1.setText("님");

        jLabel2.setText("(교수)");

        javax.swing.GroupLayout TitlePanelLayout = new javax.swing.GroupLayout(TitlePanel);
        TitlePanel.setLayout(TitlePanelLayout);
        TitlePanelLayout.setHorizontalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanelLayout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(137, 137, 137)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TitlePanelLayout.setVerticalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitlePanelLayout.createSequentialGroup()
                .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TitlePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(TitlePanelLayout.createSequentialGroup()
                        .addContainerGap(71, Short.MAX_VALUE)
                        .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TitlePanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(TitlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labCheckButtActionPerformed
        
    }//GEN-LAST:event_labCheckButtActionPerformed

    private void seminarReserButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seminarReserButtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seminarReserButtActionPerformed

    private void timeTableCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeTableCheckButtActionPerformed
        
    }//GEN-LAST:event_timeTableCheckButtActionPerformed

    private void TitlePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitlePanelMouseClicked
        
    }//GEN-LAST:event_TitlePanelMouseClicked

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
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfessorMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton labCheckButt;
    private java.awt.Label label1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton seminarReserButt;
    private java.awt.TextField textField1;
    private javax.swing.JButton timeTableCheckButt;
    // End of variables declaration//GEN-END:variables
}
