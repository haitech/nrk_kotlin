package no.nrk.nrk_kotlin.business

import io.reactivex.Observable
import no.nrk.nrk_kotlin.business.api.NrkApi
import no.nrk.nrk_kotlin.business.models.NewsMessage

class DataSupplier constructor(private val nrkApi: NrkApi) {

    fun getNewsMessages(): Observable<MutableList<NewsMessage>> {
        return nrkApi.getNewsCenter().map { response ->
            val newsMessages: MutableList<NewsMessage> = ArrayList()
            response.layerItems.mapTo(newsMessages) { NewsMessage(it.id, it.title.text) }
        }
    }
}