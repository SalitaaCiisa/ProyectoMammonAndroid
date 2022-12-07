package com.example.proyectomammon.resources;

public class Cobros {
    private String nombreCobro, cobrador, fechaCobro, descripcion, frecuencia;
    private int monto, id;

    //Constructores----------------------------------------------------
    public Cobros() {
    }

    public Cobros(String nombreCobro, String cobrador, String fechaCobro, String descripcion, String frecuencia, int monto) {
        this.nombreCobro = nombreCobro;
        this.cobrador = cobrador;
        this.fechaCobro = fechaCobro;
        this.descripcion = descripcion;
        this.frecuencia = frecuencia;
        this.monto = monto;
    }

    //Getter and Setters ------------------------------------------
    public String getNombreCobro() {
        return nombreCobro;
    }

    public void setNombreCobro(String nombreCobro) {
        this.nombreCobro = nombreCobro;
    }

    public String getCobrador() {
        return cobrador;
    }

    public void setCobrador(String cobrador) {
        this.cobrador = cobrador;
    }

    public String getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(String fechaCobro) {
        this.fechaCobro = fechaCobro;
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
        return "Cobros{" +
                "nombreCobro='" + nombreCobro + '\'' +
                ", cobrador='" + cobrador + '\'' +
                ", fechaCobro='" + fechaCobro + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", frecuencia='" + frecuencia + '\'' +
                ", monto=" + monto +
                ", id=" + id +
                '}';
    }
}
