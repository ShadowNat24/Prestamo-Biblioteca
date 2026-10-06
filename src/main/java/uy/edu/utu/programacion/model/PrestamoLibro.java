package uy.edu.utu.programacion.model;

public class PrestamoLibro {
    private String estudiante;
    private String tituloLibro;
    private int diasPrestamo;
    private boolean consultaEnSala;

    public PrestamoLibro() {}

    public PrestamoLibro(String estudiante, String tituloLibro, int diasPrestamo, boolean consultaEnSala) {
        this.estudiante = estudiante;
        this.tituloLibro = tituloLibro;
        this.diasPrestamo = diasPrestamo;
        this.consultaEnSala = consultaEnSala;
    }

    public String getEstudiante() { return estudiante; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }
    public String getTituloLibro() { return tituloLibro; }
    public void setTituloLibro(String tituloLibro) { this.tituloLibro = tituloLibro; }
    public int getDiasPrestamo() { return diasPrestamo; }
    public void setDiasPrestamo(int diasPrestamo) { this.diasPrestamo = diasPrestamo; }
    public boolean isConsultaEnSala() { return consultaEnSala; }
    public void setConsultaEnSala(boolean consultaEnSala) { this.consultaEnSala = consultaEnSala; }

    @Override
    public String toString() {
        return "PrestamoLibro{" +
                "estudiante='" + estudiante + "'" +
                ", tituloLibro='" + tituloLibro + "'" +
                ", diasPrestamo=" + diasPrestamo +
                ", consultaEnSala=" + consultaEnSala +
                '}';
    }
}