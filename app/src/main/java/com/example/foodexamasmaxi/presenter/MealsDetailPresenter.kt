package com.example.foodexamasmaxi.presenter

import android.content.Context
import com.example.foodexamasmaxi.view.MealsDetailView

class MealsDetailPresenter(var context: Context): MealsBasePresenter<MealsDetailView> {
    var detailView: MealsDetailView? = null

    override fun onAttach(view: MealsDetailView) {
        detailView = view
    }

    override fun onDetach() {
        detailView = null
    }
}