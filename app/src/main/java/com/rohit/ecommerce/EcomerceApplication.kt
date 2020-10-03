package com.rohit.ecommerce

import android.app.Application
import com.rohit.ecommerce.dependencyinjection.ApplicationComponent
import com.rohit.ecommerce.dependencyinjection.ApplicationModules
import com.rohit.ecommerce.dependencyinjection.DaggerApplicationComponent

class EcomerceApplication :Application() {

    companion object {
        lateinit var ecomerceApplication: EcomerceApplication
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        ecomerceApplication = this
        applicationComponent = DaggerApplicationComponent.builder().applicationModules(
            ApplicationModules(this)
        ).build()

    }

}