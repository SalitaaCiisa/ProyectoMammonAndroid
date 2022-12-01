package com.example.proyectomammon.interfaces;

import com.example.proyectomammon.resources.Cuentas;
import com.example.proyectomammon.resources.cuenta_api.CuentasResourceApus;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CuentasAPI {
    @GET("v1/accounts")
    public Call<List<CuentasResourceApus>> find(@Query("link_token") String link_token, @Header("Authorization") String api_key);
}
