package com.example.proyectomammon.resources;

public class Abonos {
    private String nombreAbono, abonador, fechaAbono, descripcion, frecuencia;
    private int monto, id;

    //Constructores----------------------------------------------------
    public Abonos() {
    }

    public Abonos(String nombreAbono, String abonador, String fechaAbono, String descripcion, String frecuencia, int monto, int id) {
        this.nombreAbono = nombreAbono;
        this.abonador = abonador;
        this.fechaAbono = fechaAbono;
        this.descripcion = descripcion;
        this.frecuencia = frecuencia;
        this.monto = monto;
    }

    //Getter and Setters ------------------------------------------
    public String getNombreAbono() {
        return nombreAbono;
    }

    public void setNombreAbono(String nombreAbono) {
        this.nombreAbono = nombreAbono;
    }

    public String getAbonador() {
        return abonador;
    }

    public void setAbonador(String abonador) {
        this.abonador = abonador;
    }

    public String getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(String fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //ToString----------------------------------------------------------------------
    @Override
    public String toString() {
        return "Abonos{" +
                "nombreAbono='" + nombreAbono + '\'' +
                ", abonador='" + abonador + '\'' +
                ", fechaAbono='" + fechaAbono + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", frecuencia='" + frecuencia + '\'' +
                ", monto=" + monto +
                ", id=" + id +
                '}';
    }
}
