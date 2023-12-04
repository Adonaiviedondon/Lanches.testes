/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Telas;

import ConexaoDB.ModuloConexao;
import Formatacao.FormatTft;
import java.awt.HeadlessException;

import java.sql.*;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author tyago
 */
public class TelaClientes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaClientes() {
        initComponents();
        txtNome.setDocument(new FormatTft(50, FormatTft.TipoEntrada.NOME));
        txtCidade.setDocument(new FormatTft(50, FormatTft.TipoEntrada.NOME));
        txtBairro.setDocument(new FormatTft(50, FormatTft.TipoEntrada.NOME));
        txtEndereço.setDocument(new FormatTft(50, FormatTft.TipoEntrada.NOME));
        txtEmail.setDocument(new FormatTft(50, FormatTft.TipoEntrada.EMAIL));
        conexao = ModuloConexao.conector();
    }

    private void listar() {
        DefaultListModel<String> list = new DefaultListModel<>();
        listNomes.setModel(list);
        String readLista = "select * from tbClientes where nomeCliente like '" + txtNome.getText() + "%'" + "order by nomeCliente";
        try {
            conexao = ModuloConexao.conector();
            pst = conexao.prepareStatement(readLista);
            rs = pst.executeQuery();
            while (rs.next()) {
                JNomes.setVisible(true);
                list.addElement(rs.getString(2));
                if (txtNome.getText().isEmpty()) {
                    JNomes.setVisible(false);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
        }
    }

    private void buscar() {
        int busca = listNomes.getSelectedIndex();
        if (busca >= 0) {
            String buscaNome = "select * from tbClientes where nomeCliente like '" + txtNome.getText() + "%'" + "order by nomeCliente";
            try {
                conexao = ModuloConexao.conector();
                pst = conexao.prepareStatement(buscaNome);
                rs = pst.executeQuery();
                while (rs.next()) {
                    JNomes.setVisible(false);
                    idCliente.setText(rs.getString(1));
                    txtNome.setText(rs.getString(2));
                    txtCidade.setText(rs.getString(3));
                    txtBairro.setText(rs.getString(4));
                    txtEndereço.setText(rs.getString(5));
                    txtNum.setText(rs.getString(6));
                    jFormattedCep.setText(rs.getString(7));
                    txtEmail.setText(rs.getString(8));
                    jFormattedFone.setText(rs.getString(9));
                    btAdicionar.setEnabled(false);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            JNomes.setVisible(false);
        }
    }

    private void adicionar() {
        String sql = "insert into tbClientes(nomeCliente,cidadeCliente,bairroCliente,endCliente,numCliente,cepCliente,emailCLiente,foneCliente) values(?,?,?,?,?,?,?,?)";
        try {
            conexao = ModuloConexao.conector();
            pst = conexao.prepareStatement(sql);
            //pst.setString(1,txtIdUsuario.getText());
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCidade.getText());
            pst.setString(3, txtBairro.getText());
            pst.setString(4, txtEndereço.getText());
            pst.setString(5, txtNum.getText());
            pst.setString(6, jFormattedCep.getText());
            pst.setString(7, txtEmail.getText());
            pst.setString(8, jFormattedFone.getText());
            if (txtNome.getText().isEmpty() || txtCidade.getText().isEmpty() || txtBairro.getText().isEmpty() || txtEndereço.getText().isEmpty() || txtNum.getText().isEmpty() || jFormattedCep.getText().isEmpty() || txtEmail.getText().isEmpty() || jFormattedFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe todos os campos");

            } else {

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                txtNome.setText(null);
                txtCidade.setText(null);
                txtBairro.setText(null);
                txtEndereço.setText(null);
                txtNum.setText(null);
                jFormattedCep.setText(null);
                txtEmail.setText(null);
                jFormattedFone.setText(null);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o usuário");
            System.out.println(ex);

        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }

    private void atualizar() {
        String sql = "update tbClientes set nomeCliente = ?, cidadeCliente = ?, bairroCliente = ?, endCliente = ?, cepCliente = ?, emailCLiente = ?,foneCliente = ? where idCliente = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCidade.getText());
            pst.setString(3, txtBairro.getText());
            pst.setString(4, txtEndereço.getText());
            pst.setString(5, txtNum.getText());
            pst.setString(6, jFormattedCep.getText());
            pst.setString(7, txtEmail.getText());
            pst.setString(8, jFormattedFone.getText());
            if (txtNome.getText().isEmpty() || txtCidade.getText().isEmpty() || txtBairro.getText().isEmpty() || txtEndereço.getText().isEmpty() || txtNum.getText().isEmpty() || jFormattedCep.getText().isEmpty() || txtEmail.getText().isEmpty() || jFormattedFone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe todos os campos");
            } else {
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
                txtNome.setText(null);
                txtCidade.setText(null);
                txtBairro.setText(null);
                txtEndereço.setText(null);
                txtNum.setText(null);
                jFormattedCep.setText(null);
                txtEmail.setText(null);
                jFormattedFone.setText(null);
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar os dados do usuário");
            System.out.println(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbClientes where idCliente=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, idCliente.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!");
                idCliente.setText(null);
                txtNome.setText(null);
                txtCidade.setText(null);
                txtBairro.setText(null);
                txtEndereço.setText(null);
                txtNum.setText(null);
                jFormattedCep.setText(null);
                txtEmail.setText(null);
                jFormattedFone.setText(null);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btAtualizar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btAdicionar = new javax.swing.JButton();
        txtBairro = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtEndereço = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        JNomes = new javax.swing.JScrollPane();
        listNomes = new javax.swing.JList<>();
        jFormattedCep = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedFone = new javax.swing.JFormattedTextField();
        idCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNum = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(640, 520));

        jLabel1.setText("Nome:");

        jLabel2.setText("Bairro:");

        jLabel3.setText("Cidade:");

        jLabel4.setText("Endereço:");

        jLabel5.setText("CEP:");

        jLabel6.setText("Email:");

        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/atualizar.png"))); // NOI18N
        btAtualizar.setPreferredSize(new java.awt.Dimension(60, 60));
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/deletar.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(60, 60));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/adcionar.png"))); // NOI18N
        btAdicionar.setPreferredSize(new java.awt.Dimension(60, 60));
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        txtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroActionPerformed(evt);
            }
        });

        txtNome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });

        JNomes.setBorder(null);

        listNomes.setBorder(null);
        listNomes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listNomesMouseClicked(evt);
            }
        });
        JNomes.setViewportView(listNomes);

        JNomes.setVisible(false);

        try {
            jFormattedCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setText("Fone:");

        try {
            jFormattedFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        idCliente.setVisible(false);
        idCliente.setEnabled(false);

        jLabel8.setText("Id:");

        jLabel9.setText("N°");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel8)
                        .addGap(7, 7, 7)
                        .addComponent(idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JNomes, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(jFormattedCep, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(txtEndereço, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(11, 11, 11)
                        .addComponent(jFormattedFone, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(JNomes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(jFormattedCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(txtEndereço, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jFormattedFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setBounds(0, 0, 640, 520);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        remover();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroActionPerformed

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        listar();
    }//GEN-LAST:event_txtNomeKeyReleased

    private void listNomesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listNomesMouseClicked
        buscar();
    }//GEN-LAST:event_listNomesMouseClicked

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        atualizar();
    }//GEN-LAST:event_btAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JNomes;
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btAtualizar;
    private javax.swing.JTextField idCliente;
    private javax.swing.JButton jButton2;
    private javax.swing.JFormattedTextField jFormattedCep;
    private javax.swing.JFormattedTextField jFormattedFone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> listNomes;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereço;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNum;
    // End of variables declaration//GEN-END:variables
}
