package com.burhanudin.menampilkanlistapi.rest;

import com.burhanudin.menampilkanlistapi.model.Aplikasi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("list.php")
    Call<Aplikasi> getAplikasi();
}
