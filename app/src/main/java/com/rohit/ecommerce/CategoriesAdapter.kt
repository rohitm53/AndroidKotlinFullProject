package com.rohit.ecommerce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.categories_row.view.*

class CategoriesAdapter(private val listCategories: List<String>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_category.text = listCategories[position]
    }

    override fun getItemCount() : Int = listCategories.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tv_category = view.tv_category
    }

}
