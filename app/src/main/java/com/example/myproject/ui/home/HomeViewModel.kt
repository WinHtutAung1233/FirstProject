package com.example.myproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject.api.ApiClient
import com.example.myproject.model.menu.Menu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val menuApi: ApiClient = ApiClient()
    var results:MutableLiveData<Menu> = MutableLiveData()
    var articlesLoadError:MutableLiveData<Boolean> = MutableLiveData()
    var loading:MutableLiveData<Boolean> = MutableLiveData()

    fun getResults(): LiveData<Menu> = results

    fun getError():LiveData<Boolean> = articlesLoadError

    fun getLoading(): LiveData<Boolean> = loading

    fun loadResults(){

        loading.value = true
        val call = menuApi.getMenu()
        //call api
        call.enqueue(object : Callback<Menu> {
            override fun onFailure(call: Call<Menu>, t: Throwable) {
                loading.value = false
                articlesLoadError.value = true
            }

            override fun onResponse(
                call: Call<Menu>,
                response: Response<Menu>
            ) {
                response.isSuccessful.let {
                    loading.value = false
                    var resultList = Menu(
                        response?.body()?.menus?: emptyList())
                    results.value = resultList
                }
            }

        })
    }

}