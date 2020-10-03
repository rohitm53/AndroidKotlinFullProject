package com.rohit.ecommerce.dependencyinjection

import com.rohit.ecommerce.MainFragmentViewModel
import com.rohit.ecommerce.productdetails.ProductDetailViewModal
import com.rohit.ecommerce.repos.ProductsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModules::class])
interface ApplicationComponent {

    fun inject(mainFragmentViewModel: MainFragmentViewModel)

    fun inject(productsRepository: ProductsRepository)

    fun inject(productDetailViewModal: ProductDetailViewModal)
}