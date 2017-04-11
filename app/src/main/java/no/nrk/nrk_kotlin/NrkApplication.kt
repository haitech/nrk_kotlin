package no.nrk.nrk_kotlin

import android.app.Application
import no.nrk.nrk_kotlin.business.depedencyInjection.AndroidModule
import no.nrk.nrk_kotlin.business.depedencyInjection.ApiModule
import no.nrk.nrk_kotlin.business.depedencyInjection.ApplicationComponent
import no.nrk.nrk_kotlin.business.depedencyInjection.DaggerApplicationComponent

class NrkApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        graph = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .apiModule(ApiModule())
                .build()
        graph.inject(this)
    }
}