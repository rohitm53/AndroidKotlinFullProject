package com.rohit.ecommerce.productdetails

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rohit.ecommerce.R
import com.rohit.ecommerce.repos.ProductsRepository
import com.rohit.ecommerce.viewmodelfactory.ViewModelFactory
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    private lateinit var  viewmodel: ProductDetailViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

//        viewmodel = ViewModelProviders.of(this,ViewModelFactory()).get(ProductDetailViewModal::class.java)
        viewmodel = ViewModelProvider(this).get(ProductDetailViewModal::class.java)

        val title = getIntent().getStringExtra("title") ?: ""

        viewmodel.fetchProductDetails(title)

        viewmodel.productDetail.observe(this, {
            tv_productname.text=it.title
            tv_price.text = "$ ${it.price}"
            Picasso.get().load(it.photoUrl).into(iv_photo)
        })

//        val product = ProductsRepository().getProductByName(title).
//        subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                tv_productname.text=it.title
//                tv_price.text = "$ ${it.price}"
//                Picasso.get().load(it.photoUrl).into(iv_photo)
//
//            },{
//
//            })

        btn_availablity.setOnClickListener {

            AlertDialog.Builder(this).
            setMessage("Hey $title is in stock").
            setPositiveButton("Ok" ) { p0, p1 -> }.
            create().
            show()
        }

        btn_addToCart.setOnClickListener {

        }
    }
}