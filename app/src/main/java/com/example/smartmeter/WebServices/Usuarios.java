package com.example.smartmeter.WebServices;

import com.example.smartmeter.Modelo.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Usuarios {


    @POST("application/json")
    Call<Usuario> saveuser(@Body Usuario userRequest);

}
