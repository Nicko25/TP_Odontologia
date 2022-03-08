package ui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import negocio.Usuario;

import javax.swing.*;
import negocio.Odontologo;

public class PanelManager {

    private JFrame frame;
    private PanelLogin panelLogin;
    private PanelAdmin panelAdmin;
    private PanelAltas panelAlta;
    private PanelUsuario panelUsuario;
    private PanelCalendario panelCalendario;
    private PanelTurnos panelTurnos;
    private PanelUsuario panelFormulario;

    public void armarManager() throws IOException
    {
        frame = new JFrame();
        frame.setBounds(1100,200,800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Trabajo Practico Final - Grupo: 6 Turno: Noche");
        frame.setIconImage(ImageIO.read(new File("muela.png")));
        

        panelLogin = new PanelLogin(this);
        panelLogin.armarPanelLogin();

        panelAdmin = new PanelAdmin(this);
        panelAdmin.armarPanelAdminUser();
        
        panelAlta = new PanelAltas(this);
        
        panelUsuario = new PanelUsuario(this);
        
        panelCalendario = new PanelCalendario(this);
        
        panelTurnos = new PanelTurnos(this);
        
    }

    public void mostrarPanelLogin(){
        frame.getContentPane().removeAll();
        panelLogin.armarPanelLogin();
        frame.getContentPane().add(panelLogin);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    //MENU ADMIN
    public void mostrarPanelAdmin(){
        frame.getContentPane().removeAll();
        panelAdmin.armarPanelAdmin();
        frame.getContentPane().add(panelAdmin);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    //MENU USUARIO
    public void mostrarPanelUsuario(Usuario usuario){
        frame.getContentPane().removeAll();
        panelUsuario.armarPanelUsuario(usuario);
        frame.getContentPane().add(panelUsuario);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    public void mostrarPanelUsuarioVolver(Usuario usuario){
        frame.getContentPane().removeAll();
        panelCalendario.reiniciarSemana2(usuario);
        frame.getContentPane().add(panelUsuario);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    //MENU ODONTOLOGO
    public void mostrarPanelOdontologo(){
        frame.getContentPane().removeAll();
        panelAlta.armarPanelAltaOdontologo();
        frame.getContentPane().add(panelAlta);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    //MENU CALENDARIO
    public void mostrarPanelCalendario(Usuario usuario,Odontologo odontologo){
        frame.getContentPane().removeAll();
        panelCalendario.armarPanelCalendario(usuario, odontologo);
        frame.getContentPane().add(panelCalendario);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    //MENU CALENDARIO APLICANDO VOLVER
    public void mostrarPanelCalendarioVolver(Usuario usuario,Odontologo odontologo){
        frame.getContentPane().removeAll();
        panelCalendario.reiniciarSemana(usuario, odontologo);
        frame.getContentPane().add(panelCalendario);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    //MENU TURNOS
    public void mostrarPanelTurnos(Usuario usuario,Odontologo odontologo,String dia,int mes,int año){
        frame.getContentPane().removeAll();
        panelTurnos.armarPanelTurnosPaso1(usuario,odontologo,dia,mes,año);
        frame.getContentPane().add(panelTurnos);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    public void mostrarPanelTurnos2(Usuario usuario,Odontologo odontologo,String dia,int mes,int año,String horario){
        frame.getContentPane().removeAll();
        panelTurnos.armarPanelTurnosPaso2(usuario,odontologo,dia,mes,año,horario);
        frame.getContentPane().add(panelTurnos);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    public void mostrarPanelTurnos3(Usuario usuario,Odontologo odontologo,String dia,int mes,int año,String horario,String nombre,String apellido){
        frame.getContentPane().removeAll();
        panelTurnos.armarPanelTurnosPaso3(usuario,odontologo,dia,mes,año,horario,nombre,apellido);
        frame.getContentPane().add(panelTurnos);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    
    // MENU CREAR USUARIOS
    public void mostrarPanelAltaUsuario(){
        frame.getContentPane().removeAll();
        panelAlta.armarPanelAltaUsuario();
        frame.getContentPane().add(panelAlta);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    // MENU CONSULTAR TURNOS DEL USUARIOS
    public void mostrarPanelAdministrarTurnos(Usuario usuario){
        frame.getContentPane().removeAll();
        panelUsuario.armarPanelAdministrarTurnos(usuario);
        frame.getContentPane().add(panelUsuario);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    
    
    //MENU MODIFICAR USUARIOS
    public void mostrarPanelFormularioUsuario(Usuario usuario){
        panelAlta.armarPanelAltaUsuario();
        panelAlta.llenarFormularioUsuario(usuario);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelAlta);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }
    //MENU MODIFICAR ODONTOLOGOS
    public void mostrarPanelFormularioOdontologo(Odontologo odontologo){
        panelAlta.armarPanelAltaOdontologo();
        panelAlta.llenarFormularioOdontologo(odontologo);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelAlta);
        frame.getContentPane().validate(); //refrezcar ventana
        frame.getContentPane().repaint(); //refrezcar ventana
    }

    public void showFrame(){
        frame.setVisible(true);
    }

}