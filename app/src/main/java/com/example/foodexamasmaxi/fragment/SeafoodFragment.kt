package com.example.foodexamasmaxi.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodexamasmaxi.BuildConfig

import com.example.foodexamasmaxi.R
import com.example.foodexamasmaxi.adapter.MealsAdapter
import com.example.foodexamasmaxi.model.MealsItem
import com.example.foodexamasmaxi.presenter.SeafoodPresenter
import com.example.foodexamasmaxi.view.MealsMainView
import kotlinx.android.synthetic.main.fragment_seafood.*
import org.jetbrains.anko.toast

/**
 * A simple [Fragment] subclass.
 */
class SeafoodFragment : Fragment(), MealsMainView {

    private lateinit var presenter: SeafoodPresenter
    private var mealsList: MutableList<MealsItem?>? = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seafood, container, false)
    }

    fun showLoading(state: Boolean) {
        if (state) {
            progressBar?.setVisibility(View.VISIBLE)
        } else {
            progressBar?.setVisibility(View.GONE)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoading(true);
        presenter = SeafoodPresenter()
        presenter.getDataSeafood(BuildConfig.API_KEY)
    }

    override fun error(message: String?) {
        context?.toast(message!!)
    }

    override fun showDataMeals(item: List<MealsItem>) {
        showLoading(false)
        mealsList?.clear()
        mealsList?.addAll(item)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_meals.layoutManager = layoutManager
        rv_meals.adapter = MealsAdapter(mealsList, context)
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
