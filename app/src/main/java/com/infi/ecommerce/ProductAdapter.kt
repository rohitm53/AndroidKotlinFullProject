package com.infi.ecommerce

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.infi.ecommerce.models.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private var productList : List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row,parent,false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context,ProductDetails::class.java)
            intent.putExtra("title",productList[viewHolder.adapterPosition].title)
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        Picasso.get().load(product.photoUrl).into(holder.iv_photo);
        holder.tv_title.text = product.title
        holder.tv_price.text = product.price.toString()
    }

    override fun getItemCount(): Int = productList.size

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        val iv_photo = itemView.findViewById<ImageView>(R.id.iv_photo)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        val tv_price = itemView.findViewById<TextView>(R.id.tv_price)
    }

}