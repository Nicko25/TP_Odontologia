package ui;

import negocio.Usuario;
import servicio.UsuarioService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import static java.awt.BorderLayout.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import negocio.Odontologo;
import negocio.Turno;
import servicio.OdontologoService;
import servicio.TurnoService;

public class PanelAdmin extends JPanel {

    private JTable jtable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane scrollPane;
    private JPanel menu,botonera;
    private JButton btnUsuarios,btnOdontologos,btnTurnos,btnAlta,btnEliminar,btnModificar,btnLogOut,btnVolver;
    private PanelManager panelManager;
    private JLabel lblPregunta;

    public PanelAdmin(PanelManager panelManager)
    {
        this.panelManager = panelManager;
    }
    
    
    //MENU QUE PREGUNTA SI GESTIONAR ODONTOLOGOS O USUARIOS
    
    public void armarPanelAdmin(){
        
        this.removeAll();

        this.setLayout(new BorderLayout());
        
        menu = new JPanel(new GridLayout());
        
        lblPregunta = new JLabel("¿Que desea hacer?",SwingUtilities.CENTER);
        btnUsuarios = new JButton("Gestionar Usuarios");
        btnOdontologos = new JButton("Gestionar Odontologos");
        btnTurnos = new JButton ("Gestionar Turnos");
        btnLogOut = new JButton("Log Out");
        menu.add(btnUsuarios);
        menu.add(btnOdontologos);
        menu.add(btnTurnos);
        this.add(lblPregunta,NORTH);
        this.add(btnLogOut,SOUTH);
        this.add(menu,CENTER);
        this.validate(); //refrezcar ventana
        this.repaint(); //refrezcar ventana
        
        btnUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 armarPanelAdminUser();
            }
        });
        
        btnOdontologos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armarPanelAdminOdontologos();
            }
        });
        
        btnTurnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armarPanelAdminTurnos();
            }
        });
        
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 panelManager.mostrarPanelLogin();
            }
        });
    }
    
    //GESTIONAR USUARIOS

    public void armarPanelAdminUser(){
        
        this.removeAll();

        this.setLayout(new BorderLayout());
        botonera = new JPanel();
        btnAlta = new JButton("Crear nuevo");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");
        btnVolver = new JButton("Volver");
        btnLogOut = new JButton("Log Out");
        scrollPane = new JScrollPane();
        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);

        List<Usuario> listaUsuarios = ObtenerListaUsuarios();

        //Columnas
        contenidoTabla.addColumn("ID");
        contenidoTabla.addColumn("Usuario");
        contenidoTabla.addColumn("Contraseña");
        contenidoTabla.addColumn("Nombre");
        contenidoTabla.addColumn("Apellido");
        contenidoTabla.addColumn("Admin");

        //Filas
        for(Usuario o : listaUsuarios) {
            //Usuario p = (Usuario) o;
            Object[] row = {o.getId(), o.getUsuario(), o.getContraseña(), o.getNombre(), o.getApellido(), o.isAdmin()};
            contenidoTabla.addRow(row);
        }
        jtable.setAutoCreateColumnsFromModel(true);
        scrollPane.setViewportView(jtable);

        botonera.add(btnAlta);
        botonera.add(btnEliminar);
        botonera.add(btnModificar);
        botonera.add(btnVolver);
        botonera.add(btnLogOut);

        add(scrollPane, BorderLayout.CENTER);
        add(botonera,BorderLayout.SOUTH);
        
        this.validate(); //refrezcar ventana
        this.repaint(); //refrezcar ventana

        this.setVisible(true);

        btnAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 panelManager.mostrarPanelAltaUsuario();
            }
        });
        
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //eliminar por el id
                try{
                long id = (long)jtable.getValueAt(jtable.getSelectedRow(),0);
                UsuarioService usuaroService = new UsuarioService();
                usuaroService.eliminar(id);
                armarPanelAdminUser();
                }catch(Exception error){
                    JOptionPane.showMessageDialog(botonera, "No se selecciono ningun usuario",
                    "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                long id = (long)jtable.getValueAt(jtable.getSelectedRow(),0);
                UsuarioService usuarioService = new UsuarioService();
                Usuario usuario = usuarioService.buscarId(id);
                panelManager.mostrarPanelFormularioUsuario(usuario);
                }catch(Exception error){
                    JOptionPane.showMessageDialog(botonera, "No se selecciono ningun usuario",
                    "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdmin();
            }
        });
        
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 panelManager.mostrarPanelLogin();
            }
        });
    }
    
    //GESTIONAR ODONTOLOGOS
    
    public void armarPanelAdminOdontologos(){
        
        this.removeAll();

        this.setLayout(new BorderLayout());
        botonera = new JPanel();
        btnAlta = new JButton("Crear nuevo");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");
        btnVolver = new JButton("Volver");
        btnLogOut = new JButton("Log Out");
        scrollPane = new JScrollPane();
        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);

        List<Odontologo> listaOdontologos = ObtenerListaOdontologos();

        //Columnas
        contenidoTabla.addColumn("ID");
        contenidoTabla.addColumn("Nombre");
        contenidoTabla.addColumn("Usuario");
        contenidoTabla.addColumn("Matricula");

        //Filas
        for(Odontologo o : listaOdontologos) {
            //Usuario p = (Usuario) o;
            Object[] row = {o.getId(), o.getNombre(), o.getApellido(), o.getMatricula()};
            contenidoTabla.addRow(row);
        }
        jtable.setAutoCreateColumnsFromModel(true);
        scrollPane.setViewportView(jtable);


        botonera.add(btnAlta);
        botonera.add(btnEliminar);
        botonera.add(btnModificar);
        botonera.add(btnVolver);
        botonera.add(btnLogOut);

        add(scrollPane, BorderLayout.CENTER);
        add(botonera,BorderLayout.SOUTH);
        
        this.validate(); //refrezcar ventana
        this.repaint(); //refrezcar ventana

        this.setVisible(true);

        btnAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 panelManager.mostrarPanelOdontologo();
            }
        });
        
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //eliminar por el id
                try{ 
                long id = (long)jtable.getValueAt(jtable.getSelectedRow(),0);
                OdontologoService odontologoService = new OdontologoService();
                odontologoService.eliminar(id);
                armarPanelAdminOdontologos();
                }catch(Exception error){
                    JOptionPane.showMessageDialog(botonera, "No se selecciono ningun odontologo",
                    "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                long id = (long)jtable.getValueAt(jtable.getSelectedRow(),0);
                OdontologoService odontologoService = new OdontologoService();
                Odontologo odontologo = odontologoService.buscarId(id);
                panelManager.mostrarPanelFormularioOdontologo(odontologo);
                }catch(Exception error){
                    JOptionPane.showMessageDialog(botonera, "No se selecciono ningun odontologo",
                    "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdmin();
            }
        });
        
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 panelManager.mostrarPanelLogin();
            }
        });
        
    }
    
    public void armarPanelAdminTurnos(){
        
        this.removeAll();

        this.setLayout(new BorderLayout());
        botonera = new JPanel();
        btnAlta = new JButton("Crear nuevo");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");
        btnVolver = new JButton("Volver");
        btnLogOut = new JButton("Log Out");
        scrollPane = new JScrollPane();
        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);

        List<Turno> listaTurnos = ObtenerListaTurnos();

        //Columnas
        contenidoTabla.addColumn("ID dle Turno");
        contenidoTabla.addColumn("Usuario");
        contenidoTabla.addColumn("Nombre y apellido del Paciente");
        contenidoTabla.addColumn("Fecha y Hora");
        contenidoTabla.addColumn("Nombre y apellido del odontologo");
        

        //Filas
        for(Turno o : listaTurnos) {
            //Usuario p = (Usuario) o;
            Object[] row = {o.getId(), o.getUsuario(), o.getNombre() + " " + o.getApellido(), o.getDia() + "/" + o.getMes() + "a las: " + o.getHorario(), o.getOdontNombre() + " " + o.getOdontApellido()};
            contenidoTabla.addRow(row);
        }
        jtable.setAutoCreateColumnsFromModel(true);
        scrollPane.setViewportView(jtable);

        botonera.add(btnEliminar);
        botonera.add(btnVolver);
        botonera.add(btnLogOut);

        add(scrollPane, BorderLayout.CENTER);
        add(botonera,BorderLayout.SOUTH);
        
        this.validate(); //refrezcar ventana
        this.repaint(); //refrezcar ventana

        this.setVisible(true);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //eliminar por el id
                try{
                long id = (long)jtable.getValueAt(jtable.getSelectedRow(),0);
                TurnoService turnoService = new TurnoService();
                turnoService.eliminar(id);
                armarPanelAdminTurnos();
                }catch(Exception error){
                    JOptionPane.showMessageDialog(botonera, "No se selecciono ningun turno",
                    "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdmin();
            }
        });
        
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 panelManager.mostrarPanelLogin();
            }
        });
    }
    
    private List<Usuario> ObtenerListaUsuarios() {
        UsuarioService usuarioService = new UsuarioService();
        return usuarioService.listar();

    }

    private List<Odontologo> ObtenerListaOdontologos() {
        OdontologoService odontologoService = new OdontologoService();
        return odontologoService.listar();

    }
    
    private List<Turno> ObtenerListaTurnos() {
        TurnoService turnoService = new TurnoService();
        return turnoService.listar();

    }

}