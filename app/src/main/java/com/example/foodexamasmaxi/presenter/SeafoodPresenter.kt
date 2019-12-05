package com.example.foodexamasmaxi.presenter

import android.annotation.SuppressLint
import com.example.foodexamasmaxi.model.MealsResponse
import com.example.foodexamasmaxi.network.Http
import com.example.foodexamasmaxi.view.MealsMainView
import retrofit2.Call
import retrofit2.Response

class SeafoodPresenter(var seafoodView: MealsMainView? = null):
    MealsBasePresenter<MealsMainView> {

    @SuppressLint("CheckResults")
    fun getDataSeafood(api_key:String){
        Http.retrofit.getSeafood(api_key)
            .enqueue(object : retrofit2.Callback<MealsResponse>{
                override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                    seafoodView?.error(t.message)
                }

                override fun onResponse(call: Call<MealsResponse>, response: Response<MealsResponse>
                ) {
                    seafoodView?.showDataMeals(response.body()?.meals!!)
                }

            })
    }
    override fun onAttach(view: MealsMainView) {
        seafoodView = view
    }

    override fun onDetach() {
        seafoodView = null
    }
}