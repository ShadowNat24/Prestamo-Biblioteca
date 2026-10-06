package uy.edu.utu.programacion;

import java.awt.EventQueue;
import uy.edu.utu.programacion.controller.PrestamoLibro_Controlador;
import uy.edu.utu.programacion.service.PrestamoLibro_Service;
import uy.edu.utu.programacion.view.PrestamoLibro_Vista;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            PrestamoLibro_Vista vista = new PrestamoLibro_Vista();
            PrestamoLibro_Service service = new PrestamoLibro_Service();
            new PrestamoLibro_Controlador(vista, service);
            vista.setVisible(true);
        });
    }
}