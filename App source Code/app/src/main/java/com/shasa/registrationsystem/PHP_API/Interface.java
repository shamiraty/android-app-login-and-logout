package com.shasa.registrationsystem.PHP_API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Interface {

    @FormUrlEncoded
    @POST("login.php")
    Call<ApiResponse>login(@Field("username")String username,@Field("password")String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<ApiResponse>registration_users(@Field("username")String username,@Field("password")String password,@Field("email")String email,@Field("department")String department);
}
