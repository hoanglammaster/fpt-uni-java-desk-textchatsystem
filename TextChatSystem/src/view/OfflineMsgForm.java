/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClientServer;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import model.User;

/**
 *
 * @author hoang
 */
public class OfflineMsgForm extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form OfflineMsgForm
     */
    private ClientServer clientServer;
    private User userConnected;
    private ArrayList<User> listRegistedUser;
    
    public OfflineMsgForm() {
        initComponents();
    }
    public OfflineMsgForm(ClientServer clientServer, User userConnected, ArrayList<User> listRegistedUser) {
        initComponents();
        this.clientServer = clientServer;
        this.userConnected = userConnected;
        this.listRegistedUser = listRegistedUser;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Thread t = new Thread(this);
        t.start();
    }

    
    @Override
    public void run() {
        setNameForLabel();
        while(true){
            displayListUserHasOfflineMsg();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Thead can not sleep! "+e);
            }
        }
    }
    private void setNameForLabel(){
        lbUserConnectedName.setText("Login as : "+userConnected.getFullName());
    }
    private void displayListUserHasOfflineMsg(){
        ArrayList<String> listUserHasOfflineMsg = clientServer.getListHasOfflineMsg();
        DefaultListModel listModel = new DefaultListModel();
        jlistOfflineMsg.setModel(listModel);
        for(String msg : listUserHasOfflineMsg){
            String userName = msg.substring(0, msg.indexOf("("));
            userName = userName.trim();
            for(User user : listRegistedUser){
                if(userName.equals(user.getUserName())){
                    listModel.addElement(user.getFullName()+msg.substring(msg.indexOf("(")));
                }
            }
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

        lbUserConnectedName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlistOfflineMsg = new javax.swing.JList<>();
        btRead = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbUserConnectedName.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lbUserConnectedName.setText("jLabel1");

        jScrollPane1.setViewportView(jlistOfflineMsg);

        btRead.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        btRead.setForeground(new java.awt.Color(51, 51, 255));
        btRead.setText("Read");
        btRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReadActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 255));
        jButton2.setText("Close");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lbUserConnectedName))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(93, 93, 93)
                            .addComponent(btRead)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbUserConnectedName)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRead)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReadActionPerformed
        // TODO add your handling code here:
        int selectedIndex = jlistOfflineMsg.getSelectedIndex();
        if(selectedIndex==-1){
            return;
        }else{
            String receiverFullName = jlistOfflineMsg.getSelectedValue();
            receiverFullName = receiverFullName.substring(0, receiverFullName.indexOf("("));
            receiverFullName = receiverFullName.trim();
            User receiver =null;
            for(User user : listRegistedUser){
                if(user.getFullName().equals(receiverFullName)){
                    receiver = user;
                }
            }
//            System.out.println("Receiver Name " +receiverName);
            ChatForm chatForm = new ChatForm(clientServer, userConnected,receiver);
            chatForm.setVisible(true);
        }
    }//GEN-LAST:event_btReadActionPerformed

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
            java.util.logging.Logger.getLogger(OfflineMsgForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OfflineMsgForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OfflineMsgForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OfflineMsgForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OfflineMsgForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRead;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> jlistOfflineMsg;
    private javax.swing.JLabel lbUserConnectedName;
    // End of variables declaration//GEN-END:variables

}
