package com.rohit.ecommerce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rohit.ecommerce.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

class ProductAdapter(
    private var productList : List<Product>,
    private val onClickProduct: (Int,photoView:View)-> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        Picasso.get().load(product.photoUrl).into(holder.iv_photo);
        holder.tv_title.text = product.title
        holder.tv_price.text = product.price.toString()

        if(product.isOnSale){
            holder.iv_isOnSale.visibility=View.VISIBLE
        }else{
            holder.iv_isOnSale.visibility=View.GONE
        }

        holder.iv_photo.setOnClickListener {
            onClickProduct.invoke(position,holder.iv_photo)

        }

    }

    override fun getItemCount(): Int = productList.size

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        val iv_photo = itemView.findViewById<ImageView>(R.id.iv_photo)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        val tv_price = itemView.findViewById<TextView>(R.id.tv_price)
        val iv_isOnSale = itemView.iv_isOnSale;
    }

}