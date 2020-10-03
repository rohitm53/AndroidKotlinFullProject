package com.rohit.ecommerce.dependencyinjection

import com.google.gson.GsonBuilder
import com.rohit.ecommerce.EcomerceApplication
import com.rohit.ecommerce.repos.BASE_URL
import com.rohit.ecommerce.repos.EcommerceApi
import com.rohit.ecommerce.repos.ProductsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

const val BASE_URL="https://finepointmobile.com/"

@Module
class ApplicationModules(val ecomerceApplication: EcomerceApplication) {

    @Singleton
    @Provides
    fun provideProductRepository() : ProductsRepository {
        return ProductsRepository()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideEcommerService(retrofit: Retrofit): EcommerceApi {
        return retrofit.create(EcommerceApi::class.java)
    }

}