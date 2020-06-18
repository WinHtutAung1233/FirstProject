package com.whapj.myproject.api

import com.whapj.myproject.model.menu.Menu
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient{
    private val apiInterface: ApiInterface

    companion object{
        const val BASE_URL = "http://food-delivery.khaingthinkyi.me/api/"
    }
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface=retrofit.create(ApiInterface::class.java)
    }

//    fun getRestaurant(): Call<City> {
//        return apiInterface.getRestaurantInterface()
//    }
    fun getMenu(): Call<Menu> {
        return apiInterface.getMenuInterface()
    }

}