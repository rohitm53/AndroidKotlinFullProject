package com.rohit.ecommerce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.ecommerce.models.Product
import com.rohit.ecommerce.repos.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel : ViewModel() {

    @Inject lateinit var productsRepository: ProductsRepository

    init {
        EcomerceApplication.applicationComponent.inject(this)
    }

    val products = MutableLiveData<List<Product>>()

    fun setup(){
        viewModelScope.launch(Dispatchers.Default){
            val listProducts= productsRepository.fetchAllProductsRetrofit()
            products.postValue(listProducts)
        }
    }

    fun searchProduct(searchTerm : String){
        viewModelScope.launch(Dispatchers.Default){
            val listProducts= productsRepository.searchForProduct(searchTerm)
            products.postValue(listProducts)
        }
    }

}