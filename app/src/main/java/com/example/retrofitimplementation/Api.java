package com.example.retrofitimplementation;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

String BASE_URL="https://restcountries.eu/";

@GET("rest/v2/all")
Call<ArrayList<Country>> getCountries();


}
