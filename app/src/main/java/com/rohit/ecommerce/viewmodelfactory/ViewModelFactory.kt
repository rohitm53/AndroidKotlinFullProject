package com.rohit.ecommerce.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rohit.ecommerce.productdetails.ProductDetailViewModal
import java.lang.IllegalArgumentException

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(ProductDetailViewModal::class.java)){
            return ProductDetailViewModal() as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")


    }
}