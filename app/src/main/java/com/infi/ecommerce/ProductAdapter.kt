package com.infi.ecommerce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.infi.ecommerce.models.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private var productList : ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(holder.itemView.context).load(productList[position].photoUrl).into(holder.iv_photo)
        holder.tv_title.text = productList[position].title
    }

    override fun getItemCount(): Int = productList.size

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        val iv_photo = itemView.findViewById<ImageView>(R.id.iv_photo)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
    }

}