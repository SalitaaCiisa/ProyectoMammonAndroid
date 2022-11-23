package com.example.proyectomammon.interfaces;

import com.example.proyectomammon.resources.Cuentas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CuentasAPI {
    @GET("v1/accounts?")
    public Call<String> find(@Query("link_token") String link_token, @Header("Authorization") String api_key);
}
