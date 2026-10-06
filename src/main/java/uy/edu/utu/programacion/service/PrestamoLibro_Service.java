package uy.edu.utu.programacion.service;

import java.util.ArrayList;
import java.util.List;
import uy.edu.utu.programacion.model.PrestamoLibro;

public class PrestamoLibro_Service {
    private static final int MAX_DIAS_PRESTAMO = 15;
    private static final int MAX_DIAS_CONSULTA_SALA = 1;
    private final List<PrestamoLibro> prestamos = new ArrayList<>();

    public void agregarPrestamo(PrestamoLibro prestamo) {
        validarPrestamo(prestamo);
        if (existePrestamo(prestamo.getEstudiante(), prestamo.getTituloLibro())) {
            throw new IllegalArgumentException("El estudiante ya tiene registrado ese libro.");
        }
        prestamos.add(prestamo);
    }

    private void validarPrestamo(PrestamoLibro prestamo) {
        if (prestamo == null) throw new IllegalArgumentException("El préstamo no puede ser nulo.");
        String estudiante = prestamo.getEstudiante() == null ? "" : prestamo.getEstudiante().trim();
        String titulo = prestamo.getTituloLibro() == null ? "" : prestamo.getTituloLibro().trim();

        if (estudiante.isEmpty()) throw new IllegalArgumentException("El nombre del estudiante no puede estar vacío.");
        if (titulo.isEmpty()) throw new IllegalArgumentException("El título del libro no puede estar vacío.");
        if (prestamo.getDiasPrestamo() <= 0) throw new IllegalArgumentException("Los días de préstamo deben ser mayores que cero.");
        if (prestamo.isConsultaEnSala() && prestamo.getDiasPrestamo() > MAX_DIAS_CONSULTA_SALA)
            throw new IllegalArgumentException("Un libro de consulta en sala puede prestarse como máximo 1 día.");
        if (!prestamo.isConsultaEnSala() && prestamo.getDiasPrestamo() > MAX_DIAS_PRESTAMO)
            throw new IllegalArgumentException("Un préstamo común puede durar como máximo 15 días.");
    }

    private boolean existePrestamo(String estudiante, String tituloLibro) {
        for (PrestamoLibro prestamo : prestamos) {
            if (prestamo.getEstudiante().trim().equalsIgnoreCase(estudiante.trim())
                    && prestamo.getTituloLibro().trim().equalsIgnoreCase(tituloLibro.trim())) return true;
        }
        return false;
    }

    public List<PrestamoLibro> obtenerPrestamos() { return new ArrayList<>(prestamos); }

    public void eliminarPrestamo(int indice) {
        if (indice < 0 || indice >= prestamos.size())
            throw new IllegalArgumentException("Debe seleccionar un préstamo válido para eliminar.");
        prestamos.remove(indice);
    }
}