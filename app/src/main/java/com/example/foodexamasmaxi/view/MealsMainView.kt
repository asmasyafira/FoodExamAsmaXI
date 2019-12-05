package com.example.foodexamasmaxi.view

import com.example.foodexamasmaxi.model.MealsItem

interface MealsMainView: MealsBaseView {
    fun error(message: String?)
    fun showDataMeals(item: List<MealsItem>)
}