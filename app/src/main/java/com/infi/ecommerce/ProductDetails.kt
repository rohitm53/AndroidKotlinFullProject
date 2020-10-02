package com.infi.ecommerce

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = getIntent().getStringExtra("title")
        val photo_url = getIntent().getStringExtra("photo_url")
        val price = getIntent().getDoubleExtra("price", 0.0)

        tv_productname.text=title

        Picasso.get().load(photo_url).into(iv_photo)



        btn_availablity.setOnClickListener {

            AlertDialog.Builder(this).
                    setMessage("Hey $title is in stock").
                    setPositiveButton("Ok" ) { p0, p1 -> }.
                    create().
                    show()

        }
    }
}