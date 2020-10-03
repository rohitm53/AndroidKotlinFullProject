package com.rohit.ecommerce.productdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rohit.ecommerce.EcomerceApplication
import com.rohit.ecommerce.models.Product
import com.rohit.ecommerce.repos.Git_Hub_Url
import com.rohit.ecommerce.repos.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject


const val Git_Hub_Url =
    "https://gist.githubusercontent.com/rohitm53/4c57c16a0c87700421e44d95b0ab04bd/raw/cb9d733dad40029702a10b306ca91d7100b357f3/shopping_product_json"

class ProductDetailViewModal : ViewModel() {

    init {
        EcomerceApplication.applicationComponent.inject(this)
    }

    @Inject lateinit var productsRepository: ProductsRepository


    val productDetail = MutableLiveData<Product>()
    fun fetchProductDetails(productTitleStr:String) {
        ///Coroutine stuff
        viewModelScope.launch(Dispatchers.Default) {
            productDetail.postValue(productsRepository.fetchProduct(productTitleStr))
        }
    }

}