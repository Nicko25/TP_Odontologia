package ui;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import negocio.Usuario;
import servicio.UsuarioService;

public class PanelLogin extends JPanel {
    
    private PanelManager panelManager;
    private JPanel panelNorte,panelCentro,panelSur;
    private JLabel lblUser, lblPw,lblTitulo;
    private JTextField txtUser;
    private JPasswordField txtPw;
    private JButton btnLogin;
    
    public PanelLogin(PanelManager panelManager)
    {
        this.panelManager = panelManager;
    }

    public void armarPanelLogin(){
        
        this.removeAll();
        
        this.setLayout(new BorderLayout());
        
        
        //PANEL NORTE
        
       panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
       panelNorte.setBackground(Color.LIGHT_GRAY);
       lblTitulo = new JLabel("Ingrese un nuevo usuario");
       panelNorte.add(lblTitulo);
       this.add(panelNorte,NORTH);
        
        
        //PANEL1 CENTER
        
        panelCentro = new JPanel(new GridLayout(2,2));
        lblUser = new JLabel("Usuario: ");
        txtUser = new JTextField();
        lblPw = new JLabel("Password: ");
        txtPw = new JPasswordField();
        
        panelCentro.add(lblUser);
        panelCentro.add(txtUser);
        panelCentro.add(lblPw);
        panelCentro.add(txtPw);
        
        this.add(panelCentro,CENTER);
        
        //PANEL2 SOUTH (botonera)
        panelSur = new JPanel();
        btnLogin = new JButton("Logear");
        
        panelSur.add(btnLogin);

        
        this.add(panelSur,SOUTH);

        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               iniciarLogeo();
            }
        });
    }
    
    private void iniciarLogeo() {

        UsuarioService usuarioService = new UsuarioService();

        if (usuarioService.buscarUser(txtUser.getText(), String.valueOf(txtPw.getPassword())) == 1) {
            panelManager.mostrarPanelAdmin();
        }
        if (usuarioService.buscarUser(txtUser.getText(), String.valueOf(txtPw.getPassword())) == 2) {
            Usuario usuario = usuarioService.recuperarUser(txtUser.getText(), String.valueOf(txtPw.getPassword()));
            panelManager.mostrarPanelUsuario(usuario);
        }
        if (usuarioService.buscarUser(txtUser.getText(), String.valueOf(txtPw.getPassword())) == 0) {
            JOptionPane.showMessageDialog(panelSur, "El usuario o la contraseña son incorrectos",
                    "Error al ingresar", JOptionPane.ERROR_MESSAGE);
        }

    }
    
}
    
    
    
    
    
    
    
    

