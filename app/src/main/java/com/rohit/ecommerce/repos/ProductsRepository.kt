package com.rohit.ecommerce.repos

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rohit.ecommerce.EcomerceApplication
import com.rohit.ecommerce.models.Product
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import javax.inject.Inject

const val Git_Hub_Url= "https://gist.githubusercontent.com/rohitm53/4c57c16a0c87700421e44d95b0ab04bd/raw/cb9d733dad40029702a10b306ca91d7100b357f3/shopping_product_json"

const val BASE_URL="https://finepointmobile.com/"

class ProductsRepository {

    init {
        EcomerceApplication.applicationComponent.inject(this)
    }

    @Inject lateinit var ecommerceApi: EcommerceApi

    ///Coroutine methods
    suspend fun fetchAllProductsRetrofit() : List<Product>{
        return ecommerceApi.fetchAllProducts()
    }

    suspend fun searchForProduct(search_term:String) : List<Product>{
        return  fetchAllProductsRetrofit().filter { it.title.contains(search_term,true) }
    }

    suspend fun fetchProduct(productTitleStr:String) : Product {
        return  fetchAllProductsRetrofit().first { it.title ==productTitleStr}
    }

//    ///RxJava Methods
//    fun getProductByName(name:String) : Single<Product> {
//        return Single.create<Product>{
//            val product = fetchProducts().first { it.title==name }
//            it.onSuccess(product)
//        }
//    }
//
//
//    fun getAllProducts(): Single<List<Product>> {
//        return Single.create<List<Product>>{
//            it.onSuccess(fetchProducts())
//        }
//    }
//
//    fun fetchProducts() : List<Product>{
//        val json=URL(Git_Hub_Url).readText()
//        return Gson().fromJson(json,Array<Product>::class.java).toList()
//    }

}