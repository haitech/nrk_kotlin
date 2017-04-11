package no.nrk.nrk_kotlin.business.api

import io.reactivex.Observable
import no.nrk.nrk_kotlin.business.api.NewscenterDto
import retrofit2.http.GET

interface NrkApi {
    @GET("/api/newscenter") fun getNewsCenter(): Observable<NewscenterDto>
}

