package ui;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import negocio.Odontologo;
import negocio.Turno;
import negocio.Usuario;
import servicio.TurnoService;

public class PanelTurnos extends JPanel{
    
    
    private PanelManager panelManager;
    private JPanel panelNorte,panelSur,panelCentro,panelGrid;
    private JLabel lblFecha,lblOdontologo,lblHorario,lblNombre,lblApellido,lblFinal,lblFinal2,lblFinal3;
    
    private JTextField txtNombre,txtApellido,txtId;
    private String[] meses = new String[]{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    private String[] horarios = new String[]{"10:00hs a 10:40hs","11:00hs a 11:40hs","12:00hs a 12:40hs","14:00hs a 14:40hs","15:00hs a 15:40hs","16:00hs a 16:40hs"};
    private int cantHorarios = horarios.length;
    private JTable jtable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane scrollPane;
    
    private JButton btnVolver,btnCancelar,btnSiguiente;
    
    public PanelTurnos(PanelManager panelManager){
        this.panelManager = panelManager;
    }
    
    public void armarPanelTurnosPaso1(Usuario usuario,Odontologo odontologo,String dia,int mes,int año){
        
        this.removeAll();
        this.setLayout(new BorderLayout());
        
        
        //PANEL NORTE - LABELS CON INFO
        panelNorte = new JPanel(new GridLayout(0, 1));
        lblFecha = new JLabel("Turnos disponibles para el: " + dia + " de " + meses[mes-1]);
        lblFecha.setHorizontalAlignment(JLabel.CENTER);
        lblOdontologo = new JLabel("Odontologo: " + odontologo.getNombre() + " " + odontologo.getApellido());
        lblOdontologo.setHorizontalAlignment(JLabel.CENTER);
        
        panelNorte.add(lblFecha);
        panelNorte.add(lblOdontologo);
        this.add(panelNorte,NORTH);
        
        //PANEL CENTRO - TABLA DE HORARIOS
        scrollPane = new JScrollPane();
        contenidoTabla = new DefaultTableModel();
        jtable = new JTable(contenidoTabla);
        contenidoTabla.addColumn("Horarios");
        
        List<Turno> lista = ObtenerListaTurnos();
        

        for(String hora : horarios) {
            if(!buscarHorario(hora,dia,mes,año)){
                Object[] row = {hora};
                contenidoTabla.addRow(row);
            }
        }
        
        jtable.setAutoCreateColumnsFromModel(true);
        scrollPane.setViewportView(jtable);
        
        add(scrollPane, BorderLayout.CENTER);
        
        //PANEL SUR - BOTONERA
        panelSur = new JPanel();
        
        btnVolver = new JButton("Volver");
        btnSiguiente = new JButton("Siguiente");
        
        
        panelSur.add(btnVolver);
        panelSur.add(btnSiguiente);
        
        this.add(panelSur,SOUTH);
        
        this.validate(); //refrezcar ventana
        this.repaint(); //refrezcar ventana

        this.setVisible(true);
        
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCalendarioVolver(usuario, odontologo);
            }
        });
        
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String horario = (String) contenidoTabla.getValueAt(jtable.getSelectedRow(), 0);
                panelManager.mostrarPanelTurnos2(usuario,odontologo,dia,mes,año,horario);
                }catch(Exception error){
                    JOptionPane.showMessageDialog(panelSur, "No se selecciono ningun horario",
                    "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
    
    public void armarPanelTurnosPaso2 (Usuario usuario,Odontologo odontologo,String dia,int mes,int año,String horario){
        
        //PANEL NORTE - LABELS CON INFO
        
        this.removeAll();
        this.setLayout(new BorderLayout());
        
        panelNorte = new JPanel(new GridLayout(0, 1));
        lblFecha = new JLabel("Turnos disponibles para el: " + dia + " de " + meses[mes-1]);
        lblFecha.setHorizontalAlignment(JLabel.CENTER);
        lblOdontologo = new JLabel("Odontologo: " + odontologo.getNombre() + " " + odontologo.getApellido());
        lblOdontologo.setHorizontalAlignment(JLabel.CENTER);
        lblHorario = new JLabel("Horario: " + horario);
        lblHorario.setHorizontalAlignment(JLabel.CENTER);
        
        
        panelNorte.add(lblFecha);
        panelNorte.add(lblOdontologo);
        panelNorte.add(lblHorario);
        this.add(panelNorte,NORTH);
        
        
        //PANEL CENTRO - Persona que necesita el turno
        
        
        panelCentro = new JPanel(new BorderLayout());
        panelGrid = new JPanel(new GridLayout(2,2));
        
        lblNombre = new JLabel("Nombre:");
        lblApellido = new JLabel("Apellido:");
        JLabel ingrese = new JLabel("Por favor ingrese el nombre de la persona que se atendera");
        txtNombre = new JTextField(usuario.getNombre());
        txtApellido = new JTextField(usuario.getApellido());
        
        panelCentro.add(ingrese,NORTH);
        panelGrid.add(lblNombre);
        panelGrid.add(txtNombre);
        panelGrid.add(lblApellido);
        panelGrid.add(txtApellido);
        
        
        panelCentro.add(panelGrid,CENTER);
        this.add(panelCentro,CENTER);
        
        //PANEL SUR - BOTONERA
        panelSur = new JPanel();
        
        btnVolver = new JButton("Volver");
        btnCancelar = new JButton("Cancelar");
        btnSiguiente = new JButton("Siguiente");
        
        
        panelSur.add(btnVolver);
        panelSur.add(btnCancelar);
        panelSur.add(btnSiguiente);
        
        this.add(panelSur,SOUTH);
        
        
        this.validate(); //refrezcar ventana
        this.repaint(); //refrezcar ventana

        this.setVisible(true);
        
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelTurnos(usuario, odontologo, dia, mes,año);
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelUsuarioVolver(usuario);
            }
        });
        
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String horario = (String) contenidoTabla.getValueAt(jtable.getSelectedRow(), 0);
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                panelManager.mostrarPanelTurnos3(usuario,odontologo,dia,mes,año,horario,nombre,apellido);
            }
        });
    }
    
    
    public void armarPanelTurnosPaso3 (Usuario usuario,Odontologo odontologo,String dia,int mes,int año,String horario,String nombre,String apellido){
        
        this.removeAll();
        this.setLayout(new BorderLayout());
        
        //PANEL NORTE - LABELS CON INFO
        
        this.removeAll();
        this.setLayout(new BorderLayout());
        
        panelNorte = new JPanel(new GridLayout(0, 1));
        lblFecha = new JLabel("Turnos disponibles para el: " + dia + " de " + meses[mes-1]);
        lblFecha.setHorizontalAlignment(JLabel.CENTER);
        panelNorte.add(lblFecha);

        this.add(panelNorte,NORTH);
        
        //PANEL CENTRO
        panelCentro = new JPanel(new GridLayout(3,0));
        
        lblFinal = new JLabel("Odontologo: " + odontologo.getApellido(),SwingConstants.CENTER);
        lblFinal2 = new JLabel("Horario: " + horario,SwingConstants.CENTER);
        lblFinal3 = new JLabel("Para: " + nombre + " " + apellido,SwingConstants.CENTER);
        
        txtId = new JTextField(3);
        
        panelCentro.add(lblFinal);
        panelCentro.add(lblFinal2);
        panelCentro.add(lblFinal3);
        
        this.add(panelCentro,CENTER);
        
        
        //PANEL SUR - BOTONERA
        panelSur = new JPanel();
        
        btnVolver = new JButton("Volver");
        btnCancelar = new JButton("Cancelar");
        btnSiguiente = new JButton("Siguiente");
        
        
        panelSur.add(btnVolver);
        panelSur.add(btnCancelar);
        panelSur.add(btnSiguiente);
        
        this.add(panelSur,SOUTH);
        
        this.validate(); //refrezcar ventana
        this.repaint(); //refrezcar ventana

        this.setVisible(true);
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelTurnos2(usuario, odontologo, dia, mes,año,horario);
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelUsuarioVolver(usuario);
            }
        });
        
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Turno turno = new Turno(usuario.getUsuario(),odontologo.getNombre(),odontologo.getApellido(),nombre,apellido,dia,mes,año,horario);
                      if(!txtId.getText().equals("")){
                        turno.setId(Long.parseLong(txtId.getText())); //no se que hace
                      }
                TurnoService turnoService = new TurnoService();
                turnoService.guardar(turno);
                panelManager.mostrarPanelUsuarioVolver(usuario);
            }
        });
    
    }
    
    private List<Turno> ObtenerListaTurnos() {
        TurnoService turnoService = new TurnoService();
        return turnoService.listar();

    }
    private boolean buscarHorario(String hora,String dia,int mes,int año){
        TurnoService turnoService = new TurnoService();
        return turnoService.buscarHorario(hora,dia,mes,año);
    }

}
