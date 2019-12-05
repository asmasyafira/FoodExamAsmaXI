package com.example.foodexamasmaxi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodexamasmaxi.R
import com.example.foodexamasmaxi.activities.DetailActivity
import com.example.foodexamasmaxi.model.MealsItem
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_row_seafood.view.*
import org.jetbrains.anko.startActivity

class MealsAdapter(
    private val dataMeals: List<MealsItem?>?, private val context: Context?
) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_row_seafood,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dataMeals?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataMeals?.get(position)!!)
    }

    class ViewHolder(override val containerView: View?) :
        RecyclerView.ViewHolder(containerView!!), LayoutContainer {

        fun bind(mealsItem: MealsItem) {
            itemView.tv_seafood_row.text = mealsItem.strMeal
            Picasso.get().load( mealsItem.strMealThumb)
                .resize(120, 120)
                .centerCrop()
                .into(itemView.img_seafood_row)

            containerView?.setOnClickListener{
                it.context.startActivity<DetailActivity>(
                    DetailActivity.ID to mealsItem.idMeal.toString(),
                    DetailActivity.TITLE to mealsItem.strMeal!!,
                    DetailActivity.IMAGES to mealsItem.strMealThumb!!
                )
            }
        }

    }
}