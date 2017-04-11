package no.nrk.nrk_kotlin.business.depedencyInjection

import dagger.Component
import no.nrk.nrk_kotlin.userInterface.MainActivity
import no.nrk.nrk_kotlin.NrkApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class, ApiModule::class))
interface ApplicationComponent {

    fun inject(application: NrkApplication)

    fun inject(application: MainActivity)

}