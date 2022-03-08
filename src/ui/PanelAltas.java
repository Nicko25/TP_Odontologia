package ui;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import negocio.Odontologo;
import negocio.Turno;
import negocio.Usuario;
import servicio.OdontologoService;
import servicio.UsuarioService;

public class PanelAltas extends JPanel{
    
    private PanelManager panelManager;
    private JPanel panelNorte,panelCentro,botonera,panelCheckBox;
    private JLabel lblTitulo,lblUser,lblPw,lblAdmin;
    private JTextField txtUser,txtPw,txtId;
    private JLabel lblNombre,lblApellido,lblMatricula,lblSemana;
    private JTextField txtNombre,txtApellido,txtMatricula;
    private JButton btnCancelar,btnAceptar;
    private JCheckBox[] checkBox;
    private JCheckBox boxAdmin;
    
    public PanelAltas(PanelManager panelManager){
        this.panelManager = panelManager;
    }
    
    
    public void llenarFormularioUsuario(Usuario usuario){
        txtId.setText(Long.toString(usuario.getId()));
        txtUser.setText(usuario.getUsuario());
        txtPw.setText(usuario.getContraseña());
        txtNombre.setText(usuario.getNombre());
        txtApellido.setText(usuario.getApellido());
        boxAdmin.setSelected(usuario.isAdmin());
        
    }
    
    public void llenarFormularioOdontologo(Odontologo odontologo){
        txtId.setText(Long.toString(odontologo.getId()));
        txtNombre.setText(odontologo.getNombre());
        txtApellido.setText(odontologo.getApellido());
        txtMatricula.setText(Integer.toString(odontologo.getMatricula()));
        for (int i = 0; i < checkBox.length; i++) {
            checkBox[i].setSelected(odontologo.getSemana()[i]);
        }
    }
    
    
    public void armarPanelAltaUsuario(){
        
       this.removeAll(); 
        
       this.setLayout(new BorderLayout());
       
       
       //TITULO (NORTE)
       
       panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
       panelNorte.setBackground(Color.LIGHT_GRAY);
       lblTitulo = new JLabel("Ingrese un nuevo usuario");
       panelNorte.add(lblTitulo);
       this.add(panelNorte,NORTH);
       
       
        
       // PANEL CENTRAL
       
       panelCentro = new JPanel(new GridLayout(6,2));
       
       lblUser = new JLabel("Usuario:");
       txtUser = new JTextField(3);
       lblPw = new JLabel("Contraseña:");
       txtPw = new JTextField(3);
       lblNombre = new JLabel("Nombre:");
       txtNombre = new JTextField(5);
       lblApellido = new JLabel("Apellido:");
       txtApellido = new JTextField(5);
       lblAdmin = new JLabel("Es Admin?:");
       boxAdmin = new JCheckBox();
       
       txtId = new JTextField(3);
       
       panelCentro.add(lblUser);
       panelCentro.add(txtUser); 
       panelCentro.add(lblPw); 
       panelCentro.add(txtPw);
       panelCentro.add(lblNombre);
       panelCentro.add(txtNombre);
       panelCentro.add(lblApellido);
       panelCentro.add(txtApellido);
       panelCentro.add(lblAdmin); 
       panelCentro.add(boxAdmin); 
       
       this.add(panelCentro,CENTER);
       
       
       // PANEL SUR
       
       botonera = new JPanel();
       
       btnCancelar = new JButton("Cancelar");
       btnAceptar = new JButton("Aceptar");
       
       botonera.add(btnCancelar);
       botonera.add(btnAceptar);
       this.add(botonera,SOUTH);
       
       this.validate(); //refrezcar ventana
       this.repaint(); //refrezcar ventana
        
       this.setVisible(true);
       
       
       btnCancelar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               panelManager.mostrarPanelAdmin();
           }
       });
       
       btnAceptar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Usuario usuario = new Usuario();
               if(!txtId.getText().equals(""))
                    usuario.setId(Long.parseLong(txtId.getText()));
               usuario.setUsuario(txtUser.getText());
               usuario.setContraseña(txtPw.getText());
               usuario.setNombre(txtNombre.getText());
               usuario.setApellido(txtApellido.getText());
               usuario.setAdmin(boxAdmin.isSelected());

               UsuarioService usuarioService = new UsuarioService();
               usuarioService.guardar(usuario);
               
               //borrar textFields
                
                txtUser.setText("");
                txtPw.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                
                panelManager.mostrarPanelAdmin();
               
           }
       });
       
       
        
    }
    //PANEL ODONTOLOGOS
    
    public void armarPanelAltaOdontologo(){
        
       this.removeAll(); 
        
       this.setLayout(new BorderLayout());
       
       
       //TITULO (NORTE)
       
       panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
       panelNorte.setBackground(Color.LIGHT_GRAY);
       lblTitulo = new JLabel("Ingrese un nuevo odontologo");
       panelNorte.add(lblTitulo);
       this.add(panelNorte,NORTH);
       
       
        
       // PANEL CENTRAL
       
       panelCentro = new JPanel(new GridLayout(4,2));
       
       lblNombre = new JLabel("Nombre:");
       txtNombre = new JTextField(3);
       lblApellido = new JLabel("Apellido:");
       txtApellido = new JTextField(3);
       lblMatricula = new JLabel("Matricula:");
       txtMatricula = new JTextField(5);
       lblSemana = new JLabel("Dias Laborales:");
       
       txtId = new JTextField(3);
       
       panelCentro.add(lblNombre);
       panelCentro.add(txtNombre); 
       panelCentro.add(lblApellido); 
       panelCentro.add(txtApellido); 
       panelCentro.add(lblMatricula); 
       panelCentro.add(txtMatricula);
       panelCentro.add(lblSemana);
       
       panelCheckBox = new JPanel(new GridLayout());
       checkBox = new JCheckBox[7];
       String[] dias = new String[]{"Lu","Ma","Mi","Ju","Vi","Sa","Do"};
        for (int i = 0; i < checkBox.length; i++) {
            checkBox[i] = new JCheckBox(dias[i]);
            panelCheckBox.add(checkBox[i]);
        }
       panelCentro.add(panelCheckBox);
       
       this.add(panelCentro,CENTER);
       
       
       // PANEL SUR
       
       botonera = new JPanel();
       
       btnCancelar = new JButton("Cancelar");
       btnAceptar = new JButton("Aceptar");
       
       botonera.add(btnCancelar);
       botonera.add(btnAceptar);
       this.add(botonera,SOUTH);
       
       
       this.validate(); //refrezcar ventana
       this.repaint(); //refrezcar ventana
       
       
       this.setVisible(true);
       
       
       btnCancelar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               panelManager.mostrarPanelAdmin();
           }
       });
       
       btnAceptar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Odontologo odontologo = new Odontologo();
               if(!txtId.getText().equals(""))
                    odontologo.setId(Long.parseLong(txtId.getText())); //no se que hace
               odontologo.setNombre(txtNombre.getText());
               odontologo.setApellido(txtApellido.getText());
               odontologo.setMatricula(Integer.parseInt(txtMatricula.getText()));
               odontologo.setSemana(recuperarSemana());

               OdontologoService odontologoService = new OdontologoService();
               odontologoService.guardar(odontologo);
               
               //borrar textFields
                
                txtNombre.setText("");
                txtApellido.setText("");
                txtMatricula.setText("");
                
                panelManager.mostrarPanelAdmin();
           }
       });
        
    }
    
    private boolean[] recuperarSemana(){
        boolean[] semana = new boolean[7];
        
        for (int i = 0; i < semana.length; i++) {
            semana[i] = checkBox[i].isSelected();
        }
        return semana;
    }
}
