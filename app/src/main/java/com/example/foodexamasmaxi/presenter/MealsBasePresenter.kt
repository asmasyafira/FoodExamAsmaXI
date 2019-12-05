package com.example.foodexamasmaxi.presenter

import com.example.foodexamasmaxi.view.MealsBaseView

interface MealsBasePresenter<in A : MealsBaseView> {
    fun onAttach(view: A)
    fun onDetach()
}