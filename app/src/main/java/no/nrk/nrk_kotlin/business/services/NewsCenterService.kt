package no.nrk.nrk_kotlin.business.services

import io.reactivex.Observable
import no.nrk.nrk_kotlin.business.DataSupplier
import no.nrk.nrk_kotlin.business.models.NewsMessage

class NewsCenterService constructor(val dataSupplier: DataSupplier) {
    fun getNewsCenter(): Observable<MutableList<NewsMessage>> {
        return dataSupplier.getNewsMessages()
    }
}