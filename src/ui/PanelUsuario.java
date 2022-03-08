package ui;

import negocio.Odontologo;
import servicio.OdontologoService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import negocio.Turno;
import negocio.Usuario;
import servicio.TurnoService;

public class PanelUsuario extends JPanel {

    private JTable jtable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane scrollPane;
    private JPanel botonera;
    private JButton btnAlta,btnEliminar,btnModificar,btnLogOut,btnVolver;
    private PanelManager panelManager;

    public PanelUsuario(PanelManager panelManager)
    {
        this.panelManager = panelManager;
    }

    public void armarPanelUsuario(Usuario usuario){
        
        this.removeAll();

        this.setLayout(new BorderLayout());
        botonera = new JPanel();
        btnAlta = new JButton("Nuevo Turno");
        btnModificar = new JButton("Consultar sus Turnos");
        btnLogOut = new JButton("Log Out");
        scrollPane = new JScrollPane();
        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);

        List<Odontologo> lista = ObtenerListaOdontologos();

        //Columnas
        contenidoTabla.addColumn("Nombre");
        contenidoTabla.addColumn("Apellido");

        //Filas
        for(Odontologo o: lista) {
            Object[] row = {o.getNombre(), o.getApellido()};
            contenidoTabla.addRow(row);
        }
        jtable.setAutoCreateColumnsFromModel(true);
        scrollPane.setViewportView(jtable);

        
        botonera.add(btnAlta);
        botonera.add(btnModificar);
        botonera.add(btnLogOut);
        

        add(scrollPane, BorderLayout.CENTER);
        add(botonera,BorderLayout.SOUTH);

        this.setVisible(true);

        btnAlta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
//                try{
                    String[] o = new String[]{(String)jtable.getValueAt(jtable.getSelectedRow(),0),(String)jtable.getValueAt(jtable.getSelectedRow(),1)};
                
                    OdontologoService odontologoService = new OdontologoService();
                    Odontologo odontologo = odontologoService.buscar(o);
                    panelManager.mostrarPanelCalendario(usuario,odontologo);
//                }catch(Exception error){
//                    JOptionPane.showMessageDialog(botonera, "No se selecciono ningun odontologo",
//                    "Error", JOptionPane.ERROR_MESSAGE);
//                }
            }
            
        });
        
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdministrarTurnos(usuario);
            }
        });

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 panelManager.mostrarPanelLogin();
            }
        });
    }
    
    public void armarPanelAdministrarTurnos(Usuario usuario){
        
        this.removeAll();
        
        scrollPane = new JScrollPane();
        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);
        
        List<Turno> lista = ObtenerListaTurnos();

        //Columnas
        contenidoTabla.addColumn("N° de Tramite");
        contenidoTabla.addColumn("Fecha y Hora");
        contenidoTabla.addColumn("Nombre");
        contenidoTabla.addColumn("Apellido");
        contenidoTabla.addColumn("Odontologo");

        //Filas
        for(Turno t: lista) {
            if(usuario.getUsuario().equals(t.getUsuario())){
                Object[] row = {t.getId(),t.getDia()+"/"+t.getMes()+" - " + t.getHorario(), t.getNombre(),t.getApellido(),t.getOdontNombre()+ " " + t.getOdontApellido()};
                contenidoTabla.addRow(row);
            }
        }
        jtable.setAutoCreateColumnsFromModel(true);
        scrollPane.setViewportView(jtable);
        
        btnVolver = new JButton("Volver");
        btnEliminar = new JButton("Eliminar");
        botonera = new JPanel();
        
        botonera.add(btnVolver);
        botonera.add(btnEliminar);
        botonera.add(btnLogOut);

        add(scrollPane, BorderLayout.CENTER);
        add(botonera,BorderLayout.SOUTH);
        
        this.validate(); //refrezcar ventana
        this.repaint(); //refrezcar ventana
        this.setVisible(true);
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                panelManager.mostrarPanelUsuario(usuario);
            }
        });
        
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long id = (long)jtable.getValueAt(jtable.getSelectedRow(),0);
                TurnoService turnoService = new TurnoService();
                turnoService.eliminar(id);
                panelManager.mostrarPanelAdministrarTurnos(usuario);
            }
        });
        
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelLogin();
            }
        });
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