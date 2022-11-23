package com.example.proyectomammon.interfaces;

import com.example.proyectomammon.resources.Cuentas;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CuentasAPI {
    @GET("v1/accounts?")
    public Call<JsonArray> find(@Query("link_token") String link_token, @Header("Authorization") String api_key);
}
