package no.nrk.nrk_kotlin.business.depedencyInjection

import android.app.Application
import dagger.Module
import dagger.Provides
import no.nrk.nrk_kotlin.Constants
import no.nrk.nrk_kotlin.business.DataSupplier
import no.nrk.nrk_kotlin.business.api.NrkApi
import no.nrk.nrk_kotlin.business.services.NewsCenterService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.MINUTES)
                .connectTimeout(30, TimeUnit.MINUTES)
                .addInterceptor(Interceptor { chain ->
                    val request = chain.request()
                            .newBuilder()
                            .addHeader("nrkno-app-version-android", "999")
                            .build()
                    chain.proceed(request)
                })
        return clientBuilder.build();
    }

    @Provides
    fun providesRetrofit(httpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
                .client(httpClient)
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit
    }

    @Singleton
    @Provides
    fun providesNrkApiService(retrofit: Retrofit): NrkApi {
        return retrofit.create(NrkApi::class.java)
    }

    @Singleton
    @Provides
    fun providesDataSupplier(nrkApi: NrkApi): DataSupplier {
        return DataSupplier(nrkApi)

    }

    @Singleton
    @Provides
    fun providesNewsCenterService(dataSupplier: DataSupplier): NewsCenterService {
        return NewsCenterService(dataSupplier)
    }
}