package com.example.foodexamasmaxi.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodexamasmaxi.BuildConfig

import com.example.foodexamasmaxi.R
import com.example.foodexamasmaxi.adapter.MealsGridAdapter
import com.example.foodexamasmaxi.model.MealsItem
import com.example.foodexamasmaxi.presenter.DessertPresenter
import com.example.foodexamasmaxi.view.MealsMainView
import kotlinx.android.synthetic.main.fragment_dessert.*
import org.jetbrains.anko.toast

/**
 * A simple [Fragment] subclass.
 */
class DessertFragment : Fragment(), MealsMainView {

    private lateinit var presenter: DessertPresenter
    private var mealsList: MutableList<MealsItem?>? = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dessert, container, false)
    }

    fun showLoading(state: Boolean) {
        if (state) {
            progressBarDessert?.setVisibility(View.VISIBLE)
        } else {
            progressBarDessert?.setVisibility(View.GONE)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoading(true)
        presenter = DessertPresenter()
        presenter.getDataDessert(BuildConfig.API_KEY)
    }

    override fun error(message: String?) {
        context?.toast(message!!)
    }

    override fun showDataMeals(item: List<MealsItem>) {
        showLoading(false)
        mealsList?.clear()
        mealsList?.addAll(item)
        val layoutManager = GridLayoutManager(context, 2)
        rv_meals_grid.layoutManager = layoutManager
        rv_meals_grid.adapter = MealsGridAdapter(mealsList, context)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
    }
}
