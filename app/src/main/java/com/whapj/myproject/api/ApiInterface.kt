package com.whapj.myproject.api

import com.whapj.myproject.model.menu.Menu
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{
//    @GET("setup/city")
//    fun getRestaurantInterface(): Call<City>

    @GET("setup/menu")
    fun getMenuInterface(): Call<Menu>
}