/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import conexão.ConectaBD;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Veiculo;

/**
 *
 * @author Gabs
 */
public class ListaVeiculos extends javax.swing.JFrame {

    /**
     * Creates new form ListaVeiculos
     */
    public ListaVeiculos() {
        initComponents();
        setIcon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rtTitulo = new javax.swing.JLabel();
        cxPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btMostrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTabelaVeiculos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Veículos");

        rtTitulo.setText("Digite o Modelo:");

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        btMostrar.setText("Mostrar Todos");
        btMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMostrarActionPerformed(evt);
            }
        });

        tbTabelaVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Marca", "Modelo", "Ano"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbTabelaVeiculos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btMostrar)
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rtTitulo)
                        .addGap(18, 18, 18)
                        .addComponent(cxPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rtTitulo)
                    .addComponent(cxPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btPesquisar)
                    .addComponent(btMostrar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMostrarActionPerformed
        List<Veiculo> lvei = new ArrayList<Veiculo>();
        DefaultTableModel modelo = (DefaultTableModel) tbTabelaVeiculos.getModel();
        modelo.setRowCount(0);
        
        ConectaBD banco = new ConectaBD();
        boolean con = banco.getConnection();
        if(con == true){
            try {
                PreparedStatement pst = banco.connection.prepareStatement("SELECT * FROM veiculo ORDER BY codigo");
                ResultSet rst = pst.executeQuery();
                while(rst.next()){
                    Veiculo vei = new Veiculo();
                    
                    vei.setCodigo(rst.getInt("codigo"));
                    vei.setMarca(rst.getString("marca"));
                    vei.setModelo(rst.getString("modelo"));
                    vei.setAno(rst.getInt("ano"));
                  
                    lvei.add(vei);
                }
                for(int i = 0; i<=lvei.size(); i++){
                    modelo.insertRow(i, new Object[]{lvei.get(i).getCodigo(), lvei.get(i).getMarca(), lvei.get(i).getModelo(), lvei.get(i).getAno()});
                }
                pst.close();
                rst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro de Consulta\n"+ex.toString());
            }
            banco.close();
        }
        else{
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados");
        }
    }//GEN-LAST:event_btMostrarActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        List<Veiculo> lvei = new ArrayList<Veiculo>();
        DefaultTableModel modelo = (DefaultTableModel) tbTabelaVeiculos.getModel();
        modelo.setRowCount(0);
        
        ConectaBD banco = new ConectaBD();
        boolean con = banco.getConnection();
        if(con == true){
            try {
                PreparedStatement pst = banco.connection.prepareStatement("SELECT * FROM veiculo WHERE modelo LIKE '%"+cxPesquisa.getText()+"%' ORDER BY modelo");
                ResultSet rst = pst.executeQuery();
                while(rst.next()){
                    Veiculo vei = new Veiculo();
                    
                    vei.setCodigo(rst.getInt("codigo"));
                    vei.setMarca(rst.getString("marca"));
                    vei.setModelo(rst.getString("modelo"));
                    vei.setAno(rst.getInt("ano"));
                  
                    lvei.add(vei);
                }
                for(int i = 0; i<=lvei.size(); i++){
                    modelo.insertRow(i, new Object[]{lvei.get(i).getCodigo(), lvei.get(i).getMarca(), lvei.get(i).getModelo(), lvei.get(i).getAno()});
                }
                pst.close();
                rst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro de Consulta\n"+ex.toString());
            }
            banco.close();
        }
        else{
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados");
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(ListaVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaVeiculos().setVisible(true);
            }
        });
    }
    
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.jpeg")));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btMostrar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JTextField cxPesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel rtTitulo;
    private javax.swing.JTable tbTabelaVeiculos;
    // End of variables declaration//GEN-END:variables
}
