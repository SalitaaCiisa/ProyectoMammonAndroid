package com.example.proyectomammon.resources;

public class Cuentas {
    private String nombreCuenta, link_token, api_key, id_cuenta;
    private int id;

    //Constructores--------------------------------------------------------------
    public Cuentas() {
    }

    public Cuentas(String nombreCuenta, String link_token, String api_key, String id_cuenta, int id) {
        this.nombreCuenta = nombreCuenta;
        this.link_token = link_token;
        this.api_key = api_key;
        this.id_cuenta = id_cuenta;
        this.id = id;
    }

    //Getter and Setters -------------------------------------------------------
    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getLink_token() {
        return link_token;
    }

    public void setLink_token(String link_token) {
        this.link_token = link_token;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(String id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //ToString ----------------------------------------------------------
    @Override
    public String toString() {
        return "Cuentas{" +
                "nombreCuenta='" + nombreCuenta + '\'' +
                ", link_token='" + link_token + '\'' +
                ", api_key='" + api_key + '\'' +
                ", id_cuenta='" + id_cuenta + '\'' +
                ", id=" + id +
                '}';
    }
}
