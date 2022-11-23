package com.example.proyectomammon.resources;

import androidx.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CuentasApi {
    private String id; //id
    private String numeroCuenta; //number
    private String tipoCuenta; //official_name
    private String fechaHoraUltimoMovimiento; //refreshed_at
    private int balance; //balance->available

    public CuentasApi(@NonNull JsonElement jsonElement){
        JsonObject json = jsonElement.getAsJsonObject();
        try{
            this.id = json.getAsJsonPrimitive("id").getAsString();
            this.numeroCuenta = json.getAsJsonPrimitive("number").getAsString();
            this.tipoCuenta = json.getAsJsonPrimitive("official_name").getAsString();
            this.fechaHoraUltimoMovimiento = json.getAsJsonPrimitive("refreshed_at").getAsString();
            this.balance = json.getAsJsonObject("balance").getAsJsonPrimitive("available").getAsInt();
        }catch (Exception ex){

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getFechaHoraUltimoMovimiento() {
        return fechaHoraUltimoMovimiento;
    }

    public void setFechaHoraUltimoMovimiento(String fechaHoraUltimoMovimiento) {
        this.fechaHoraUltimoMovimiento = fechaHoraUltimoMovimiento;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CuentasApi{" +
                "id='" + id + '\'' +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", fechaHoraUltimoMovimiento='" + fechaHoraUltimoMovimiento + '\'' +
                ", balance=" + balance +
                '}';
    }
}
