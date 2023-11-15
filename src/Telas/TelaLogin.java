package Telas;

import java.sql.*;
import ConexaoDB.ModuloConexao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class TelaLogin extends JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void logar() {
        String sql = "select * from tbUsuarios where Login =? and Senha=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, textUsuario.getText());
            String senha = new String(passwordUsuario.getPassword());
            pst.setString(2, senha);
            rs = pst.executeQuery();
            if (rs.next()) {
                String perfil = rs.getString(6);
                if (perfil.equals("Admin")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.jMUsuario.setEnabled(true);
                    TelaPrincipal.jMenuCadastro.setEnabled(true);
                    TelaPrincipal.jLabelUsario.setText(rs.getString(2));
                    this.dispose();
                } else {                    
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.jLabelUsario.setText(rs.getString(2));
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválido");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel conectar ao banco");
            System.out.print(e);
        }
    }

    private static final long serialVersionUID = 1L;
    private JTextField textUsuario;
    private JPasswordField passwordUsuario;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaLogin frame = new TelaLogin();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaLogin() {
        conexao = ModuloConexao.conector();

        setTitle("DomDomLanches");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Button.light"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel Usuário = new JLabel("Usuário");
        Usuário.setBounds(51, 107, 59, 23);
        Usuário.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(Usuário);

        JLabel Senha = new JLabel("Senha");
        Senha.setBounds(51, 156, 46, 17);
        Senha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(Senha);

        textUsuario = new JTextField();
        textUsuario.setBounds(107, 110, 272, 20);
        contentPane.add(textUsuario);
        textUsuario.setColumns(10);

        passwordUsuario = new JPasswordField();
        passwordUsuario.setBounds(107, 156, 272, 20);
        contentPane.add(passwordUsuario);

        JButton BtLogin = new JButton("Login");
        BtLogin.setBounds(290, 203, 89, 23);
        BtLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logar();
            }
        });
        contentPane.add(BtLogin);

        JLabel lblStatus = new JLabel("");
        lblStatus.setIcon(new ImageIcon("D:\\ProjetoJava\\src\\Icones\\dbko.png"));
        lblStatus.setBounds(64, 184, 99, 66);
        contentPane.add(lblStatus);

        if (conexao != null) {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/dbok.png")));
        } else {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/dbko.png")));
        }

    }
}
