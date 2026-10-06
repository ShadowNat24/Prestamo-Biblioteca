package uy.edu.utu.programacion.controller;

import java.util.List;
import javax.swing.JOptionPane;
import uy.edu.utu.programacion.model.PrestamoLibro;
import uy.edu.utu.programacion.service.PrestamoLibro_Service;
import uy.edu.utu.programacion.view.PrestamoLibro_Vista;

public class PrestamoLibro_Controlador {
    private final PrestamoLibro_Vista vista;
    private final PrestamoLibro_Service service;

    public PrestamoLibro_Controlador(PrestamoLibro_Vista vista, PrestamoLibro_Service service) {
        this.vista = vista;
        this.service = service;
        registrarListeners();
        actualizarTabla();
    }

    private void registrarListeners() {
        vista.setAccionAgregar(e -> agregarPrestamo());
        vista.setAccionCancelar(e -> vista.limpiarCampos());
        vista.setAccionEliminar(e -> eliminarPrestamo());
    }

    private void agregarPrestamo() {
        try {
            PrestamoLibro prestamo = vista.obtenerPrestamoFormulario();
            service.agregarPrestamo(prestamo);
            actualizarTabla();
            vista.limpiarCampos();
            vista.mostrarMensaje("Préstamo registrado correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            vista.mostrarError("Los días de préstamo deben ser un número entero.");
        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    private void eliminarPrestamo() {
        int fila = vista.obtenerFilaSeleccionada();
        if (fila < 0) {
            vista.mostrarError("Debe seleccionar un préstamo de la tabla para eliminarlo.");
            return;
        }
        if (vista.confirmarAccion("¿Está seguro de que desea eliminar el préstamo seleccionado?")
                == JOptionPane.YES_OPTION) {
            try {
                service.eliminarPrestamo(fila);
                actualizarTabla();
                vista.limpiarCampos();
            } catch (IllegalArgumentException ex) {
                vista.mostrarError(ex.getMessage());
            }
        }
    }

    private void actualizarTabla() {
        List<PrestamoLibro> prestamos = service.obtenerPrestamos();
        vista.mostrarPrestamos(prestamos);
    }
}