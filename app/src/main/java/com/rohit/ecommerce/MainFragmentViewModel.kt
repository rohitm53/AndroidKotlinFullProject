package com.rohit.ecommerce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.ecommerce.models.Product
import com.rohit.ecommerce.repos.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel : ViewModel() {

    val products = MutableLiveData<List<Product>>()

    fun setup(){
        viewModelScope.launch(Dispatchers.Default){
            val listProducts= ProductsRepository().fetchAllProductsRetrofit()
            products.postValue(listProducts)
        }
    }

    fun searchProduct(searchTerm : String){
        viewModelScope.launch(Dispatchers.Default){
            val listProducts= ProductsRepository().searchForProduct(searchTerm)
            products.postValue(listProducts)
        }
    }

}