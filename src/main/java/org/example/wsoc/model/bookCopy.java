package org.example.wsoc.model;

public class bookCopy {
    public int Id;
    public int IdLibro;
    public String NumeroInventario;
    public String EstadoFisico;
    public String FechaUltimoPrestamo;


    public int getId() {
        return Id;
    }

    public int getIdLibro() {
        return IdLibro;
    }

    public String getNumeroInventario() {
        return NumeroInventario;
    }

    public String getEstadoFisico() {
        return EstadoFisico;
    }

    public String getFechaUltimoPrestamo() {
        return FechaUltimoPrestamo;
    }
}
