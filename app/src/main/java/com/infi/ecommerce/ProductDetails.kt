package com.infi.ecommerce

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = getIntent().getStringExtra("title")
        tv_productname.text=title


        btn_availablity.setOnClickListener {

            AlertDialog.Builder(this).
                    setMessage("Hey $title is in stock").
                    setPositiveButton("Ok" ) { p0, p1 -> }.
                    create().
                    show()

        }
    }
}