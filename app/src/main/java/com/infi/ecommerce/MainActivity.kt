package com.infi.ecommerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.infi.ecommerce.models.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productList = arrayListOf<Product>()
        for(i in 0..100){
            productList.add(Product("Organic Apple","https://via.placeholder.com/150/FF0000/FFFFFF",1.99))
        }

        rv_items.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter = ProductAdapter(productList)
        }
    }

}