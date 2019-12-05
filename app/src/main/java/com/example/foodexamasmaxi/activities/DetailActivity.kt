package com.example.foodexamasmaxi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodexamasmaxi.R
import com.example.foodexamasmaxi.model.MealsItem
import com.example.foodexamasmaxi.presenter.MealsDetailPresenter
import com.example.foodexamasmaxi.view.MealsDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), MealsDetailView {

    companion object{
        const val ID : String = "ID"
        const val TITLE : String = "TITLE"
        const val IMAGES : String = "IMAGES"
    }

    lateinit var presenter: MealsDetailPresenter
    lateinit var id : String
    lateinit var title : String
    lateinit var images : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter = MealsDetailPresenter(this)

        id = intent.getStringExtra(ID)
        title = intent.getStringExtra(TITLE)
        images = intent.getStringExtra(IMAGES)

        tv_detail.text = title
        Picasso.get().load(images)
            .resize(500, 500)
            .centerCrop()
            .into(iv_detail)
    }

    override fun loadDataDetailMeals(item: List<MealsItem>) {
    }

    override fun errorDetail(message: String?) {
        toast(message!!)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDetachView() {
        presenter.onDetach()
    }

}
