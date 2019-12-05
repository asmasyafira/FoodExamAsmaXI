package com.example.foodexamasmaxi.view

import com.example.foodexamasmaxi.model.MealsItem

interface MealsDetailView : MealsBaseView {
    fun loadDataDetailMeals(item: List<MealsItem>)
    fun errorDetail(message: String?)
}