package uy.edu.utu.programacion.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import uy.edu.utu.programacion.model.PrestamoLibro;

public class PrestamoLibro_Vista extends JFrame {
    private final JTextField txtEstudiante = new JTextField(20);
    private final JTextField txtTituloLibro = new JTextField(20);
    private final JTextField txtDiasPrestamo = new JTextField(20);
    private final JCheckBox chkConsultaEnSala = new JCheckBox("Consulta en sala");
    private final JButton btnAgregar = new JButton("Agregar");
    private final JButton btnCancelar = new JButton("Cancelar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JTable tablaPrestamos;
    private final DefaultTableModel modeloTabla;

    public PrestamoLibro_Vista() {
        setTitle("Préstamos de biblioteca");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        modeloTabla = new DefaultTableModel(
                new Object[]{"Estudiante", "Libro", "Días", "Consulta en sala"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaPrestamos = new JTable(modeloTabla);
        organizarComponentes();
    }

    private void organizarComponentes() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.setBorder(BorderFactory.createTitledBorder("Datos del préstamo"));
        formulario.add(new JLabel("Estudiante:")); formulario.add(txtEstudiante);
        formulario.add(new JLabel("Título del libro:")); formulario.add(txtTituloLibro);
        formulario.add(new JLabel("Días de préstamo:")); formulario.add(txtDiasPrestamo);
        formulario.add(new JLabel("Tipo de préstamo:")); formulario.add(chkConsultaEnSala);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botones.add(btnAgregar); botones.add(btnCancelar); botones.add(btnEliminar);

        JPanel superior = new JPanel(new BorderLayout(10, 10));
        superior.add(formulario, BorderLayout.CENTER);
        superior.add(botones, BorderLayout.SOUTH);

        JScrollPane scroll = new JScrollPane(tablaPrestamos);
        scroll.setBorder(BorderFactory.createTitledBorder("Préstamos registrados"));

        principal.add(superior, BorderLayout.NORTH);
        principal.add(scroll, BorderLayout.CENTER);
        add(principal);
    }

    public PrestamoLibro obtenerPrestamoFormulario() {
        return new PrestamoLibro(
                txtEstudiante.getText().trim(),
                txtTituloLibro.getText().trim(),
                Integer.parseInt(txtDiasPrestamo.getText().trim()),
                chkConsultaEnSala.isSelected());
    }

    public void limpiarCampos() {
        txtEstudiante.setText("");
        txtTituloLibro.setText("");
        txtDiasPrestamo.setText("");
        chkConsultaEnSala.setSelected(false);
        txtEstudiante.requestFocus();
    }

    public void mostrarPrestamos(List<PrestamoLibro> prestamos) {
        modeloTabla.setRowCount(0);
        for (PrestamoLibro p : prestamos) {
            modeloTabla.addRow(new Object[]{
                p.getEstudiante(), p.getTituloLibro(), p.getDiasPrestamo(),
                p.isConsultaEnSala() ? "Sí" : "No"});
        }
    }

    public int obtenerFilaSeleccionada() { return tablaPrestamos.getSelectedRow(); }

    public void setAccionAgregar(ActionListener listener) { btnAgregar.addActionListener(listener); }
    public void setAccionCancelar(ActionListener listener) { btnCancelar.addActionListener(listener); }
    public void setAccionEliminar(ActionListener listener) { btnEliminar.addActionListener(listener); }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    public int confirmarAccion(String mensaje) {
        return JOptionPane.showConfirmDialog(this, mensaje, "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}