package com.example.foodexamasmaxi.network

import com.example.foodexamasmaxi.model.MealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceClient {

    @GET("api/json/v1/1/filter.php?c=Seafood")
    fun getSeafood(
        @Query("api_key") api_key:String
    ): Call<MealsResponse>

    @GET("api/json/v1/1/filter.php?c=Dessert")
    fun getDessert(
        @Query("api_key") api_key:String
    ): Call<MealsResponse>
}