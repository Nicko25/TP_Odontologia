package ui;

import java.io.IOException;
import ui.PanelManager;

public class Main {

    public static void main(String[] args) throws IOException {
	    System.out.println("Propuesta Arquitectura Swing");
	    PanelManager panelManager = new PanelManager();
	    panelManager.armarManager();
	    panelManager.mostrarPanelLogin();
	    panelManager.showFrame();


    }
}