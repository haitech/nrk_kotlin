package no.nrk.nrk_kotlin.userInterface

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import no.nrk.nrk_kotlin.business.services.NewsCenterService
import javax.inject.Inject


class MainPresenter @Inject constructor() {
    val disposable = CompositeDisposable()

    lateinit var view: MainView

    @Inject
    lateinit var newsCenterService: NewsCenterService

    fun attachView(view: MainView) {
        this.view = view
    }

    fun start() {
        view.showText("hello")

        disposable.add(newsCenterService.getNewsCenter()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ newsMessages ->
                    for ((id, title) in newsMessages) {
                        println("$id - $title")
                    }
                }, Throwable::printStackTrace)
        )
    }

    fun stop() {
        disposable.clear()
    }
}