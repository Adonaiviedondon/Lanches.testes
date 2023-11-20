package Telas;

import java.sql.*;
import ConexaoDB.ModuloConexao;
import Formatacao.FormatMasck;
import Formatacao.FormatTft;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

/**
 *
 * @author tyago
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        txtNomeUsuario.setDocument(new FormatTft(50, FormatTft.TipoEntrada.NOME));
        conexao = ModuloConexao.conector();
    }

 

    private void listar() {
        DefaultListModel<String> list = new DefaultListModel<>();
        listNomes.setModel(list);
        String readLista = "select * from tbUsuarios where usuario like '" + txtNomeUsuario.getText() + "%'" + "order by usuario";
        try {
            conexao = ModuloConexao.conector();
            pst = conexao.prepareStatement(readLista);
            rs = pst.executeQuery();
            while (rs.next()) {
                jNomes.setVisible(true);
                list.addElement(rs.getString(2));
                if (txtNomeUsuario.getText().isEmpty()) {
                    jNomes.setVisible(false);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void buscar() {
        int busca = listNomes.getSelectedIndex();
        if (busca >= 0) {
            String buscaNome = "select * from tbUsuarios where usuario like '" + txtNomeUsuario.getText() + "%'" + "order by usuario limit " + busca + ",1";
            try {
                conexao = ModuloConexao.conector();
                pst = conexao.prepareStatement(buscaNome);
                rs = pst.executeQuery();
                while (rs.next()) {
                    jNomes.setVisible(false);
                    txtIdUsuario.setText(rs.getString(1));
                    txtNomeUsuario.setText(rs.getString(2));
                    txtFoneUsuario.setText(rs.getString(3));
                    txtLoginUsuario.setText(rs.getString(4));
                    txtSenhaUsuario.setText(rs.getString(5));
                    boxPerfilUsuario.setSelectedItem(rs.getString(6));
                }

            } catch (Exception e) {
            }
        } else {
            jNomes.setVisible(false);
        }
    }

    private void adicionar() {
        String sql = "insert into tbUsuarios(usuario,fone,login,senha,perfil) values(?,?,?,?,?)";
        try {
            conexao = ModuloConexao.conector();
            pst = conexao.prepareStatement(sql);
            //pst.setString(1,txtIdUsuario.getText());
            pst.setString(1, txtNomeUsuario.getText());
            pst.setString(2, txtFoneUsuario.getText());
            pst.setString(3, txtLoginUsuario.getText());
            pst.setString(4, txtSenhaUsuario.getText());
            pst.setString(5, boxPerfilUsuario.getSelectedItem().toString());
            if (txtNomeUsuario.getText().isEmpty() || txtFoneUsuario.getText().isEmpty() || txtLoginUsuario.getText().isEmpty() || txtSenhaUsuario.getText().isEmpty() || boxPerfilUsuario.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe todos os campos");

            } else {

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                txtIdUsuario.setText(null);
                txtNomeUsuario.setText(null);
                txtFoneUsuario.setText(null);
                txtLoginUsuario.setText(null);
                txtSenhaUsuario.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o usuário");

        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }

    private void atualizar() {
        String sql = "update tbUsuarios set usuario = ?, fone = ?, login = ?, senha = ?, perfil = ? where idUser = ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeUsuario.getText());
            pst.setString(2, txtFoneUsuario.getText());
            pst.setString(3, txtLoginUsuario.getText());
            pst.setString(4, txtSenhaUsuario.getText());
            pst.setString(5, boxPerfilUsuario.getSelectedItem().toString());
            pst.setString(6, txtIdUsuario.getText());
            if (txtNomeUsuario.getText().isEmpty() || txtFoneUsuario.getText().isEmpty() || txtLoginUsuario.getText().isEmpty() || txtSenhaUsuario.getText().isEmpty() || boxPerfilUsuario.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe todos os campos");
            } else {
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
                txtIdUsuario.setText(null);
                txtNomeUsuario.setText(null);
                txtFoneUsuario.setText(null);
                txtLoginUsuario.setText(null);
                txtSenhaUsuario.setText(null);
            }

        } catch (Exception e) {
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
    private void remover(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbUsuarios where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdUsuario.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!");
                txtIdUsuario.setText(null);
                txtNomeUsuario.setText(null);
                txtFoneUsuario.setText(null);
                txtLoginUsuario.setText(null);
                txtSenhaUsuario.setText(null);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
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

        jNomeUsuario = new javax.swing.JLabel();
        jFoneUsuaruo = new javax.swing.JLabel();
        jLoginUsuario = new javax.swing.JLabel();
        jSenhaUsuario = new javax.swing.JLabel();
        jPerfilUsuario = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        txtNomeUsuario = new javax.swing.JTextField();
        txtFoneUsuario = new javax.swing.JTextField();
        txtLoginUsuario = new javax.swing.JTextField();
        txtSenhaUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        boxPerfilUsuario = new javax.swing.JComboBox<>();
        btAdicionar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        jNomes = new javax.swing.JScrollPane();
        listNomes = new javax.swing.JList<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(640, 520));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jNomeUsuario.setText("Nome:");
        getContentPane().add(jNomeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 88, -1, -1));

        jFoneUsuaruo.setText("Fone:");
        getContentPane().add(jFoneUsuaruo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLoginUsuario.setText("Login:");
        getContentPane().add(jLoginUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, -1, -1));

        jSenhaUsuario.setText("Senha:");
        getContentPane().add(jSenhaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, 22));

        jPerfilUsuario.setText("Perfil:");
        getContentPane().add(jPerfilUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        txtIdUsuario.setVisible(false);
        getContentPane().add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 27, 130, -1));

        txtNomeUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNomeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeUsuarioActionPerformed(evt);
            }
        });
        txtNomeUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeUsuarioKeyReleased(evt);
            }
        });
        getContentPane().add(txtNomeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 460, -1));

        txtFoneUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFoneUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtFoneUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 180, -1));
        getContentPane().add(txtLoginUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 180, -1));

        txtSenhaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenhaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 180, -1));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 322, -1, -1));

        boxPerfilUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        boxPerfilUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxPerfilUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(boxPerfilUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/adcionar.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAdicionar.setPreferredSize(new java.awt.Dimension(70, 70));
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/atualizar.png"))); // NOI18N
        btAtualizar.setToolTipText("Atualizar");
        btAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAtualizar.setPreferredSize(new java.awt.Dimension(70, 70));
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/deletar.png"))); // NOI18N
        btDeletar.setToolTipText("Deletar");
        btDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDeletar.setPreferredSize(new java.awt.Dimension(70, 70));
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        getContentPane().add(btDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, -1, -1));

        jNomes.setBorder(null);
        jNomes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jNomes.setVisible(false);

        listNomes.setBorder(null);
        listNomes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listNomesMouseClicked(evt);
            }
        });
        jNomes.setViewportView(listNomes);

        getContentPane().add(jNomes, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 460, 100));

        setBounds(0, 0, 640, 520);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeUsuarioActionPerformed

    private void txtFoneUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFoneUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFoneUsuarioActionPerformed

    private void txtSenhaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaUsuarioActionPerformed

    private void boxPerfilUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxPerfilUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxPerfilUsuarioActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        remover();
    }//GEN-LAST:event_btDeletarActionPerformed

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionar();

    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        atualizar();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void txtNomeUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeUsuarioKeyReleased
        listar();
    }//GEN-LAST:event_txtNomeUsuarioKeyReleased

    private void listNomesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listNomesMouseClicked
        buscar();
    }//GEN-LAST:event_listNomesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxPerfilUsuario;
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btDeletar;
    private javax.swing.JLabel jFoneUsuaruo;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLoginUsuario;
    private javax.swing.JLabel jNomeUsuario;
    private javax.swing.JScrollPane jNomes;
    private javax.swing.JLabel jPerfilUsuario;
    private javax.swing.JLabel jSenhaUsuario;
    private javax.swing.JList<String> listNomes;
    private javax.swing.JTextField txtFoneUsuario;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtLoginUsuario;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JTextField txtSenhaUsuario;
    // End of variables declaration//GEN-END:variables
}
