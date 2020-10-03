package com.rohit.ecommerce.repos

import com.rohit.ecommerce.models.Product
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface EcommerceApi {

    @GET("api/ecommerce/v1/allProducts")
    suspend fun fetchAllProducts() : List<Product>

//    @GET("api/ecommerce/v1/allProducts")
//    suspend fun fetchAllProducts() : Deferred<List<Product>>

}