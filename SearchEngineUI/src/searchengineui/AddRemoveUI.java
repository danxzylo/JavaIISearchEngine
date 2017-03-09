/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengineui;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class AddRemoveUI extends javax.swing.JFrame {

    DefaultListModel <String> fileList = new DefaultListModel<>();
    int numberOfFilesIndexed;
    /**
     * Creates new form AddRemoveUI
     */
    public AddRemoveUI() {
        initComponents();
        
        Path indexedFiles = Paths.get("indexedFiles.txt");
        try (InputStream in = Files.newInputStream(indexedFiles);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) 
        {
            String line;            
            while ((line = reader.readLine()) != null) {
                fileList.addElement(line);                              
            }
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, e);
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
        java.awt.GridBagConstraints gridBagConstraints;

        okButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add/Remove Files");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        okButton.setMnemonic('o');
        okButton.setText("OK");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OkButton(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(okButton, gridBagConstraints);

        jList1.setModel(fileList);
        jScrollPane2.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 350;
        gridBagConstraints.ipady = 156;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        addButton.setMnemonic('a');
        addButton.setText("Add File/Directory");
        addButton.setToolTipText("Add files or directories to the index");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 55, 0, 40);
        jPanel1.add(addButton, gridBagConstraints);

        jButton1.setText("Remove Selected");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RemoveItem(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 65);
        jPanel1.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        JFileChooser fileDialog = new JFileChooser();
        // Filter out non .txt files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileDialog.setFileFilter(filter);
        
        fileDialog.setMultiSelectionEnabled(true);
        // Set the Directory
        fileDialog.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileDialog.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileDialog.getSelectedFiles();
            
            // Add selected files if they are not already in fileList            
            int i = 0, n =0;      
            selectedFilesLoop:
            for(i = 0; i < selectedFiles.length; ++i) {
                for(n = 0; n < fileList.getSize(); ++n){
                    if(selectedFiles[i].toString().equals(fileList.elementAt(n))){
                        JOptionPane.showMessageDialog(null, "File \"" + 
                        selectedFiles[i].toString() + "\" is already indexed.");
                        continue selectedFilesLoop;
                    }
                }
                fileList.addElement(selectedFiles[i].getAbsolutePath());
            }                                  
            numberOfFilesIndexed = fileList.getSize();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void RemoveItem(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RemoveItem
        int selectedIndex = jList1.getSelectedIndex();
        if(selectedIndex != -1){
            fileList.remove(selectedIndex); 
            numberOfFilesIndexed = fileList.getSize();
        }                
    }//GEN-LAST:event_RemoveItem

    private void OkButton(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OkButton
        // TODO add your handling code here: 
        Path indexedFiles = Paths.get("indexedFiles.txt");
        try (OutputStream out = Files.newOutputStream(indexedFiles);) 
        {
            int i;
            String newLine = "\n";
            for(i = 0; i < fileList.getSize(); ++i)
            {
                out.write(fileList.getElementAt(i).getBytes());
                out.write(newLine.getBytes());
            }
            Index index = new Index();
            index.indexIt(fileList);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        numberOfFilesIndexed = fileList.getSize();
        this.dispose();       
    }//GEN-LAST:event_OkButton

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
            java.util.logging.Logger.getLogger(AddRemoveUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddRemoveUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddRemoveUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddRemoveUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AddRemoveUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
