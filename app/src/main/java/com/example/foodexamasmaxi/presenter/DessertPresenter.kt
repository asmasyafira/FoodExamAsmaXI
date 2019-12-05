package com.example.foodexamasmaxi.presenter

import android.annotation.SuppressLint
import com.example.foodexamasmaxi.model.MealsResponse
import com.example.foodexamasmaxi.network.Http
import com.example.foodexamasmaxi.view.MealsMainView
import retrofit2.Call
import retrofit2.Response

class DessertPresenter(var dessertView: MealsMainView? = null):
    MealsBasePresenter<MealsMainView> {

    @SuppressLint("CheckResults")
    fun getDataDessert(api_key:String){
        Http.retrofit.getDessert(api_key)
            .enqueue(object : retrofit2.Callback<MealsResponse>{
                override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                    dessertView?.error(t.message)
                }

                override fun onResponse(call: Call<MealsResponse>, response: Response<MealsResponse>
                ) {
                    dessertView?.showDataMeals(response.body()?.meals!!)
                }

            })
    }

    override fun onAttach(view: MealsMainView) {
        dessertView = view
    }

    override fun onDetach() {
        dessertView = null
    }
}