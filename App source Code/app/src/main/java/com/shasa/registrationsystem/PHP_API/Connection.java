package com.shasa.registrationsystem.PHP_API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {
    private  static  final String BASE_URL="http://192.168.43.248/system/";
    private  static Retrofit retrofit=null;

    public  static  Retrofit getRetrofit()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
