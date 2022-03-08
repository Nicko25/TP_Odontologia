package ui;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import negocio.Odontologo;
import negocio.Usuario;

public class PanelCalendario extends JPanel{
    
    private PanelManager panelManager;
    private JPanel panelDias,panelIzq,panelDer,panelNorte,panelSur;
    private JButton btnIzq,btnDer,btnVolver;
    private JLabel lblMes,lblAño;
    private String mes = "11";
    private int veces = 0;
    public int guardarSemana;
    private int cont = 1;
    private String año = "2021";
    
    public PanelCalendario(PanelManager panelManager) {
        this.panelManager = panelManager;
        
    }
    
    public void reiniciarSemana(Usuario usuario, Odontologo odontologo){
        this.removeAll();  
        veces = guardarSemana;
        armarPanelCalendario(usuario,odontologo);
    }
    
    public void reiniciarSemana2(Usuario usuario){
        this.removeAll();  
        veces = guardarSemana;
        panelManager.mostrarPanelUsuario(usuario);
    }
    
    
    public void armarPanelCalendario(Usuario usuario,Odontologo odontologo){
        
        
        this.setLayout(new BorderLayout());
        
        //PANEL CENTRAL CALENDARIO
        
        guardarSemana = veces;
        armarBotones(usuario,odontologo);
        
        //PANEL NORTE
        panelNorte = new JPanel();
        lblMes = new JLabel(obtenerMes(mes) + "   - ");
        lblAño = new JLabel(año);
        panelNorte.add(lblMes);
        panelNorte.add(lblAño);
        this.add(panelNorte,NORTH);
        
        //PANEL IZQUIERDO
        panelIzq = new JPanel(new CardLayout());
        btnIzq = new JButton("<<");
        panelIzq.add(btnIzq,WEST);
        this.add(panelIzq,WEST);  
        
        //PANEL DERECHO
        panelDer = new JPanel(new CardLayout());
        btnDer = new JButton(">>");
        panelDer.add(btnDer,EAST);
        this.add(panelDer,EAST);  

        //PANEL SUR (BOTONERA)  
        panelSur = new JPanel();
        btnVolver = new JButton("Volver");
        panelSur.add(btnVolver);
        this.add(panelSur,SOUTH);

        this.setVisible(true);
        
        btnDer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adelantarMes(usuario,odontologo);
            }
        });
        
        btnIzq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrocederMes(usuario,odontologo);
            }
        });
        
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                veces = guardarSemana;
                panelManager.mostrarPanelUsuario(usuario);
            }
        });
    }
        
        public void armarBotones(Usuario usuario, Odontologo odontologo){
        this.removeAll();    
        cont=1;
        
        String[] texto_semana = new String[] {"Lunes","Martes","Miercoles","Jueves","viernes","Sabado","Domingo"};
        JButton[] dia = new JButton[40];
        JLabel[] semana = new JLabel[7];
        
        panelDias = new JPanel(new GridLayout(0,7));
        panelDias.setBackground(Color.LIGHT_GRAY);
        
        for (int i = 0; i < texto_semana.length; i++) {
            semana[i] = new JLabel(texto_semana[i],SwingConstants.CENTER);
            panelDias.add(semana[i]);
        }

        if (mes.equals("1") || mes.equals("3") || mes.equals("5") || mes.equals("7") || mes.equals("8") || mes.equals("10") || mes.equals("12")) {
            for (int i = 0; i < 40; i++) {
                    
                   if (i >= veces && cont <= 31) {
                    dia[i] = new JButton(String.valueOf(cont));
                    dia[i].setBackground(Color.white);
                    dia[i].setEnabled(false);
                    if(odontologo.getSemana()[veces]){
                        dia[i].setBackground(Color.green);
                        dia[i].setEnabled(true);
                }
                    
                    cont++;
                    veces++;
                    if (veces > 6) {
                        dia[i].setBackground(Color.gray);
                        veces = 0;
                    }
                    panelDias.add(dia[i]);
                }
                
                
                else{
                    dia[i] = new JButton();
                    dia[i].setVisible(false);
                    dia[i].setEnabled(false);
                    panelDias.add(dia[i]);
                }
                
                dia[i].addActionListener(new ActionListener() {
                   @Override 
                   public void actionPerformed(ActionEvent e) {
                           JButton b = (JButton) e.getSource();
                           panelManager.mostrarPanelTurnos(usuario,odontologo,b.getText(),Integer.valueOf(mes),Integer.valueOf(año));
                       }
                   
                });
            }
            
        }

        
        if (mes.equals("4") || mes.equals("6") || mes.equals("9") || mes.equals("11")) {
            for (int i = 0; i < 40; i++) {
                   
                
                if (i >= veces && cont <= 30) {
                    dia[i] = new JButton(String.valueOf(cont));
                    dia[i].setBackground(Color.white);
                    dia[i].setEnabled(false);
                    if(odontologo.getSemana()[veces]){
                        dia[i].setBackground(Color.green);
                        dia[i].setEnabled(true);
                }
                    
                    cont++;
                    veces++;
                    if (veces > 6) {
                        dia[i].setBackground(Color.gray);
                        veces = 0;
                    }
                    panelDias.add(dia[i]);
                }
                
                
                else{
                    dia[i] = new JButton();
                    dia[i].setVisible(false);
                    dia[i].setEnabled(false);
                    panelDias.add(dia[i]);
                }
                
                dia[i].addActionListener(new ActionListener() {
                   @Override 
                   public void actionPerformed(ActionEvent e) {
                           JButton b = (JButton) e.getSource();
                           panelManager.mostrarPanelTurnos(usuario,odontologo,b.getText(),Integer.valueOf(mes),Integer.valueOf(año));
                       }
                   
                });
                
            }
            
        }
        
        if (mes.equals("2")) {
            for (int i = 0; i < 40; i++) {
                
                if (i >= veces && cont <= 28) {
                    dia[i] = new JButton(String.valueOf(cont));
                    dia[i].setBackground(Color.white);
                    dia[i].setEnabled(false);
                    if(odontologo.getSemana()[veces]){
                        dia[i].setBackground(Color.green);
                        dia[i].setEnabled(true);
                }
                    
                    cont++;
                    veces++;
                    if (veces > 6) {
                        dia[i].setBackground(Color.gray);
                        veces = 0;
                    }
                    panelDias.add(dia[i]);
                }
                
                
                else{
                    dia[i] = new JButton();
                    dia[i].setVisible(false);
                    dia[i].setEnabled(false);
                    panelDias.add(dia[i]);
                }
                
                dia[i].addActionListener(new ActionListener() {
                   @Override 
                   public void actionPerformed(ActionEvent e) {
                           JButton b = (JButton) e.getSource();
                           panelManager.mostrarPanelTurnos(usuario,odontologo,b.getText(),Integer.valueOf(mes),Integer.valueOf(año));
                       }
                   
                });
            }

        }
        
        
        this.add(panelDias,CENTER);
        System.out.println(mes);
    }

    
    private String obtenerMes(String mes){    
        String[] meses = new String[]{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        return  meses[Integer.valueOf(mes)-1];
    }
    
    private void adelantarMes(Usuario usuario,Odontologo odontologo){
        mes = String.valueOf(Integer.valueOf(mes)+1);
        if(mes.equals("13")){
            mes = "1";
            año = String.valueOf(Integer.valueOf(año)+1);
        }
        cont=1;
        panelManager.mostrarPanelCalendario(usuario, odontologo);
    }
    
    
    private void retrocederMes(Usuario usuario,Odontologo odontologo){
        mes = String.valueOf(Integer.valueOf(mes)-1);
        if(mes.equals("0")){
            mes = "12";
            año = String.valueOf(Integer.valueOf(año)-1);
        }
        
        
        if (mes.equals("1") || mes.equals("3") || mes.equals("5") || mes.equals("7") || mes.equals("8") || mes.equals("10") || mes.equals("12")) {
            for (int i = 31+cont-1; i > 0; i--) {
                veces--;
                
                if (veces == -1) {
                    veces = 6;
                }
            }
        }
        
        if (mes.equals("4") || mes.equals("6") || mes.equals("9") || mes.equals("11")) {
            for (int i = 30+cont-1; i > 0; i--) {
                veces--;
                
                if (veces == -1) {
                    veces = 6;
                }
            }
        }
        
        if (mes.equals("2")) {
            for (int i = 28+cont-1; i > 0; i--) {
                veces--;
                
                if (veces == -1) {
                    veces = 6;
                }
            }
        }
        cont=1;
        
        panelManager.mostrarPanelCalendario(usuario,odontologo);
    
    }
    
    
    
    
}

